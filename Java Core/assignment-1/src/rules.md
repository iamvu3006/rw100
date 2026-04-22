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