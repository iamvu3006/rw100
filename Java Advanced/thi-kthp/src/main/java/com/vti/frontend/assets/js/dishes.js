initLayout("dishes", "Món ăn");

let currentPage = 0;
const pageSize = 10;
let categories = [];

async function loadCategoriesIntoFilters() {
    categories = await api.get("/categories");
    const filterSel = qs("#categoryFilter");
    const formSel = qs("#categoryId");
    categories.forEach((c) => {
        filterSel.insertAdjacentHTML(
            "beforeend",
            `<option value="${c.id}">${escapeHtml(c.name)}</option>`,
        );
        formSel.insertAdjacentHTML(
            "beforeend",
            `<option value="${c.id}">${escapeHtml(c.name)}</option>`,
        );
    });
}

function buildQuery() {
    const params = new URLSearchParams();
    const search = qs("#searchInput").value.trim();
    const categoryId = qs("#categoryFilter").value;
    const status = qs("#statusFilter").value;
    if (search) params.set("search", search);
    if (categoryId) params.set("categoryId", categoryId);
    if (status) params.set("status", status);
    params.set("page", currentPage);
    params.set("size", pageSize);
    params.set("sort", "id,desc");
    return params.toString();
}

async function loadDishes() {
    const body = qs("#dishBody");
    const emptyState = qs("#emptyState");
    const pagination = qs("#pagination");
    try {
        const data = await api.get("/dishes?" + buildQuery());
        window._dishesCache = data.content;
        if (data.content.length === 0) {
            body.innerHTML = "";
            pagination.style.display = "none";
            emptyState.innerHTML = `<div class="empty-state"><h3>Không tìm thấy món ăn</h3><p>Thử đổi bộ lọc hoặc thêm món ăn mới.</p></div>`;
            return;
        }
        emptyState.innerHTML = "";
        body.innerHTML = data.content
            .map(
                (d) => `
      <tr>
        <td>${d.imageUrl ? `<img class="dish-thumb" src="${escapeHtml(d.imageUrl)}" onerror="this.style.visibility='hidden'">` : `<div class="dish-thumb"></div>`}</td>
        <td style="font-weight:600">${escapeHtml(d.name)}</td>
        <td style="color:var(--ink-soft)">${escapeHtml(d.categoryName || "—")}</td>
        <td class="mono">${formatVND(d.price)}</td>
        <td>${d.status === "AVAILABLE" ? '<span class="badge badge-green"><span class="badge-dot"></span>Đang phục vụ</span>' : '<span class="badge badge-red"><span class="badge-dot"></span>Ngừng phục vụ</span>'}</td>
        <td class="cell-actions">
          <button class="icon-btn" data-edit="${d.id}" title="Sửa">
            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M12 20h9M16.5 3.5a2.1 2.1 0 013 3L7 19l-4 1 1-4 12.5-12.5z"/></svg>
          </button>
          <button class="icon-btn" data-del="${d.id}" title="Xóa">
            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M3 6h18M8 6V4h8v2M19 6l-1 14H6L5 6"/></svg>
          </button>
        </td>
      </tr>`,
            )
            .join("");

        pagination.style.display = "flex";
        pagination.innerHTML = `
      <span>Trang ${data.number + 1} / ${data.totalPages || 1} · ${data.totalElements} món</span>
      <div class="pg-btns">
        <button class="btn btn-sm btn-outline" id="prevPg" ${data.first ? "disabled" : ""}>← Trước</button>
        <button class="btn btn-sm btn-outline" id="nextPg" ${data.last ? "disabled" : ""}>Sau →</button>
      </div>`;
        qs("#prevPg")?.addEventListener("click", () => {
            currentPage--;
            loadDishes();
        });
        qs("#nextPg")?.addEventListener("click", () => {
            currentPage++;
            loadDishes();
        });

        qsa("[data-edit]").forEach((b) =>
            b.addEventListener("click", () => openEdit(b.dataset.edit)),
        );
        qsa("[data-del]").forEach((b) =>
            b.addEventListener("click", () => deleteDish(b.dataset.del)),
        );
    } catch (err) {
        body.innerHTML = "";
        pagination.style.display = "none";
        emptyState.innerHTML = `<div class="empty-state"><h3>Không tải được danh sách món ăn</h3><p>${escapeHtml(err.message)}</p></div>`;
    }
}

function openEdit(id) {
    const d = window._dishesCache.find((x) => String(x.id) === String(id));
    qs("#modalTitle").textContent = "Sửa món ăn";
    qs("#dishId").value = d.id;
    qs("#name").value = d.name;
    qs("#price").value = d.price;
    qs("#categoryId").value = d.categoryId;
    qs("#imageUrl").value = d.imageUrl || "";
    qs("#description").value = d.description || "";
    qs("#status").value = d.status;
    qs("#formError").textContent = "";
    openModal("dishModal");
}

qs("#btnAdd").addEventListener("click", () => {
    qs("#modalTitle").textContent = "Thêm món ăn";
    qs("#dishForm").reset();
    qs("#dishId").value = "";
    qs("#formError").textContent = "";
    openModal("dishModal");
});

qs("#dishForm").addEventListener("submit", async (e) => {
    e.preventDefault();
    const id = qs("#dishId").value;
    const payload = {
        name: qs("#name").value.trim(),
        price: Number(qs("#price").value),
        description: qs("#description").value.trim() || null,
        imageUrl: qs("#imageUrl").value.trim() || null,
        status: qs("#status").value,
        categoryId: Number(qs("#categoryId").value) || null,
    };
    if (!payload.name || !payload.categoryId || isNaN(payload.price)) {
        qs("#formError").textContent =
            "Vui lòng nhập tên món, giá và chọn danh mục.";
        return;
    }
    qs("#saveBtn").disabled = true;
    try {
        if (id) await api.put(`/dishes/${id}`, payload);
        else await api.post("/dishes", payload);
        toast(id ? "Đã cập nhật món ăn" : "Đã thêm món ăn mới", "success");
        closeModal("dishModal");
        loadDishes();
    } catch (err) {
        qs("#formError").textContent = err.message;
    } finally {
        qs("#saveBtn").disabled = false;
    }
});

async function deleteDish(id) {
    const ok = await confirmDialog({
        title: "Xóa món ăn",
        message:
            "Món ăn sẽ bị xóa khỏi thực đơn (nếu đã từng có trong đơn hàng, hệ thống sẽ tự chuyển sang Ngừng phục vụ). Tiếp tục?",
        okText: "Xóa",
    });
    if (!ok) return;
    try {
        const message = await api.del(`/dishes/${id}`);
        toast(message || "Đã xử lý món ăn", "success");
        loadDishes();
    } catch (err) {
        toast(err.message, "error");
    }
}

[qs("#categoryFilter"), qs("#statusFilter")].forEach((el) =>
    el.addEventListener("change", () => {
        currentPage = 0;
        loadDishes();
    }),
);
let searchDebounce;
qs("#searchInput").addEventListener("input", () => {
    clearTimeout(searchDebounce);
    searchDebounce = setTimeout(() => {
        currentPage = 0;
        loadDishes();
    }, 350);
});

(async function init() {
    await loadCategoriesIntoFilters();
    loadDishes();
})();
