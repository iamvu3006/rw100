package backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Exercise4 {
    public int question1CountWords(String input) {
        String normalized = normalizeSpaces(input);
        if (normalized.isEmpty()) {
            return 0;
        }
        return normalized.split(" ").length;
    }

    public String question2ConcatStrings(String s1, String s2) {
        String left = s1 == null ? "" : s1;
        String right = s2 == null ? "" : s2;
        return left + right;
    }

    public String question3CapitalizeName(String name) {
        String normalized = normalizeSpaces(name);
        if (normalized.isEmpty()) {
            return normalized;
        }
        return Character.toUpperCase(normalized.charAt(0)) + normalized.substring(1);
    }

    public String[] question4PrintCharacters(String name) {
        String normalized = normalizeSpaces(name);
        String[] lines = new String[normalized.length()];
        for (int i = 0; i < normalized.length(); i++) {
            lines[i] = "Ký tự thứ " + (i + 1) + " là: " + normalized.charAt(i);
        }
        return lines;
    }

    public String question5CombineFullName(String lastName, String firstName) {
        String left = normalizeSpaces(lastName);
        String right = normalizeSpaces(firstName);
        if (left.isEmpty()) {
            return right;
        }
        if (right.isEmpty()) {
            return left;
        }
        return left + " " + right;
    }

    public String[] question6SplitFullName(String fullName) {
        String normalized = normalizeSpaces(fullName);
        if (normalized.isEmpty()) {
            return new String[]{"", "", ""};
        }
        String[] parts = normalized.split(" ");
        String lastName = parts[0];
        String firstName = parts[parts.length - 1];
        String middleName = parts.length > 2 ? joinRange(parts, 1, parts.length - 1) : "";
        return new String[]{lastName, middleName, firstName};
    }

    public String question7NormalizeFullName(String fullName) {
        String normalized = normalizeSpaces(fullName).toLowerCase(Locale.US);
        if (normalized.isEmpty()) {
            return normalized;
        }
        String[] parts = normalized.split(" ");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            if (!part.isEmpty()) {
                builder.append(Character.toUpperCase(part.charAt(0)));
                if (part.length() > 1) {
                    builder.append(part.substring(1));
                }
            }
            if (i < parts.length - 1) {
                builder.append(' ');
            }
        }
        return builder.toString();
    }

    public String[] question8GroupsContainingJava(String[] groups) {
        return filterGroups(groups, false);
    }

    public String[] question9GroupsExactlyJava(String[] groups) {
        return filterGroups(groups, true);
    }

    public boolean question10AreReverseStrings(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return false;
        }
        return reverseString(s1).equals(s2);
    }

    public int question11CountCharA(String input) {
        if (input == null) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'a') {
                count++;
            }
        }
        return count;
    }

    public String question12ReverseString(String input) {
        return reverseString(input);
    }

    public boolean question13StringWithoutDigit(String input) {
        if (input == null) {
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public String question14ReplaceChar(String str, char target, char replacement) {
        if (str == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            builder.append(c == target ? replacement : c);
        }
        return builder.toString();
    }

    public String question15ReverseWords(String input) {
        String normalized = normalizeSpaces(input);
        if (normalized.isEmpty()) {
            return normalized;
        }
        List<String> words = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        for (int i = 0; i < normalized.length(); i++) {
            char c = normalized.charAt(i);
            if (c == ' ') {
                if (current.length() > 0) {
                    words.add(current.toString());
                    current.setLength(0);
                }
            } else {
                current.append(c);
            }
        }
        if (current.length() > 0) {
            words.add(current.toString());
        }
        StringBuilder result = new StringBuilder();
        for (int i = words.size() - 1; i >= 0; i--) {
            result.append(words.get(i));
            if (i > 0) {
                result.append(' ');
            }
        }
        return result.toString();
    }

    public String[] question16SplitByLength(String str, int n) {
        if (str == null || n <= 0 || str.length() % n != 0) {
            return null;
        }
        int parts = str.length() / n;
        String[] result = new String[parts];
        for (int i = 0; i < parts; i++) {
            int start = i * n;
            result[i] = str.substring(start, start + n);
        }
        return result;
    }

    private String normalizeSpaces(String input) {
        if (input == null) {
            return "";
        }
        String trimmed = input.trim();
        StringBuilder builder = new StringBuilder();
        boolean inSpace = false;
        for (int i = 0; i < trimmed.length(); i++) {
            char c = trimmed.charAt(i);
            if (Character.isWhitespace(c)) {
                if (!inSpace) {
                    builder.append(' ');
                    inSpace = true;
                }
            } else {
                builder.append(c);
                inSpace = false;
            }
        }
        return builder.toString();
    }

    private String reverseString(String input) {
        if (input == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder(input.length());
        for (int i = input.length() - 1; i >= 0; i--) {
            builder.append(input.charAt(i));
        }
        return builder.toString();
    }

    private String[] filterGroups(String[] groups, boolean exactMatch) {
        if (groups == null) {
            return new String[0];
        }
        List<String> result = new ArrayList<>();
        for (String group : groups) {
            if (group == null) {
                continue;
            }
            boolean matches = exactMatch ? group.equals("Java") : group.contains("Java");
            if (matches) {
                result.add(group);
            }
        }
        return result.toArray(new String[0]);
    }

    private String joinRange(String[] parts, int start, int end) {
        StringBuilder builder = new StringBuilder();
        for (int i = start; i < end; i++) {
            builder.append(parts[i]);
            if (i < end - 1) {
                builder.append(' ');
            }
        }
        return builder.toString();
    }
}
