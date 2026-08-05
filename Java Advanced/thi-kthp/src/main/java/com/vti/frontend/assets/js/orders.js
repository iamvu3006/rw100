initLayout("orders", "Đơn hàng");

let currentPage = 0;
const pageSize = 9;
let tables = [];
let categories = [];
let dishes = [];
let cart = {}; // dishId -> {dish, qty}
let activeCategoryId = "all";

const statusMap = {
  CREATED:["Đang xử lý","badge-gold"],
  PAID:["Đã thanh toán","badge-green"],
  CANCELLED:["Đã hủy","badge-red"]
};

function buildQuery(){
  const params = new URLSearchParams();
  const status = qs("#statusFilter").value;
  const tableId = qs("#tableFilter").value;
  if(status) params.set("status", status);
  if(tableId) params.set("tableId", tableId);
  params.set("page", currentPage);
  params.set("size", pageSize);
  params.set("sort", "createdDate,desc");
  return params.toString();
}

function renderTicket(o){
  const [label, cls] = statusMap[o.status] || ["—","badge-gray"];
  const items = (o.orderItems||[]).slice(0,4).map(it=>`<div class="ticket-item-row"><span>${escapeHtml(it.dishName)} x${it.quantity}</span><span>${formatVND(it.subTotal)}</span></div>`).join("");
  return `<div class="ticket" data-order="${o.id}" style="cursor:pointer">
    <div class="ticket-top">
      <div><div class="ticket-table">Bàn ${escapeHtml(o.tableNumber)}</div><div class="ticket-id">#ORD-${String(o.id).padStart(4,"0")}</div></div>
      <span class="badge ${cls}"><span class="badge-dot"></span>${label}</span>
    </div>
    <div class="ticket-items">${items || '<span style="color:var(--ink-faint)">Không có món</span>'}</div>
    <div class="ticket-total"><span>Tổng</span><span>${formatVND(o.totalAmount)}</span></div>
    <div class="ticket-foot"><span class="ticket-time">${formatDateTime(o.createdDate)}</span>
      <span style="font-size:12px;color:var(--ink-faint)">${o.userName ? "bởi "+escapeHtml(o.userName) : ""}</span></div>
  </div>`;
}

async function loadOrders(){
  const grid = qs("#ordersGrid");
  const emptyState = qs("#emptyState");
  const pagination = qs("#pagination");
  try{
    const data = await api.get("/orders?" + buildQuery());
    if(data.content.length === 0){
      grid.innerHTML = "";
      pagination.style.display = "none";
      emptyState.innerHTML = `<div class="empty-state"><h3>Chưa có đơn hàng nào</h3><p>Nhấn "Tạo đơn mới" để bắt đầu ghi order.</p></div>`;
      return;
    }
    emptyState.innerHTML = "";
    grid.innerHTML = data.content.map(renderTicket).join("");
    qsa("[data-order]").forEach(el=> el.addEventListener("click", ()=> openDetail(el.dataset.order)));

    pagination.style.display = "flex";
    pagination.innerHTML = `
      <span>Trang ${data.number + 1} / ${data.totalPages || 1} · ${data.totalElements} đơn</span>
      <div class="pg-btns">
        <button class="btn btn-sm btn-outline" id="prevPg" ${data.first ? "disabled":""}>← Trước</button>
        <button class="btn btn-sm btn-outline" id="nextPg" ${data.last ? "disabled":""}>Sau →</button>
      </div>`;
    qs("#prevPg")?.addEventListener("click", ()=>{ currentPage--; loadOrders(); });
    qs("#nextPg")?.addEventListener("click", ()=>{ currentPage++; loadOrders(); });
  }catch(err){
    grid.innerHTML = "";
    pagination.style.display = "none";
    emptyState.innerHTML = `<div class="empty-state"><h3>Không tải được đơn hàng</h3><p>${escapeHtml(err.message)}</p></div>`;
  }
}

