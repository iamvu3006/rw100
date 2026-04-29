public class Exercise6 {
    public static int[] question1PrintEvenNumbersLessThan10() {
        int[] numbers = new int[]{2, 4, 6, 8};
        for (int number : numbers) {
            System.out.println(number);
        }
        return numbers;
    }

    public static void question2PrintAccounts(Account[] accounts) {
        if (accounts == null || accounts.length == 0) {
            System.out.println("(Khong co account)");
            return;
        }
        for (Account account : accounts) {
            String departmentName = account.department == null ? "Chua co phong ban" : account.department.name;
            System.out.println("Email: " + account.email + ", FullName: " + account.fullName + ", Department: " + departmentName);
        }
    }

    public static int[] question3PrintPositiveNumbersLessThan10() {
        int[] numbers = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int number : numbers) {
            System.out.println(number);
        }
        return numbers;
    }
}

