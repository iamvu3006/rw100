requireAdmin();
initLayout("accounts", "Tài khoản nhân viên");

let currentPage = 0;
const pageSize = 10;

function buildQuery(){
  const params = new URLSearchParams();
  const search = qs("#searchInput").value.trim();
  const role = qs("#roleFilter").value;
  if(search) params.set("search", search);
  if(role) params.set("role", role);
  params.set("page", currentPage);
  params.set("size", pageSize);
  params.set("sort", "id,desc");
  return params.toString();
}

async function loadAccounts(){
  const body = qs("#accBody");
  const emptyState = qs("#emptyState");
  const pagination = qs("#pagination");
  try{
    const data = await api.get("/accounts?" + buildQuery());
    window._accCache = data.content;
    if(data.content.length === 0){
      body.innerHTML = "";
      pagination.style.display = "none";
      emptyState.innerHTML = `<div class="empty-state"><h3>Không tìm thấy tài khoản</h3><p>Thử đổi bộ lọc hoặc thêm tài khoản mới.</p></div>`;
      return;
    }
    emptyState.innerHTML = "";
    body.innerHTML = data.content.map(a=>`
      <tr>
        <td><div class="avatar" style="width:30px;height:30px;font-size:11px;background:var(--gold-soft);color:var(--gold-dark)">${initials(a.fullName)}</div></td>
        <td style="font-weight:600">${escapeHtml(a.fullName)}</td>
        <td class="mono">${escapeHtml(a.username)}</td>
        <td>${escapeHtml(a.email)}</td>
        <td>${a.role==="ADMIN" ? '<span class="badge badge-gold">Quản trị viên</span>' : '<span class="badge badge-gray">Nhân viên</span>'}</td>
        <td style="color:var(--ink-soft);font-size:12.5px">${formatDateTime(a.createdDate)}</td>
        <td class="cell-actions">
          <button class="icon-btn" data-edit="${a.id}" title="Sửa">
            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M12 20h9M16.5 3.5a2.1 2.1 0 013 3L7 19l-4 1 1-4 12.5-12.5z"/></svg>
          </button>
          <button class="icon-btn" data-del="${a.id}" title="Xóa">
            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M3 6h18M8 6V4h8v2M19 6l-1 14H6L5 6"/></svg>
          </button>
        </td>
      </tr>`).join("");

    pagination.style.display = "flex";
    pagination.innerHTML = `
      <span>Trang ${data.number + 1} / ${data.totalPages || 1} · ${data.totalElements} tài khoản</span>
      <div class="pg-btns">
        <button class="btn btn-sm btn-outline" id="prevPg" ${data.first ? "disabled":""}>← Trước</button>
        <button class="btn btn-sm btn-outline" id="nextPg" ${data.last ? "disabled":""}>Sau →</button>
      </div>`;
    qs("#prevPg")?.addEventListener("click", ()=>{ currentPage--; loadAccounts(); });
    qs("#nextPg")?.addEventListener("click", ()=>{ currentPage++; loadAccounts(); });

    qsa("[data-edit]").forEach(b=> b.addEventListener("click", ()=> openEdit(b.dataset.edit)));
    qsa("[data-del]").forEach(b=> b.addEventListener("click", ()=> deleteAccount(b.dataset.del)));
  }catch(err){
    body.innerHTML = "";
    pagination.style.display = "none";
    emptyState.innerHTML = `<div class="empty-state"><h3>Không tải được danh sách tài khoản</h3><p>${escapeHtml(err.message)}</p></div>`;
  }
}

function openEdit(id){
  const a = window._accCache.find(x=> String(x.id)===String(id));
  qs("#modalTitle").textContent = "Sửa tài khoản";
  qs("#accId").value = a.id;
  qs("#username").value = a.username;
  qs("#usernameRow").style.display = "none"; // username không cho sửa
  qs("#passwordRow").style.display = "none"; // đổi mật khẩu không nằm trong API update
  qs("#fullName").value = a.fullName;
  qs("#email").value = a.email;
  qs("#role").value = a.role;
  qs("#avatarUrl").value = a.avatarUrl || "";
  qs("#formError").textContent = "";
  openModal("accModal");
}

qs("#btnAdd").addEventListener("click", ()=>{
  qs("#modalTitle").textContent = "Thêm tài khoản";
  qs("#accForm").reset();
  qs("#accId").value = "";
  qs("#usernameRow").style.display = "";
  qs("#passwordRow").style.display = "";
  qs("#formError").textContent = "";
  openModal("accModal");
});

qs("#accForm").addEventListener("submit", async (e)=>{
  e.preventDefault();
  const id = qs("#accId").value;
  qs("#saveBtn").disabled = true;
  try{
    if(id){
      const payload = {
        fullName: qs("#fullName").value.trim(),
        email: qs("#email").value.trim(),
        role: qs("#role").value,
        avatarUrl: qs("#avatarUrl").value.trim() || null
      };
      if(!payload.fullName || !payload.email){ qs("#formError").textContent = "Vui lòng nhập đầy đủ họ tên và email."; qs("#saveBtn").disabled=false; return; }
      await api.put(`/accounts/${id}`, payload);
      toast("Đã cập nhật tài khoản", "success");
    } else {
      const payload = {
        username: qs("#username").value.trim(),
        password: qs("#password").value,
        fullName: qs("#fullName").value.trim(),
        email: qs("#email").value.trim(),
        role: qs("#role").value,
        avatarUrl: qs("#avatarUrl").value.trim() || null
      };
      if(!payload.username || !payload.password || !payload.fullName || !payload.email){
        qs("#formError").textContent = "Vui lòng nhập đầy đủ thông tin bắt buộc.";
        qs("#saveBtn").disabled=false; return;
      }
      await api.post("/accounts", payload);
      toast("Đã thêm tài khoản mới", "success");
    }
    closeModal("accModal");
    loadAccounts();
  }catch(err){
    qs("#formError").textContent = err.message;
  }finally{
    qs("#saveBtn").disabled = false;
  }
});

async function deleteAccount(id){
  const ok = await confirmDialog({ title:"Xóa tài khoản", message:"Tài khoản sẽ bị xóa vĩnh viễn khỏi hệ thống. Tiếp tục?", okText:"Xóa" });
  if(!ok) return;
  try{ await api.del(`/accounts/${id}`); toast("Đã xóa tài khoản","success"); loadAccounts(); }
  catch(err){ toast(err.message,"error"); }
}

let searchDebounce;
qs("#searchInput").addEventListener("input", ()=>{ clearTimeout(searchDebounce); searchDebounce = setTimeout(()=>{ currentPage=0; loadAccounts(); }, 350); });
qs("#roleFilter").addEventListener("change", ()=>{ currentPage=0; loadAccounts(); });

loadAccounts();