if (Auth.isLoggedIn()) window.location.href = "index.html";

// Nếu vừa đăng xuất xong và được redirect về đây -> hiện toast xác nhận
if (sessionStorage.getItem("rw100_just_logged_out")) {
    sessionStorage.removeItem("rw100_just_logged_out");
    toast("Đã đăng xuất thành công", "success");
}

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
        // Đánh dấu vừa đăng nhập -> trang tiếp theo (index.html) sẽ hiện toast chào mừng
        sessionStorage.setItem(
            "rw100_just_logged_in",
            data.fullName || data.username || "",
        );
        window.location.href = "index.html";
    } catch (err) {
        banner.textContent = err.message || "Sai tên đăng nhập hoặc mật khẩu.";
        banner.classList.add("show");
        submitBtn.disabled = false;
        submitBtn.textContent = "Đăng nhập";
    }
});
