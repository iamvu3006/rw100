# 🍽️ TEST GUIDE — Restaurant Management System

Hướng dẫn kiểm thử luồng demo trên **Swagger UI** hoặc **Postman**.

---

## 🔑 Thông tin đăng nhập mẫu

| Username | Password | Role  |
|----------|----------|-------|
| `admin`  | `123456` | ADMIN |
| `staff01`| `123456` | STAFF |

---

## 🌐 Truy cập Swagger UI

Sau khi khởi động ứng dụng (`./mvnw spring-boot:run`), mở trình duyệt:

`
http://localhost:8080/swagger-ui/index.html
`

---

## 📋 Luồng Demo Hoàn Chỉnh

### ✅ BƯỚC 1 — Login & Lấy Bearer Token

**Endpoint:** `POST /api/v1/auth/login`

**Request Body:**
`json
{
  username: admin,
  password: 123456
}
`

**Response (lưu lại token):**
`json
{
  token: eyJhbGciOiJIUzI1NiJ9...,
  id: 1,
  username: admin,
  fullName: Admin Hệ Thống,
  role: ADMIN
}
`

> **Trên Swagger UI**: Click nút 🔒 **Authorize** (góc trên phải) → Nhập `Bearer <token>` → Click Authorize.

---

### ✅ BƯỚC 2 — Xem danh sách Bàn ăn

**Endpoint:** `GET /api/v1/tables`

Kết quả: 6 bàn, tất cả `status: EMPTY`. Ghi nhớ `id` của bàn **B01** (thường là `1`).

---

### ✅ BƯỚC 3 — Xem danh sách Món ăn

**Endpoint:** `GET /api/v1/dishes`

Ghi nhớ `id` của các món muốn gọi (ví dụ: 1 = Cơm Gà, 4 = Chả Giò, 6 = Sinh Tố Bơ).

Filter nâng cao:
- `?search=Phở` — lọc theo tên
- `?categoryId=1` — lọc theo danh mục
- `?status=AVAILABLE` — chỉ món còn hàng

---

### ✅ BƯỚC 4 — Tạo Order cho Bàn B01

**Endpoint:** `POST /api/v1/orders`

**Request Body:**
`json
{
  tableId: 1,
  orderItems: [
    { dishId: 1, quantity: 2 },
    { dishId: 4, quantity: 1 },
    { dishId: 6, quantity: 2 }
  ]
}
`

→ Kiểm tra lại `GET /api/v1/tables` → Bàn B01 chuyển sang `OCCUPIED` ✅

---

### ✅ BƯỚC 5 — Xem chi tiết Order

**Endpoint:** `GET /api/v1/orders/1`

Hiển thị toàn bộ thông tin đơn: bàn, nhân viên, danh sách món, tổng tiền.

---

### ✅ BƯỚC 6 — Thanh toán Order

**Endpoint:** `PUT /api/v1/orders/1/pay`

Response: `status: PAID`, `paymentDate` được ghi nhận.

→ Kiểm tra lại `GET /api/v1/tables` → Bàn B01 trở về `EMPTY` ✅

---

### ✅ BƯỚC 7 — Hủy Order (demo tùy chọn)

Tạo order mới cho bàn B02 → Gọi `PUT /api/v1/orders/{id}/cancel`

→ Bàn B02 trở về `EMPTY` sau khi hủy ✅

---

## 🔒 Phân quyền ADMIN vs STAFF

| Hành động                   | ADMIN | STAFF     |
|-----------------------------|:-----:|:---------:|
| POST /api/v1/accounts       | ✅    | ❌ 403    |
| PUT /api/v1/accounts/{id}   | ✅    | ❌ 403    |
| DELETE /api/v1/accounts/{id}| ✅    | ❌ 403    |
| POST /api/v1/orders         | ✅    | ✅        |
| PUT /api/v1/orders/{id}/pay | ✅    | ✅        |
| GET /api/v1/dishes          | ✅    | ✅        |

Test: Login bằng `staff01` → thử `POST /api/v1/accounts` → nhận 403 Forbidden ✅

---

## 🔗 Endpoint Reference

| Method | URL | Mô tả |
|--------|-----|-------|
| POST | /api/v1/auth/login | Đăng nhập |
| GET  | /api/v1/auth/me | Thông tin tài khoản hiện tại |
| GET  | /api/v1/categories | Danh sách danh mục |
| GET  | /api/v1/dishes | Danh sách món ăn (có filter) |
| GET  | /api/v1/tables | Danh sách bàn ăn |
| POST | /api/v1/orders | Tạo đơn gọi món |
| PUT  | /api/v1/orders/{id} | Sửa đơn gọi món |
| GET  | /api/v1/orders/{id} | Chi tiết đơn |
| PUT  | /api/v1/orders/{id}/pay | Thanh toán |
| PUT  | /api/v1/orders/{id}/cancel | Hủy đơn |
