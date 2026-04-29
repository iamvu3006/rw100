package frontend;

import backend.Exercise4;

import java.util.Scanner;

public class Program4 {
    public static void main(String[] args) {
        Exercise4 exercise = new Exercise4();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Câu 1: Nhập một chuỗi ký tự: ");
        String text = scanner.nextLine();
        System.out.println("Câu 1: Số lượng từ trong chuỗi là: " + exercise.question1CountWords(text));

        System.out.print("Câu 2: Nhập chuỗi s1: ");
        String s1 = scanner.nextLine();
        System.out.print("Câu 2: Nhập chuỗi s2: ");
        String s2 = scanner.nextLine();
        System.out.println("Câu 2: Chuỗi sau khi nối: " + exercise.question2ConcatStrings(s1, s2));

        System.out.print("Câu 3: Nhập tên: ");
        String nameQ3 = scanner.nextLine();
        System.out.println("Câu 3: Tên sau khi viết hoa chữ cái đầu: " + exercise.question3CapitalizeName(nameQ3));

        System.out.print("Câu 4: Nhập tên để in từng ký tự: ");
        String nameQ4 = scanner.nextLine();
        String[] chars = exercise.question4PrintCharacters(nameQ4);
        for (String line : chars) {
            System.out.println(line);
        }

        System.out.print("Câu 5: Nhập họ: ");
        String lastName = scanner.nextLine();
        System.out.print("Câu 5: Nhập tên: ");
        String firstName = scanner.nextLine();
        System.out.println("Câu 5: Họ và tên đầy đủ: " + exercise.question5CombineFullName(lastName, firstName));

        System.out.print("Câu 6: Nhập họ và tên đầy đủ: ");
        String fullName = scanner.nextLine();
        String[] parts = exercise.question6SplitFullName(fullName);
        System.out.println("Câu 6: Họ là: " + parts[0]);
        System.out.println("Câu 6: Tên đệm là: " + parts[1]);
        System.out.println("Câu 6: Tên là: " + parts[2]);

        System.out.print("Câu 7: Nhập họ và tên cần chuẩn hóa: ");
        String normalizeInput = scanner.nextLine();
        System.out.println("Câu 7: Họ và tên sau khi chuẩn hóa: " + exercise.question7NormalizeFullName(normalizeInput));

        int groupCount = readInt(scanner, "Câu 8/9: Nhập số lượng nhóm: ");
        String[] groups = new String[groupCount];
        for (int i = 0; i < groupCount; i++) {
            System.out.print("Nhóm " + (i + 1) + ": ");
            groups[i] = scanner.nextLine();
        }
        for (String group : exercise.question8GroupsContainingJava(groups)) {
            System.out.println("Câu 8: Nhóm có chứa chữ \"Java\": " + group);
        }
        for (String group : exercise.question9GroupsExactlyJava(groups)) {
            System.out.println("Câu 9: Nhóm \"Java\": " + group);
        }

        System.out.print("Câu 10: Nhập chuỗi thứ nhất: ");
        String q10s1 = scanner.nextLine();
        System.out.print("Câu 10: Nhập chuỗi thứ hai: ");
        String q10s2 = scanner.nextLine();
        System.out.println("Câu 10: Kết quả kiểm tra đảo ngược: " + (exercise.question10AreReverseStrings(q10s1, q10s2) ? "OK" : "KO"));

        System.out.print("Câu 11: Nhập chuỗi để đếm ký tự 'a': ");
        String q11 = scanner.nextLine();
        System.out.println("Câu 11: Số lần xuất hiện ký tự 'a': " + exercise.question11CountCharA(q11));

        System.out.print("Câu 12: Nhập chuỗi để đảo ngược: ");
        String q12 = scanner.nextLine();
        System.out.println("Câu 12: Chuỗi sau khi đảo ngược: " + exercise.question12ReverseString(q12));

        System.out.print("Câu 13: Nhập chuỗi để kiểm tra chữ số: ");
        String q13 = scanner.nextLine();
        System.out.println("Câu 13: Chuỗi không chứa chữ số: " + exercise.question13StringWithoutDigit(q13));

        System.out.print("Câu 14: Nhập chuỗi gốc: ");
        String q14str = scanner.nextLine();
        char q14target = readChar(scanner, "Câu 14: Nhập ký tự cần thay thế: ");
        char q14replacement = readChar(scanner, "Câu 14: Nhập ký tự thay thế mới: ");
        System.out.println("Câu 14: Chuỗi sau khi thay thế: " + exercise.question14ReplaceChar(q14str, q14target, q14replacement));

        System.out.print("Câu 15: Nhập chuỗi để đảo ngược theo từ: ");
        String q15 = scanner.nextLine();
        System.out.println("Câu 15: Chuỗi sau khi đảo ngược theo từ: " + exercise.question15ReverseWords(q15));

        System.out.print("Câu 16: Nhập chuỗi cần chia: ");
        String q16str = scanner.nextLine();
        int n = readInt(scanner, "Câu 16: Nhập độ dài mỗi phần: ");
        String[] split = exercise.question16SplitByLength(q16str, n);
        if (split == null) {
            System.out.println("Câu 16: Kết quả: KO");
        } else {
            for (String part : split) {
                System.out.println("Câu 16: Phần sau khi chia: " + part);
            }
        }

        scanner.close();
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            try {
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException ex) {
                System.out.println("Vui lòng nhập số nguyên hợp lệ.");
            }
        }
    }

    private static char readChar(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            if (!line.isEmpty()) {
                return line.charAt(0);
            }
            System.out.println("Vui lòng nhập ít nhất một ký tự.");
        }
    }
}
