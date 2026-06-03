Lưu ý:
1. Quy tắc đặt tên (code convention):
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
2. Cấu trúc thư mục sẽ là:
+ entity: chứa các class thực thể
+ controller: chứa các class để xử lý logic nghiệp vụ, gọi các hàm trong service
+ service: chứa các class để xử lý logic nghiệp vụ, gọi các hàm trong repository
+ repository: chứa các class để kết nối database, thực hiện các câu lệnh SQL
+ frontend: chứa class để chạy chương trình.
+ enums: chứa các enum (ví dụ: PositionName,..)
+ utils: chứa các class tiện ích, kết nối database, validate dữ liệu,...
3. Viết theo OOP và đúng coding convention  
4. Sử dụng đầy đủ comment  
5. Kết hợp mô hình 3-layers để xây dựng program 
6. Đây là cấu trúc database của tôi:
   CREATE TABLE Candidate (
   CandidateID INT AUTO_INCREMENT PRIMARY KEY,

   FirstName VARCHAR(50) NOT NULL,
   LastName VARCHAR(50) NOT NULL,

   Phone VARCHAR(20) NOT NULL,

   Email VARCHAR(100) NOT NULL UNIQUE,

   Password VARCHAR(255) NOT NULL,

   Role VARCHAR(20) NOT NULL
   CHECK (Role IN ('Experience', 'Fresher')),

   ExpInYear INT NULL,

   ProSkill VARCHAR(100) NULL,

   GraduationRank VARCHAR(50) NULL
   );