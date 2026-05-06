package backend;

import java.util.Locale;

public class Exercise3 {
    public String question1ConvertSalary() {
        Integer salary = 5000;
        float salaryFloat = salary.floatValue();
        return String.format(Locale.US, "%.2f", salaryFloat);
    }

    public int question2StringToInt() {
        String value = "1234567";
        return Integer.parseInt(value);
    }

    public int question3IntegerToInt() {
        Integer value = Integer.valueOf("1234567");
        return value.intValue();
    }
}

