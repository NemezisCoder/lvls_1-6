//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        System.out.println(StringModifier.nonRepeatable("abracadabra")); // ➞ "abrcd"
        System.out.println(StringModifier.nonRepeatable("paparazzi")); // ➞ "parzi"

        System.out.println(BracketsGenerator.generateBrackets(1)); // ➞ ["()"]
        System.out.println(BracketsGenerator.generateBrackets(2)); // ➞ ["(())", "()()"]
        System.out.println(BracketsGenerator.generateBrackets(3)); // ➞ ["((()))", "(()())", "(())()", "()(())", "()()()"]
        System.out.println();

        System.out.println(BinarySystem.binarySystem(3)); // ➞ ["010", "011", "101", "110", "111"]
        System.out.println(BinarySystem.binarySystem(4)); // ➞ ["0101", "0110", "0111", "1010", "1011", "1101", "1110", "1111"]
        System.out.println();

        System.out.println(LongestAlphabeticRow.alphabeticRow("abcdjuwx")); // ➞ "abcd"
        System.out.println(LongestAlphabeticRow.alphabeticRow("klmabzyxw")); // ➞ "zyxw"
        System.out.println();

        System.out.println(StringCompressor.compressString("aaabbcdd")); // ➞ "c1b2d2a3"
        System.out.println(StringCompressor.compressString("vvvvaajaaaaa")); // ➞ "j1a2v4a5"
        System.out.println();

        System.out.println(NumberConverter.convertToNum("eight")); // ➞ 8
        System.out.println(NumberConverter.convertToNum("five hundred sixty seven")); // ➞ 567
        System.out.println(NumberConverter.convertToNum("thirty one")); // ➞ 31
        System.out.println();

        System.out.println(UniqueSubstring.uniqueSubstring("123412324")); // ➞ "1234"
        System.out.println(UniqueSubstring.uniqueSubstring("111111")); // ➞ "1"
        System.out.println(UniqueSubstring.uniqueSubstring("77897898")); // ➞ "789"
        System.out.println();

        int[][] matrix1 = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        System.out.println(MatrixPathFinder.shortestWay(matrix1)); // ➞ 7

        int[][] matrix2 = {
                {2, 7, 3},
                {1, 4, 8},
                {4, 5, 9}
        };
        System.out.println(MatrixPathFinder.shortestWay(matrix2)); // ➞ 21
        System.out.println();

        System.out.println(StringReorder.numericOrder("t3o the5m 1One all6 r4ule ri2ng")); // ➞ " One ring to rule them all"
        System.out.println(StringReorder.numericOrder("re6sponsibility Wit1h gr5eat power3 4comes g2reat")); // ➞ " With great power comes great responsibility"
        System.out.println();

        System.out.println(NumberSwitcher.switchNums(519, 723)); // ➞ 953
        System.out.println(NumberSwitcher.switchNums(491, 3912)); // ➞ 9942
        System.out.println(NumberSwitcher.switchNums(6274, 71259)); // ➞ 77659
        System.out.println();


    }
}


class StringModifier {
    public static String nonRepeatable(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        char firstChar = s.charAt(0);
        String remaining = s.substring(1);

        // Удаляем все вхождения firstChar из remaining
        remaining = remaining.replace(String.valueOf(firstChar), "");

        // Рекурсивно обрабатываем оставшуюся часть строки
        return firstChar + nonRepeatable(remaining);
    }
}

class BracketsGenerator {
    public static List<String> generateBrackets(int n) {
        List<String> result = new ArrayList<>();
        generateCombinations(result, "", 0, 0, n);
        return result;
    }

    private static void generateCombinations(List<String> result, String current, int open, int close, int max) {
        if (current.length() == max * 2) {
            result.add(current);
            return;
        }

        if (open < max) {
            generateCombinations(result, current + "(", open + 1, close, max);
        }
        if (close < open) {
            generateCombinations(result, current + ")", open, close + 1, max);
        }
    }
}

class BinarySystem {
    public static List<String> binarySystem(int n) {
        List<String> result = new ArrayList<>();
        generateCombinations(result, "", n, true);
        return result;
    }

    private static void generateCombinations(List<String> result, String current, int remaining, boolean canUseZero) {
        if (remaining == 0) {
            result.add(current);
            return;
        }

        // Добавляем '1' и продолжаем рекурсию
        generateCombinations(result, current + "1", remaining - 1, true);

        // Добавляем '0' только если перед этим была '1'
        if (canUseZero) {
            generateCombinations(result, current + "0", remaining - 1, false);
        }
    }
}

class LongestAlphabeticRow {
    public static String alphabeticRow(String s) {
        String longest = "";
        String currentIncreasing = "";
        String currentDecreasing = "";

        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);

            // Обработка увеличивающейся последовательности
            if (i == 0 || currentChar == currentIncreasing.charAt(currentIncreasing.length() - 1) + 1) {
                currentIncreasing += currentChar;
            } else {
                currentIncreasing = "" + currentChar;
            }

            // Обработка уменьшающейся последовательности
            if (i == 0 || currentChar == currentDecreasing.charAt(currentDecreasing.length() - 1) - 1) {
                currentDecreasing += currentChar;
            } else {
                currentDecreasing = "" + currentChar;
            }

