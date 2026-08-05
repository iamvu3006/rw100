initLayout("index", "Tổng quan");

async function loadDashboard(){
  const statGrid = qs("#statGrid");
  const activeWrap = qs("#activeOrders");

  try{
    const [tables, ordersPage, dishesPage] = await Promise.all([
      api.get("/tables"),
      api.get("/orders?status=CREATED&size=50&sort=createdDate,desc"),
      api.get("/dishes?status=AVAILABLE&size=1")
    ]);

    const empty = tables.filter(t=>t.status==="EMPTY").length;
    const occupied = tables.filter(t=>t.status==="OCCUPIED").length;
    const reserved = tables.filter(t=>t.status==="RESERVED").length;
    const activeOrders = ordersPage.content || [];
    const revenueToday = activeOrders.reduce((s,o)=> s + Number(o.totalAmount||0), 0);

    statGrid.innerHTML = `
      <div class="stat-card"><div class="label">Tổng số bàn</div><div class="value">${tables.length}</div>
        <div class="sub">${empty} trống · ${occupied} đang dùng · ${reserved} đã đặt</div></div>
      <div class="stat-card"><div class="label">Đơn đang xử lý</div><div class="value">${activeOrders.length}</div>
        <div class="sub">Chưa thanh toán</div></div>
      <div class="stat-card"><div class="label">Giá trị đơn đang xử lý</div><div class="value" style="font-size:22px">${formatVND(revenueToday)}</div>
        <div class="sub">Tổng các đơn CREATED</div></div>
      <div class="stat-card"><div class="label">Món ăn khả dụng</div><div class="value">${dishesPage.totalElements ?? "—"}</div>
        <div class="sub">Đang phục vụ được</div></div>`;

    if(activeOrders.length === 0){
      activeWrap.innerHTML = `<div class="empty-state" style="grid-column:1/-1">
        <h3>Chưa có đơn nào đang xử lý</h3><p>Tạo đơn mới ở trang Đơn hàng.</p></div>`;
    } else {
      activeWrap.innerHTML = activeOrders.slice(0,6).map(renderMiniTicket).join("");
    }
  }catch(err){
    statGrid.innerHTML = `<div class="empty-state" style="grid-column:1/-1"><h3>Không tải được dữ liệu</h3><p>${escapeHtml(err.message)}</p></div>`;
  }
}

function renderMiniTicket(o){
  const items = (o.orderItems||[]).slice(0,3).map(it=>`<div class="ticket-item-row"><span>${escapeHtml(it.dishName)} x${it.quantity}</span></div>`).join("");
  return `<div class="ticket">
    <div class="ticket-top"><div><div class="ticket-table">Bàn ${escapeHtml(o.tableNumber)}</div><div class="ticket-id">#ORD-${String(o.id).padStart(4,"0")}</div></div>
      <span class="badge badge-gold">Đang xử lý</span></div>
    <div class="ticket-items">${items}</div>
    <div class="ticket-total"><span>Tổng</span><span>${formatVND(o.totalAmount)}</span></div>
    <div class="ticket-foot"><span class="ticket-time">${formatDateTime(o.createdDate)}</span>
      <a href="orders.html" class="btn btn-sm btn-outline">Chi tiết</a></div>
  </div>`;
}

loadDashboard();