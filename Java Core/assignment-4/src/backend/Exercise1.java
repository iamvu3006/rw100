package backend;

import java.util.Random;

public class Exercise1 {
    public int question1RoundSalary1() {
        float salary1 = 5240.5f;
        return Math.round(salary1);
    }

    public int question1RoundSalary2() {
        float salary2 = 10970.055f;
        return Math.round(salary2);
    }

    public String question2RandomFiveDigits() {
        Random random = new Random();
        int value = random.nextInt(100000);
        return String.format("%05d", value);
    }

    public String question3LastTwoDigits(String fiveDigits) {
        if (fiveDigits == null || fiveDigits.length() < 2) {
            return "";
        }
        return fiveDigits.substring(fiveDigits.length() - 2);
    }

    public float question4Divide(int a, int b) {
        if (b == 0) {
            return Float.NaN;
        }
        return (float) a / b;
    }
}

