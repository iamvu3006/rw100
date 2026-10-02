import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập số nguyên a: ");
        int a = scanner.nextInt();
        System.out.print("Nhập số nguyên b: ");
        int b = scanner.nextInt();
        System.out.println("Tổng của " + a + " và " + b + " là: " + add(a, b));
        System.out.println("Hiệu của " + a + " và " + b + " là: " + subtract(a, b));
        scanner.close();
    }

    // tạo hàm add 2 số nguyên a,b
    public static int add(int a, int b) {
        return a + b;
    }

    // tạo hàm subtract 2 số nguyên a,b
    public static int subtract(int a, int b) {
        return a - b;
    }
}
