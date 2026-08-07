// Vẽ sidebar + topbar dùng chung, currentPage = "index"|"orders"|"tables"|"dishes"|"categories"|"accounts"
function initLayout(currentPage, pageTitle) {
    requireAuth();
    const auth = Auth.get();

    // Vừa đăng nhập xong -> hiện toast chào mừng đúng 1 lần
    const justLoggedIn = sessionStorage.getItem("rw100_just_logged_in");
    if (justLoggedIn) {
        sessionStorage.removeItem("rw100_just_logged_in");
        toast(`Đăng nhập thành công! Chào mừng ${justLoggedIn}`, "success");
    }

    const navItems = [
        {
            key: "index",
            href: "index.html",
            label: "Tổng quan",
            icon: '<path d="M3 13h8V3H3v10zM13 21h8V11h-8v10zM13 3v6h8V3h-8zM3 21h8v-6H3v6z"/>',
        },
        {
            key: "orders",
            href: "orders.html",
            label: "Đơn hàng",
            icon: '<path d="M9 2h6v4H9zM6 6h12l1 15H5z"/><path d="M9 10h6M9 14h6"/>',
        },
        {
            key: "tables",
            href: "tables.html",
            label: "Bàn ăn",
            icon: '<rect x="3" y="9" width="18" height="3"/><path d="M5 12v7M19 12v7M9 12v7M15 12v7"/>',
        },
        {
            key: "dishes",
            href: "dishes.html",
            label: "Món ăn",
            icon: '<path d="M6 2v8a3 3 0 003 3M9 2v13M18 2c-2 3-2 8 0 10v10"/>',
        },
        {
            key: "categories",
            href: "categories.html",
            label: "Danh mục",
            icon: '<path d="M20 12v7a1 1 0 01-1 1H5a1 1 0 01-1-1v-7M3 7h18l-2-5H5z"/><path d="M3 7l2 5h14l2-5"/>',
        },
    ];
    if (auth.role === "ADMIN") {
        navItems.push({
            key: "accounts",
            href: "accounts.html",
            label: "Tài khoản",
            icon: '<circle cx="12" cy="8" r="4"/><path d="M4 21c0-4 4-6 8-6s8 2 8 6"/>',
        });
    }

    const shell = document.createElement("div");
    shell.className = "app-shell";
    shell.innerHTML = `
    <aside class="sidebar" id="sidebar">
      <div class="brand"><div class="brand-name">RW100</div><div class="brand-tag">Nhà hàng · Order desk</div></div>
      <nav class="nav">
        ${navItems
            .map(
                (
                    i,
                ) => `<a class="nav-link ${i.key === currentPage ? "active" : ""}" href="${i.href}">
            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.8" stroke-linecap="round" stroke-linejoin="round">${i.icon}</svg>
            ${i.label}</a>`,
            )
            .join("")}
      </nav>
      <div class="sidebar-footer">
        <div class="user-chip">
          <div class="avatar">${initials(auth.fullName)}</div>
          <div class="user-info">
            <div class="user-name">${escapeHtml(auth.fullName)}</div>
            <div class="user-role">${auth.role === "ADMIN" ? "Quản trị viên" : "Nhân viên"}</div>
          </div>
          <button class="logout-btn" id="logoutBtn" title="Đăng xuất">
            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M9 21H5a2 2 0 01-2-2V5a2 2 0 012-2h4M16 17l5-5-5-5M21 12H9"/></svg>
          </button>
        </div>
      </div>
    </aside>
    <div class="main">
      <div class="topbar">
        <div style="display:flex;align-items:center;gap:12px">
          <button class="menu-toggle" id="menuToggle">
            <svg width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M3 6h18M3 12h18M3 18h18"/></svg>
          </button>
          <div class="topbar-title">${escapeHtml(pageTitle)}</div>
        </div>
      </div>
      <div class="content" id="pageContent"></div>
    </div>`;

    const existingContent = document.getElementById("app")?.innerHTML || "";
    document.getElementById("app").replaceWith(shell);
    document.getElementById("pageContent").innerHTML = existingContent;

    document.getElementById("logoutBtn").addEventListener("click", async () => {
        const confirmed = await confirmDialog({
            title: "Đăng xuất",
            message: "Bạn có chắc muốn đăng xuất không?",
            okText: "Đăng xuất",
            danger: true,
        });
        if (!confirmed) return;

        Auth.clear();
        sessionStorage.setItem("rw100_just_logged_out", "1");
        window.location.href = "login.html";
    });

    document.getElementById("menuToggle").addEventListener("click", () => {
        document.getElementById("sidebar").classList.toggle("open");
    });
}
