Lưu ý:
1. Quy tắc đặt tên:
 + Với class thì viết hoa chữ cái đầu tiên của mỗi từ,
không có dấu cách (ví dụ: Position, Account, Department, GroupAccount,...).
 + Với thuộc tính thì viết thường chữ cái đầu tiên của mỗi từ,
bắt đầu từ từ thứ 2 trở đi thì viết hoa chữ cái đầu,
không có dấu cách (ví dụ: id, name, username, createDate, fullName,..).
 + Với giá trị của enum thì viết hoa tất cả các chữ cái,
có dấu gạch dưới nếu có nhiều từ (ví dụ: DEV, TEST, PM, SALE, ...).
 + Với kiểu dữ liệu boolean thì tên thuộc tính nên bắt đầu bằng "is" 
(ví dụ: isDeleted, isPass...).
 + Đặt tên biến, tên hàm, tên class có ý nghĩa, dễ hiểu,
không nên đặt tên tắt, không được đặt tên vớ vẩn (ví dụ: String username,...).
2. Lưu ý Assignment 2:
 + Trong class GroupAccount sẽ bị khó khăn khi xử lý logic của Group và Account,
vì vậy nên bên Group hãy có thêm 1 thuộc tính Account[] accounts để lưu trữ các Account của Group đó,
còn bên Account thì có thêm 1 thuộc tính Group[] groups để lưu trữ các Group mà Account đó tham gia.
3. Lưu ý khi giải bài tập:
 + Ví dụ Exercise 1, thì chúng ta sẽ tạo 1 class có tên là Exercise1,
trong đó sẽ có 5 hàm tương ứng với 5 câu hỏi của Exercise 1, mỗi hàm sẽ trả về kết quả của câu hỏi đó. 
Mỗi question sẽ có 1 hàm riêng, không nên gộp nhiều câu hỏi vào 1 hàm
 + Ví dụ Exercise 2, thì chúng ta sẽ tạo 1 class có tên là Exercise2, trong đó sẽ có 6 hàm tương ứng với 6 câu hỏi của Exercise 2,
mỗi hàm sẽ trả về kết quả của câu hỏi đó. Sau đó chúng ta sẽ gọi hàm đó trong Program.java để in ra kết quả.