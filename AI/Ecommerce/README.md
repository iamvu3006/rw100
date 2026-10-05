# E-commerce Console App

Bài thực hành Java console gồm:

- **Bài 1 - Máy tính:** cộng, trừ, nhân, chia và kiểm tra chia cho 0.
- **Bài 2 - Quản lý sản phẩm/đơn hàng:** CRUD sản phẩm, lọc theo giá, top 3 sản phẩm đắt nhất, tạo đơn nhiều sản phẩm, kiểm tra email, tìm đơn theo khách hàng và xuất TXT.

## Kiến trúc 3 layer

- `src/model`: `Product`, `Customer`, `Order`, `Category`.
- `src/service`: `CalculatorService`, `ProductManagement`, `OrderManagement`.
- `src/ui`: `ConsoleApplication` chứa menu và xử lý nhập liệu.
- `src/App.java`: điểm khởi chạy chương trình.

## Chạy bằng VS Code

Mở file `src/App.java`, chọn **Run Java**. Chương trình chỉ dùng console, không cần thư viện ngoài.

## Chạy bằng terminal

```powershell
New-Item -ItemType Directory -Force bin
javac --release 23 -encoding UTF-8 -d bin (Get-ChildItem -Recurse -Filter *.java src | ForEach-Object FullName)
java -cp bin App
```

`--release 23` phù hợp với Java runtime hiện tại của môi trường thực hành; nếu máy dùng runtime khác, thay bằng phiên bản tương ứng.

Khi chọn chức năng xuất file, chương trình tạo `products.txt` và `orders.txt` tại thư mục chạy.