/* ---- Chi tiết đơn ---- */
async function openDetail(id){
  try{
    const o = await api.get(`/orders/${id}`);
    const [label, cls] = statusMap[o.status] || ["—","badge-gray"];
    qs("#detailTitle").textContent = `Đơn #ORD-${String(o.id).padStart(4,"0")} · Bàn ${o.tableNumber}`;
    qs("#detailBody").innerHTML = `
      <div style="display:flex;justify-content:space-between;margin-bottom:14px">
        <span class="badge ${cls}">${label}</span>
        <span style="font-size:12px;color:var(--ink-faint)">${formatDateTime(o.createdDate)}</span>
      </div>
      <div class="table-wrap" style="box-shadow:none">
        <table>
          <thead><tr><th>Món</th><th>SL</th><th>Đơn giá</th><th>Thành tiền</th></tr></thead>
          <tbody>${(o.orderItems||[]).map(it=>`<tr><td>${escapeHtml(it.dishName)}</td><td>${it.quantity}</td><td class="mono">${formatVND(it.price)}</td><td class="mono">${formatVND(it.subTotal)}</td></tr>`).join("")}</tbody>
        </table>
      </div>
      <div style="text-align:right;margin-top:12px;font-weight:700;font-family:'IBM Plex Mono',monospace;font-size:16px">Tổng cộng: ${formatVND(o.totalAmount)}</div>`;

    const footer = qs("#detailFooter");
    if(o.status === "CREATED"){
      footer.innerHTML = `
        <button class="btn btn-danger" id="cancelOrderBtn">Hủy đơn</button>
        <button class="btn btn-primary" id="payOrderBtn">Xác nhận thanh toán</button>`;
      qs("#payOrderBtn").onclick = async ()=>{
        const ok = await confirmDialog({ title:"Thanh toán đơn hàng", message:"Xác nhận đơn hàng đã được thanh toán?", okText:"Xác nhận", danger:false });
        if(!ok) return;
        try{ await api.put(`/orders/${o.id}/pay`); toast("Đã thanh toán đơn hàng","success"); closeModal("detailModal"); loadOrders(); }
        catch(err){ toast(err.message,"error"); }
      };
      qs("#cancelOrderBtn").onclick = async ()=>{
        const ok = await confirmDialog({ title:"Hủy đơn hàng", message:"Đơn hàng sẽ được đánh dấu là đã hủy. Tiếp tục?", okText:"Hủy đơn" });
        if(!ok) return;
        try{ await api.put(`/orders/${o.id}/cancel`); toast("Đã hủy đơn hàng","success"); closeModal("detailModal"); loadOrders(); }
        catch(err){ toast(err.message,"error"); }
      };
    } else {
      footer.innerHTML = `<button class="btn btn-outline" onclick="closeModal('detailModal')">Đóng</button>`;
    }
    openModal("detailModal");
  }catch(err){
    toast(err.message, "error");
  }
}

/* ---- Tạo đơn mới ---- */
function renderCategoryTabs(){
  const tabsEl = qs("#categoryTabs");
  tabsEl.innerHTML = `<button class="tab-btn ${activeCategoryId==='all'?'active':''}" data-cat="all">Tất cả</button>` +
    categories.map(c=>`<button class="tab-btn ${String(activeCategoryId)===String(c.id)?'active':''}" data-cat="${c.id}">${escapeHtml(c.name)}</button>`).join("");
  qsa("[data-cat]").forEach(btn=> btn.addEventListener("click", ()=>{ activeCategoryId = btn.dataset.cat==="all"?"all":Number(btn.dataset.cat); renderCategoryTabs(); renderDishPickList(); }));
}

