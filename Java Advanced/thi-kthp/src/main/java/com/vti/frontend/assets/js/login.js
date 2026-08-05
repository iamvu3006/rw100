if (Auth.isLoggedIn()) window.location.href = "index.html";

const form = qs("#loginForm");
const banner = qs("#errorBanner");
const submitBtn = qs("#submitBtn");

form.addEventListener("submit", async (e) => {
    e.preventDefault();
    banner.classList.remove("show");
    const username = qs("#username").value.trim();
    const password = qs("#password").value;
    if (!username || !password) {
        banner.textContent = "Vui lòng nhập đầy đủ tên đăng nhập và mật khẩu.";
        banner.classList.add("show");
        return;
    }
    submitBtn.disabled = true;
    submitBtn.textContent = "Đang đăng nhập...";
    try {
        const data = await api.post("/auth/login", { username, password });
        Auth.set(data);
        window.location.href = "index.html";
    } catch (err) {
        banner.textContent = err.message || "Sai tên đăng nhập hoặc mật khẩu.";
        banner.classList.add("show");
        submitBtn.disabled = false;
        submitBtn.textContent = "Đăng nhập";
    }
});
