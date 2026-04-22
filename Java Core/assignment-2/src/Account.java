import java.util.Date;
@SuppressWarnings("unused")
public class Account {
    // Đây là các thuộc tính của lớp Account, nó sẽ lưu trữ thông tin của người dùng
    // Tương đương với các column của bảng account trong database
    int id;
    String email;
    String userName;
    String fullName;
    Department department;
    Position position;
    Group[] groups;
    Date createDate;
}
