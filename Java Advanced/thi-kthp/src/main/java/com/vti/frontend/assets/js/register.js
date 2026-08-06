if (Auth.isLoggedIn()) window.location.href = "index.html";

const form = qs("#registerForm");
const banner = qs("#errorBanner");
const submitBtn = qs("#submitBtn");

function validateRegisterInput({
    fullName,
    username,
    email,
    password,
    confirmPassword,
}) {
    if (
        !/^[a-zA-Z][a-zA-Z0-9_]*$/.test(username) ||
        username.length < 4 ||
        username.length > 20
    ) {
        return "Username phải từ 4-20 ký tự, bắt đầu bằng chữ cái, chỉ gồm chữ/số/dấu gạch dưới.";
    }
    if (password.length < 6 || !/(?=.*[A-Za-z])(?=.*\d)/.test(password)) {
        return "Mật khẩu tối thiểu 6 ký tự và phải có cả chữ lẫn số.";
    }
    if (password !== confirmPassword) {
        return "Mật khẩu xác nhận không khớp.";
    }
    if (!/^[\p{L} ]+$/u.test(fullName) || fullName.trim().length < 2) {
        return "Họ tên không hợp lệ.";
    }
    if (!/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(email)) {
        return "Email không đúng định dạng.";
    }
    return null;
}

form.addEventListener("submit", async (e) => {
    e.preventDefault();
    banner.classList.remove("show");
    const fullName = qs("#fullName").value.trim();
    const username = qs("#username").value.trim();
    const email = qs("#email").value.trim();
    const password = qs("#password").value;
    const confirmPassword = qs("#confirmPassword").value;

    if (!fullName || !username || !email || !password || !confirmPassword) {
        banner.textContent = "Vui lòng điền đầy đủ thông tin.";
        banner.classList.add("show");
        return;
    }

    const validationError = validateRegisterInput({
        fullName,
        username,
        email,
        password,
        confirmPassword,
    });
    if (validationError) {
        banner.textContent = validationError;
        banner.classList.add("show");
        return;
    }

    submitBtn.disabled = true;
    submitBtn.textContent = "Đang tạo tài khoản...";
    try {
        await api.post("/auth/register", {
            username,
            password,
            confirmPassword,
            fullName,
            email,
        });
        toast("Đăng ký thành công! Hãy đăng nhập.", "success");
        setTimeout(() => (window.location.href = "login.html"), 900);
    } catch (err) {
        banner.textContent = err.message || "Đăng ký thất bại.";
        banner.classList.add("show");
        submitBtn.disabled = false;
        submitBtn.textContent = "Đăng ký";
    }
});
