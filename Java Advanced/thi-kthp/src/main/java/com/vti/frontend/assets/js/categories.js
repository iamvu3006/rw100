initLayout("categories", "Danh mục món ăn");

async function loadCategories(){
  const body = qs("#catBody");
  const emptyState = qs("#emptyState");
  try{
    const cats = await api.get("/categories");
    window._catsCache = cats;
    if(cats.length === 0){
      body.innerHTML = "";
      emptyState.innerHTML = `<div class="empty-state"><h3>Chưa có danh mục nào</h3><p>Nhấn "Thêm danh mục" để bắt đầu.</p></div>`;
      return;
    }
    emptyState.innerHTML = "";
    body.innerHTML = cats.map(c=>`
      <tr>
        <td style="font-weight:600">${escapeHtml(c.name)}</td>
        <td style="color:var(--ink-soft)">${escapeHtml(c.description || "—")}</td>
        <td class="cell-actions">
          <button class="icon-btn" data-edit="${c.id}" title="Sửa">
            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M12 20h9M16.5 3.5a2.1 2.1 0 013 3L7 19l-4 1 1-4 12.5-12.5z"/></svg>
          </button>
          <button class="icon-btn" data-del="${c.id}" title="Xóa">
            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M3 6h18M8 6V4h8v2M19 6l-1 14H6L5 6"/></svg>
          </button>
        </td>
      </tr>`).join("");

    qsa("[data-edit]").forEach(b=> b.addEventListener("click", ()=> openEdit(b.dataset.edit)));
    qsa("[data-del]").forEach(b=> b.addEventListener("click", ()=> deleteCategory(b.dataset.del)));
  }catch(err){
    body.innerHTML = "";
    emptyState.innerHTML = `<div class="empty-state"><h3>Không tải được danh mục</h3><p>${escapeHtml(err.message)}</p></div>`;
  }
}

function openEdit(id){
  const c = window._catsCache.find(x=> String(x.id)===String(id));
  qs("#modalTitle").textContent = "Sửa danh mục";
  qs("#catId").value = c.id;
  qs("#name").value = c.name;
  qs("#description").value = c.description || "";
  qs("#formError").textContent = "";
  openModal("catModal");
}

qs("#btnAdd").addEventListener("click", ()=>{
  qs("#modalTitle").textContent = "Thêm danh mục";
  qs("#catForm").reset();
  qs("#catId").value = "";
  qs("#formError").textContent = "";
  openModal("catModal");
});

qs("#catForm").addEventListener("submit", async (e)=>{
  e.preventDefault();
  const id = qs("#catId").value;
  const payload = { name: qs("#name").value.trim(), description: qs("#description").value.trim() || null };
  if(!payload.name){ qs("#formError").textContent = "Tên danh mục không được để trống."; return; }

  qs("#saveBtn").disabled = true;
  try{
    if(id) await api.put(`/categories/${id}`, payload);
    else await api.post("/categories", payload);
    toast(id ? "Đã cập nhật danh mục" : "Đã thêm danh mục mới", "success");
    closeModal("catModal");
    loadCategories();
  }catch(err){
    qs("#formError").textContent = err.message;
  }finally{
    qs("#saveBtn").disabled = false;
  }
});

async function deleteCategory(id){
  const ok = await confirmDialog({ title:"Xóa danh mục", message:"Các món ăn thuộc danh mục này có thể bị ảnh hưởng. Tiếp tục xóa?", okText:"Xóa" });
  if(!ok) return;
  try{ await api.del(`/categories/${id}`); toast("Đã xóa danh mục","success"); loadCategories(); }
  catch(err){ toast(err.message,"error"); }
}

loadCategories();