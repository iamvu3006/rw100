package frontend;

import backend.Exercise4;

public class Program4 {
    public static void main(String[] args) {
        Exercise4 exercise = new Exercise4();

        String text = "  hello   world  from   java ";
        System.out.println("Exercise4 Q1 word count: " + exercise.question1CountWords(text));
        System.out.println("Exercise4 Q2 concat: " + exercise.question2ConcatStrings("Hello ", "World"));
        System.out.println("Exercise4 Q3 capitalize: " + exercise.question3CapitalizeName("nam"));

        String[] chars = exercise.question4PrintCharacters("Nam");
        for (String line : chars) {
            System.out.println(line);
        }

        System.out.println("Exercise4 Q5 full name: " + exercise.question5CombineFullName("Nguyen", "Nam"));

        String[] parts = exercise.question6SplitFullName("Nguyen Van Nam");
        System.out.println("Exercise4 Q6 last name: " + parts[0]);
        System.out.println("Exercise4 Q6 middle name: " + parts[1]);
        System.out.println("Exercise4 Q6 first name: " + parts[2]);

        System.out.println("Exercise4 Q7 normalized: " + exercise.question7NormalizeFullName("   nguyen  van  nam   "));

        String[] groups = {"Java", "Java Core", "C# Group", "My Java Group", "Python"};
        for (String group : exercise.question8GroupsContainingJava(groups)) {
            System.out.println("Exercise4 Q8 group: " + group);
        }
        for (String group : exercise.question9GroupsExactlyJava(groups)) {
            System.out.println("Exercise4 Q9 group: " + group);
        }

        System.out.println("Exercise4 Q10 reverse check: " + (exercise.question10AreReverseStrings("word", "drow") ? "OK" : "KO"));
        System.out.println("Exercise4 Q11 count 'a': " + exercise.question11CountCharA("banana"));
        System.out.println("Exercise4 Q12 reverse: " + exercise.question12ReverseString("abcdef"));
        System.out.println("Exercise4 Q13 no digit: " + exercise.question13StringWithoutDigit("abc123"));
        System.out.println("Exercise4 Q14 replace: " + exercise.question14ReplaceChar("VTI Academy", 'e', '*'));
        System.out.println("Exercise4 Q15 reverse words: " + exercise.question15ReverseWords("    I am developer      "));

        String[] split = exercise.question16SplitByLength("123456", 2);
        if (split == null) {
            System.out.println("Exercise4 Q16: KO");
        } else {
            for (String part : split) {
                System.out.println("Exercise4 Q16 part: " + part);
            }
        }
    }
}

