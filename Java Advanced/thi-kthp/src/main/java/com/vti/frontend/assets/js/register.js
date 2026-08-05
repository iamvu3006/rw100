if(Auth.isLoggedIn()) window.location.href = "index.html";

const form = qs("#registerForm");
const banner = qs("#errorBanner");
const submitBtn = qs("#submitBtn");

form.addEventListener("submit", async (e)=>{
  e.preventDefault();
  banner.classList.remove("show");
  const fullName = qs("#fullName").value.trim();
  const username = qs("#username").value.trim();
  const email = qs("#email").value.trim();
  const password = qs("#password").value;

  if(!fullName || !username || !email || !password){
    banner.textContent = "Vui lòng điền đầy đủ thông tin.";
    banner.classList.add("show");
    return;
  }
  submitBtn.disabled = true;
  submitBtn.textContent = "Đang tạo tài khoản...";
  try{
    await api.post("/auth/register", { username, password, fullName, email });
    toast("Đăng ký thành công! Hãy đăng nhập.", "success");
    setTimeout(()=> window.location.href = "login.html", 900);
  }catch(err){
    banner.textContent = err.message || "Đăng ký thất bại.";
    banner.classList.add("show");
    submitBtn.disabled = false;
    submitBtn.textContent = "Đăng ký";
  }
});