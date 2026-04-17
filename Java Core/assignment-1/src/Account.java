import java.util.Date;

public class Account {
    // Đây là các thuộc tính của lớp Account, nó sẽ lưu trữ thông tin của người dùng
    // Tương đương với các column của bảng account trong database
    int id;
    String username;
    String fullname;
    Gender gender;
    Date createDate;
    int age;
    float height;
    float weight;
    float[] points;
    boolean isPassCourse;
    public enum Gender{
        MALE, FEMALE, UNKNOW;
    }

    //đây là các phương thức của lớp Account, nó sẽ thực hiện các hành động liên quan đến người dùng
    public void an (){
        System.out.println("đang ăn");
    }

    public void ngu (){
        System.out.println("đang ngủ");
    }
}
