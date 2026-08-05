// Cấu hình gốc API — đổi lại nếu backend chạy ở địa chỉ/port khác
const API_BASE = "http://localhost:8080/api/v1";

const Auth = {
  get(){ try{ return JSON.parse(localStorage.getItem("rw100_auth")); }catch(e){ return null; } },
  set(data){ localStorage.setItem("rw100_auth", JSON.stringify(data)); },
  clear(){ localStorage.removeItem("rw100_auth"); },
  isLoggedIn(){ return !!Auth.get()?.token; },
  isAdmin(){ return Auth.get()?.role === "ADMIN"; }
};

function requireAuth(){
  if(!Auth.isLoggedIn()){ window.location.href = "login.html"; }
}
function requireAdmin(){
  requireAuth();
  if(!Auth.isAdmin()){
    document.body.innerHTML = `<div style="display:flex;align-items:center;justify-content:center;height:100vh;font-family:Inter,sans-serif;color:#5B5147;text-align:center;padding:20px">
      <div><h2 style="font-family:Fraunces,serif;color:#2B2320;margin-bottom:8px">Không có quyền truy cập</h2>
      <p>Chỉ tài khoản <b>ADMIN</b> mới xem được trang này.</p>
      <a href="index.html" style="color:#C68A15;font-weight:600">← Về trang chủ</a></div></div>`;
    throw new Error("stop");
  }
}

async function apiFetch(path, options = {}){
  const auth = Auth.get();
  const headers = { "Content-Type":"application/json", ...(options.headers||{}) };
  if(auth?.token) headers["Authorization"] = "Bearer " + auth.token;

  let res;
  try{
    res = await fetch(API_BASE + path, { ...options, headers });
  }catch(e){
    throw new Error("Không thể kết nối tới server. Kiểm tra backend có đang chạy ở " + API_BASE + " không.");
  }

  if(res.status === 401){
    Auth.clear();
    window.location.href = "login.html";
    throw new Error("Phiên đăng nhập đã hết hạn");
  }

  const text = await res.text();
  let data = null;
  try{ data = text ? JSON.parse(text) : null; }catch(e){ data = text; }

  if(!res.ok){
    const msg = (data && (data.message || data.error)) || (typeof data === "string" ? data : null) || "Có lỗi xảy ra (" + res.status + ")";
    throw new Error(msg);
  }
  return data;
}

const api = {
  get:(path)=> apiFetch(path, { method:"GET" }),
  post:(path, body)=> apiFetch(path, { method:"POST", body: JSON.stringify(body) }),
  put:(path, body)=> apiFetch(path, { method:"PUT", body: JSON.stringify(body) }),
  patch:(path)=> apiFetch(path, { method:"PATCH" }),
  del:(path)=> apiFetch(path, { method:"DELETE" }),
};

function formatVND(amount){
  if(amount == null) return "0 ₫";
  return new Intl.NumberFormat("vi-VN").format(amount) + " ₫";
}
function formatDateTime(iso){
  if(!iso) return "—";
  const d = new Date(iso);
  return d.toLocaleString("vi-VN", { day:"2-digit", month:"2-digit", year:"numeric", hour:"2-digit", minute:"2-digit" });
}
function escapeHtml(str){
  if(str == null) return "";
  return String(str).replace(/[&<>"']/g, c => ({"&":"&amp;","<":"&lt;",">":"&gt;",'"':"&quot;","'":"&#39;"}[c]));
}
function initials(name){
  if(!name) return "?";
  const parts = name.trim().split(/\s+/);
  return (parts[0][0] + (parts.length>1?parts[parts.length-1][0]:"")).toUpperCase();
}
function qs(sel, root=document){ return root.querySelector(sel); }
function qsa(sel, root=document){ return Array.from(root.querySelectorAll(sel)); }