function renderDishPickList(){
  const list = qs("#dishPickList");
  const filtered = activeCategoryId==="all" ? dishes : dishes.filter(d=> d.categoryId===activeCategoryId);
  if(filtered.length === 0){ list.innerHTML = `<div style="padding:20px;text-align:center;color:var(--ink-faint);font-size:13px">Không có món trong danh mục này</div>`; return; }
  list.innerHTML = filtered.map(d=>{
    const qty = cart[d.id]?.qty || 0;
    return `<div class="dish-pick">
      <div class="dish-pick-name">${escapeHtml(d.name)}<div class="dish-pick-price mono">${formatVND(d.price)}</div></div>
      <div class="qty-stepper">
        <button type="button" data-dec="${d.id}">−</button>
        <span class="mono" style="min-width:18px;text-align:center">${qty}</span>
        <button type="button" data-inc="${d.id}">+</button>
      </div>
    </div>`;
  }).join("");
  qsa("[data-inc]").forEach(b=> b.addEventListener("click", ()=> changeQty(Number(b.dataset.inc), +1)));
  qsa("[data-dec]").forEach(b=> b.addEventListener("click", ()=> changeQty(Number(b.dataset.dec), -1)));
}

function changeQty(dishId, delta){
  const dish = dishes.find(d=> d.id===dishId);
  const cur = cart[dishId]?.qty || 0;
  const next = Math.max(0, cur + delta);
  if(next === 0) delete cart[dishId];
  else cart[dishId] = { dish, qty: next };
  renderDishPickList();
  renderCart();
}

function renderCart(){
  const entries = Object.values(cart);
  const cartList = qs("#cartList");
  const cartTotal = qs("#cartTotal");
  if(entries.length === 0){
    cartList.innerHTML = `<div class="cart-empty">Chưa chọn món nào</div>`;
    cartTotal.textContent = formatVND(0);
    return;
  }
  cartList.innerHTML = entries.map(e=>`
    <div class="cart-row"><span>${escapeHtml(e.dish.name)} x${e.qty}</span><span class="mono">${formatVND(e.dish.price * e.qty)}</span></div>`).join("");
  const total = entries.reduce((s,e)=> s + e.dish.price * e.qty, 0);
  cartTotal.textContent = formatVND(total);
}

async function openNewOrderModal(){
  cart = {};
  activeCategoryId = "all";
  qs("#orderFormError").textContent = "";
  try{
    const [tablesData, catsData, dishesData] = await Promise.all([
      api.get("/tables"),
      api.get("/categories"),
      api.get("/dishes?status=AVAILABLE&size=200")
    ]);
    tables = tablesData;
    categories = catsData;
    dishes = dishesData.content;

    qs("#orderTable").innerHTML = tables.map(t=>`<option value="${t.id}">Bàn ${escapeHtml(t.tableNumber)} ${t.status==='EMPTY'?'(trống)':t.status==='OCCUPIED'?'(đang dùng)':'(đã đặt)'}</option>`).join("");
    renderCategoryTabs();
    renderDishPickList();
    renderCart();
    openModal("newOrderModal");
  }catch(err){
    toast(err.message, "error");
  }
}

qs("#btnNewOrder").addEventListener("click", openNewOrderModal);

qs("#submitOrderBtn").addEventListener("click", async ()=>{
  const tableId = Number(qs("#orderTable").value);
  const orderItems = Object.values(cart).map(e=> ({ dishId: e.dish.id, quantity: e.qty }));
  if(!tableId){ qs("#orderFormError").textContent = "Vui lòng chọn bàn."; return; }
  if(orderItems.length === 0){ qs("#orderFormError").textContent = "Vui lòng chọn ít nhất một món ăn."; return; }

  qs("#submitOrderBtn").disabled = true;
  try{
    await api.post("/orders", { tableId, orderItems });
    toast("Đã tạo đơn hàng mới","success");
    closeModal("newOrderModal");
    loadOrders();
  }catch(err){
    qs("#orderFormError").textContent = err.message;
  }finally{
    qs("#submitOrderBtn").disabled = false;
  }
});

[qs("#statusFilter")].forEach(el=> el.addEventListener("change", ()=>{ currentPage=0; loadOrders(); }));
qs("#tableFilter").addEventListener("change", ()=>{ currentPage=0; loadOrders(); });

(async function init(){
  try{
    const t = await api.get("/tables");
    qs("#tableFilter").insertAdjacentHTML("beforeend", t.map(x=>`<option value="${x.id}">Bàn ${escapeHtml(x.tableNumber)}</option>`).join(""));
  }catch(e){}
  loadOrders();
})();