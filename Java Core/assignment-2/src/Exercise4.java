import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class Exercise4 {
    private static final Random RANDOM = new Random();
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static int question1RandomInt() {
        return RANDOM.nextInt(1000);
    }

    public static double question2RandomDouble() {
        return RANDOM.nextDouble();
    }

    public static String question3RandomName(String[] names) {
        if (names == null || names.length == 0) {
            return "";
        }
        int index = RANDOM.nextInt(names.length);
        return names[index];
    }

    public static LocalDate question4RandomDateInRange() {
        LocalDate start = LocalDate.of(1995, 7, 24);
        LocalDate end = LocalDate.of(1995, 12, 20);
        return randomDateBetween(start, end);
    }

    public static LocalDate question5RandomDateLastYear() {
        LocalDate end = LocalDate.now();
        LocalDate start = end.minusYears(1);
        return randomDateBetween(start, end);
    }

    public static LocalDate question6RandomPastDate() {
        LocalDate end = LocalDate.now().minusDays(1);
        LocalDate start = LocalDate.of(1970, 1, 1);
        if (end.isBefore(start)) {
            return start;
        }
        return randomDateBetween(start, end);
    }

    public static int question7RandomThreeDigitNumber() {
        return RANDOM.nextInt(900) + 100;
    }

    public static String formatDate(LocalDate date) {
        if (date == null) {
            return "";
        }
        return DATE_FORMAT.format(date);
    }

    private static LocalDate randomDateBetween(LocalDate start, LocalDate end) {
        long daysBetween = ChronoUnit.DAYS.between(start, end);
        if (daysBetween < 0) {
            return start;
        }
        int offset = RANDOM.nextInt((int) daysBetween + 1);
        return start.plusDays(offset);
    }
}

