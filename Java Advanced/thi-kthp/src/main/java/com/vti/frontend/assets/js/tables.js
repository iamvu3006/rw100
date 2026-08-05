initLayout("tables", "Bàn ăn");

const statusLabel = { EMPTY:["Trống","badge-green"], OCCUPIED:["Đang dùng","badge-gold"], RESERVED:["Đã đặt trước","badge-blue"] };

async function loadTables(){
  const body = qs("#tableBody");
  const emptyState = qs("#emptyState");
  try{
    const tables = await api.get("/tables");
    if(tables.length === 0){
      body.innerHTML = "";
      emptyState.innerHTML = `<div class="empty-state"><h3>Chưa có bàn nào</h3><p>Nhấn "Thêm bàn" để tạo bàn đầu tiên.</p></div>`;
      return;
    }
    emptyState.innerHTML = "";
    body.innerHTML = tables.map(t=>{
      const [label, cls] = statusLabel[t.status] || ["—","badge-gray"];
      return `<tr>
        <td class="mono" style="font-weight:600">${escapeHtml(t.tableNumber)}</td>
        <td>${t.capacity ?? "—"} người</td>
        <td>
          <select class="field" style="padding:5px 8px;font-size:12px" data-status-select data-id="${t.id}">
            ${Object.keys(statusLabel).map(s=>`<option value="${s}" ${s===t.status?"selected":""}>${statusLabel[s][0]}</option>`).join("")}
          </select>
        </td>
        <td class="cell-actions">
          <button class="icon-btn" data-edit="${t.id}" title="Sửa">
            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M12 20h9M16.5 3.5a2.1 2.1 0 013 3L7 19l-4 1 1-4 12.5-12.5z"/></svg>
          </button>
          <button class="icon-btn" data-del="${t.id}" title="Xóa">
            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M3 6h18M8 6V4h8v2M19 6l-1 14H6L5 6"/></svg>
          </button>
        </td>
      </tr>`;
    }).join("");

    window._tablesCache = tables;
    qsa("[data-status-select]").forEach(sel=>{
      sel.addEventListener("change", async ()=>{
        try{ await api.patch(`/tables/${sel.dataset.id}/status?status=${sel.value}`); toast("Đã cập nhật trạng thái bàn","success"); loadTables(); }
        catch(err){ toast(err.message,"error"); }
      });
    });
    qsa("[data-edit]").forEach(btn=> btn.addEventListener("click", ()=> openEdit(btn.dataset.edit)));
    qsa("[data-del]").forEach(btn=> btn.addEventListener("click", ()=> deleteTable(btn.dataset.del)));
  }catch(err){
    body.innerHTML = "";
    emptyState.innerHTML = `<div class="empty-state"><h3>Không tải được danh sách bàn</h3><p>${escapeHtml(err.message)}</p></div>`;
  }
}

function openEdit(id){
  const t = window._tablesCache.find(x=> String(x.id)===String(id));
  qs("#modalTitle").textContent = "Sửa bàn ăn";
  qs("#tableId").value = t.id;
  qs("#tableNumber").value = t.tableNumber;
  qs("#capacity").value = t.capacity ?? "";
  qs("#status").value = t.status;
  qs("#formError").textContent = "";
  openModal("tableModal");
}

qs("#btnAdd").addEventListener("click", ()=>{
  qs("#modalTitle").textContent = "Thêm bàn ăn";
  qs("#tableForm").reset();
  qs("#tableId").value = "";
  qs("#formError").textContent = "";
  openModal("tableModal");
});

qs("#tableForm").addEventListener("submit", async (e)=>{
  e.preventDefault();
  const id = qs("#tableId").value;
  const payload = {
    tableNumber: qs("#tableNumber").value.trim(),
    capacity: Number(qs("#capacity").value) || null,
    status: qs("#status").value
  };
  if(!payload.tableNumber){ qs("#formError").textContent = "Số bàn không được để trống."; return; }

  qs("#saveBtn").disabled = true;
  try{
    if(id) await api.put(`/tables/${id}`, payload);
    else await api.post("/tables", payload);
    toast(id ? "Đã cập nhật bàn ăn" : "Đã thêm bàn ăn mới", "success");
    closeModal("tableModal");
    loadTables();
  }catch(err){
    qs("#formError").textContent = err.message;
  }finally{
    qs("#saveBtn").disabled = false;
  }
});

async function deleteTable(id){
  const ok = await confirmDialog({ title:"Xóa bàn ăn", message:"Bàn ăn sẽ bị xóa vĩnh viễn. Tiếp tục?", okText:"Xóa" });
  if(!ok) return;
  try{ await api.del(`/tables/${id}`); toast("Đã xóa bàn ăn","success"); loadTables(); }
  catch(err){ toast(err.message,"error"); }
}

loadTables();