            // Проверяем, является ли текущая последовательность самой длинной
            if (currentIncreasing.length() > longest.length()) {
                longest = currentIncreasing;
            }
            if (currentDecreasing.length() > longest.length()) {
                longest = currentDecreasing;
            }
        }

        return longest;
    }
}

class StringCompressor {
    public static String compressString(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }

        StringBuilder compressed = new StringBuilder();
        int count = 1;
        char prev = str.charAt(0);

        for (int i = 1; i < str.length(); i++) {
            char current = str.charAt(i);
            if (current == prev) {
                count++;
            } else {
                compressed.append(prev).append(count);
                count = 1;
                prev = current;
            }
        }
        compressed.append(prev).append(count);

        List<String> parts = new ArrayList<>();
        for (int i = 0; i < compressed.length(); i += 2) {
            parts.add(compressed.substring(i, i + 2));
        }

        Collections.sort(parts, (a, b) -> Integer.compare(a.charAt(1), b.charAt(1)));

        return String.join("", parts);
    }
}

class NumberConverter {

    private static final Map<String, Integer> singleDigits = new HashMap<>();
    private static final Map<String, Integer> teens = new HashMap<>();
    private static final Map<String, Integer> tens = new HashMap<>();

    static {
        singleDigits.put("zero", 0);
        singleDigits.put("one", 1);
        singleDigits.put("two", 2);
        singleDigits.put("three", 3);
        singleDigits.put("four", 4);
        singleDigits.put("five", 5);
        singleDigits.put("six", 6);
        singleDigits.put("seven", 7);
        singleDigits.put("eight", 8);
        singleDigits.put("nine", 9);

        teens.put("ten", 10);
        teens.put("eleven", 11);
        teens.put("twelve", 12);
        teens.put("thirteen", 13);
        teens.put("fourteen", 14);
        teens.put("fifteen", 15);
        teens.put("sixteen", 16);
        teens.put("seventeen", 17);
        teens.put("eighteen", 18);
        teens.put("nineteen", 19);

        tens.put("twenty", 20);
        tens.put("thirty", 30);
        tens.put("forty", 40);
        tens.put("fifty", 50);
        tens.put("sixty", 60);
        tens.put("seventy", 70);
        tens.put("eighty", 80);
        tens.put("ninety", 90);
    }
    public static int convertToNum(String str) {
        String[] words = str.split("\\s+");
        int result = 0;
        int currentNumber = 0;

        for (String word : words) {
            if (singleDigits.containsKey(word)) {
                currentNumber += singleDigits.get(word);
            } else if (teens.containsKey(word)) {
                currentNumber += teens.get(word);
            } else if (tens.containsKey(word)) {
                currentNumber += tens.get(word);
            } else if (word.equals("hundred")) {
                currentNumber *= 100;
            } else {
                result += currentNumber;
                currentNumber = 0;
            }
        }

        result += currentNumber;
        return result;
    }
}

class UniqueSubstring {
    public static String uniqueSubstring(String s) {
        Set<Character> characters = new HashSet<>();
        String longestSubstring = "";
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // Проверяем, содержится ли текущий символ в наборе
            while (characters.contains(currentChar)) {
                characters.remove(s.charAt(left));
                left++;
            }

            // Добавляем текущий символ в набор
            characters.add(currentChar);

            // Обновляем longestSubstring, если текущая подстрока длиннее
            if (right - left + 1 > longestSubstring.length()) {
                longestSubstring = s.substring(left, right + 1);
            }
        }

        return longestSubstring;
    }
}

class MatrixPathFinder {
    public static int shortestWay(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];

        dp[0][0] = matrix[0][0];

        // Заполнение первой строки
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + matrix[0][i];
        }

        // Заполнение первого столбца
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + matrix[i][0];
        }

        // Заполнение оставшейся части матрицы
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + matrix[i][j];
            }
        }

        return dp[n - 1][n - 1];
    }
}

class StringReorder {
    public static String numericOrder(String str) {
        String[] words = str.split("\\s+");
        String[] ordered = new String[words.length];

        Pattern p = Pattern.compile("\\d+");

        for (String word : words) {
            Matcher m = p.matcher(word);
            if (m.find()) {
                int index = Integer.parseInt(m.group()) - 1;
                ordered[index] = word.replaceAll("\\d", "");
            }
        }

        return String.join(" ", ordered);
    }
}
    class NumberSwitcher {
        public static int switchNums(int num1, int num2) {
            // Подсчет количества каждой цифры в num1
            Map<Character, Integer> countMap = new HashMap<>();
            for (char c : String.valueOf(num1).toCharArray()) {
                countMap.put(c, countMap.getOrDefault(c, 0) + 1);
            }

            char[] num2Chars = String.valueOf(num2).toCharArray();

            for (int i = 0; i < num2Chars.length; i++) {
                for (char c = '9'; c > num2Chars[i]; c--) {
                    if (countMap.getOrDefault(c, 0) > 0) {
                        // Замена цифры и обновление карты
                        num2Chars[i] = c;
                        countMap.put(c, countMap.get(c) - 1);
                        break;
                    }
                }
            }

            return Integer.parseInt(new String(num2Chars));
        }
}
