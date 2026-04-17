import java.util.Date;

public class Program {
    public static void main(String[] args) {
        // Tạo một đối tượng Account để lưu trữ thông tin của người dùng
        Account acc1 = new Account();
        acc1.id = 1;
        acc1.username = "dongnv";
        acc1.fullname = "Nguyễn Viết Đồng";
        acc1.gender = Account.Gender.MALE;
        acc1.createDate = new Date();
        acc1.age = 27;
        acc1.height = 175.0f;
        acc1.weight = 80.0f;
        acc1.isPassCourse = true;
        acc1.points = new float[]{8.5f, 9.0f, 7.5f};
        //viết phương thức để in thông tin của người dùng
        printAccountInfo(acc1);
    }

    private static void printAccountInfo(Account acc1) {
        System.out.println("Thông tin tài khoản:");
        System.out.println("ID: " + acc1.id);
        System.out.println("Username: " + acc1.username);
        System.out.println("Fullname: " + acc1.fullname);
        System.out.println("Gender: " + acc1.gender);
        System.out.println("Create Date: " + acc1.createDate);
        System.out.println("Age: " + acc1.age);
        System.out.println("Height: " + acc1.height);
        System.out.println("Weight: " + acc1.weight);
        System.out.println("Is Pass Course: " + acc1.isPassCourse);
        System.out.println("Points: ");
        for (float point : acc1.points) {
            System.out.println(point);
        }
    }
}
