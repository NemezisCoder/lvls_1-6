import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;
import java.util.Arrays;
import java.util.Map;
import java.math.BigInteger;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        System.out.println(AnagramFinder.hiddenAnagram("My world evolves in a beautiful space called Tesh.", "sworn love lived"));
        System.out.println(AnagramFinder.hiddenAnagram("An old west action hero actor", "Clint Eastwood"));
        System.out.println(AnagramFinder.hiddenAnagram("Mr. Mojo Rising could be a song title", "Jim Morrison"));
        System.out.println(AnagramFinder.hiddenAnagram("Banana? margaritas", "ANAGRAM"));
        System.out.println(AnagramFinder.hiddenAnagram("D  e b90it->?$ (c)a r...d,,#~", "bad credit"));
        System.out.println(AnagramFinder.hiddenAnagram("Bright is the moon", "Bongo mirth"));
        System.out.println();

        System.out.println(SliceCollector.collect("intercontinentalisationalism", 6));
        System.out.println(SliceCollector.collect("strengths", 3));
        System.out.println(SliceCollector.collect("pneumonoultramicroscopicsilicovolcanoconiosis", 15));
        System.out.println();

        System.out.println(NicoCipher.nicoCipher("myworldevolvesinhers", "tesh"));
        System.out.println(NicoCipher.nicoCipher("andiloveherso", "tesha"));
        System.out.println(NicoCipher.nicoCipher("mubashirhassan", "crazy"));
        System.out.println(NicoCipher.nicoCipher("edabitisamazing", "matt"));
        System.out.println(NicoCipher.nicoCipher("iloveher", "612345"));
        System.out.println();

        System.out.println(Arrays.toString(ArrayProduct.twoProduct(new int[]{1, 2, 3, 9, 4, 5, 15}, 45)));
        System.out.println(Arrays.toString(ArrayProduct.twoProduct(new int[]{1, 2, 3, 9, 4, 15, 3, 5}, 45)));
        System.out.println(Arrays.toString(ArrayProduct.twoProduct(new int[]{1, 2, -1, 4, 5, 6, 10, 7}, 20)));
        System.out.println(Arrays.toString(ArrayProduct.twoProduct(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 10)));
        System.out.println(Arrays.toString(ArrayProduct.twoProduct(new int[]{100, 12, 4, 1, 2}, 15)));
        System.out.println();

        System.out.println(Arrays.toString(FactorialChecker.isExact(6)));    // [6, 3]
        System.out.println(Arrays.toString(FactorialChecker.isExact(24)));   // [24, 4]
        System.out.println(Arrays.toString(FactorialChecker.isExact(125)));  // []
        System.out.println(Arrays.toString(FactorialChecker.isExact(720)));  // [720, 6]
        System.out.println(Arrays.toString(FactorialChecker.isExact(1024))); // []
        System.out.println(Arrays.toString(FactorialChecker.isExact(40320)));// [40320, 8]
        System.out.println();

        System.out.println(DecimalToFraction.fractions("0.(6)"));       // "2/3"
        System.out.println(DecimalToFraction.fractions("1.(1)"));       // "10/9"
        System.out.println(DecimalToFraction.fractions("3.(142857)"));  // "22/7"
        System.out.println(DecimalToFraction.fractions("0.19(2367)"));  // "5343/27775"
        System.out.println(DecimalToFraction.fractions("0.1097(3)"));   // "823/7500"
        System.out.println();

        System.out.println(PilishString.pilish_string("33314444"));  // "333 1 4444"
        System.out.println(PilishString.pilish_string("TOP"));       // "TOP"
        System.out.println(PilishString.pilish_string("X"));        // "XXX"
        System.out.println(PilishString.pilish_string(""));         // ""
        System.out.println();

        System.out.println(ExpressionCalculator.generateNonconsecutive("3 + 5 * (2 - 6)")); // 3 + 5 * -4 = 3 - 20 = -17
        System.out.println(ExpressionCalculator.generateNonconsecutive("6 - 18 / (4 - 1)")); // 6 - 18 / 2 = 6 - 9 = 0
        System.out.println();

        System.out.println(SherlockStringValidator.isValid("aabbcd")); // NO
        System.out.println(SherlockStringValidator.isValid("aabbccddeefghi")); // NO
        System.out.println(SherlockStringValidator.isValid("abcdefghhgfedecba")); // YES
        System.out.println();

        System.out.println(LCS.findLCS("abcd", "bd")); // "bd"
        System.out.println(LCS.findLCS("aggtab", "gxtxamb")); // "gtab"
        System.out.println();
    }
}


class AnagramFinder {
    public static String hiddenAnagram(String text, String phrase) {
        String cleanedText = text.replaceAll("[^a-zA-Z]", "").toLowerCase();
        String cleanedPhrase = phrase.replaceAll("[^a-zA-Z]", "").toLowerCase();

        if (cleanedPhrase.length() > cleanedText.length()) {
            return "notfound";
        }

        HashMap<Character, Integer> phraseMap = new HashMap<>();
        for (char c : cleanedPhrase.toCharArray()) {
            phraseMap.put(c, phraseMap.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i <= cleanedText.length() - cleanedPhrase.length(); i++) {
            if (isAnagram(cleanedText.substring(i, i + cleanedPhrase.length()), new HashMap<>(phraseMap))) {
                return cleanedText.substring(i, i + cleanedPhrase.length());
            }
        }

        return "notfound";
    }

    private static boolean isAnagram(String subText, HashMap<Character, Integer> phraseMap) {
        for (char c : subText.toCharArray()) {
            if (!phraseMap.containsKey(c) || phraseMap.get(c) == 0) {
                return false;
            }
            phraseMap.put(c, phraseMap.get(c) - 1);
        }
        return true;
    }
}

class SliceCollector {

    public static List<String> collect(String str, int n) {
        List<String> result = new ArrayList<>();
        for (int start = 0; start + n <= str.length(); start += n) {
            result.add(str.substring(start, start + n));
        }
        Collections.sort(result);
        return result;
    }
}

class NicoCipher {
    public static String nicoCipher(String message, String key) {
        int messageLength = message.length();
        int keyLength = key.length();
        int numRows = (int) Math.ceil((double) messageLength / keyLength);

        // Создаем и заполняем матрицу символов
        char[][] grid = new char[numRows][keyLength];
        for (int i = 0; i < messageLength; i++) {
            grid[i / keyLength][i % keyLength] = message.charAt(i);
        }

        // Заполняем оставшееся пространство в матрице пробелами
        for (int i = messageLength; i < numRows * keyLength; i++) {
            grid[i / keyLength][i % keyLength] = ' ';
        }

        // Определяем порядок столбцов
        Integer[] order = getOrder(key);

        // Строим закодированное сообщение
        StringBuilder encodedMessage = new StringBuilder();
        for (int row = 0; row < numRows; row++) {
            for (int column : order) {
                encodedMessage.append(grid[row][column]);
            }
        }

        return encodedMessage.toString();
    }

    private static Integer[] getOrder(String key) {
        Integer[] order = new Integer[key.length()];
        for (int i = 0; i < key.length(); i++) {
            order[i] = i;
        }
        Character[] chars = key.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
        Arrays.sort(order, Comparator.comparing(c -> chars[c]));
        return order;
    }
}

class ArrayProduct {
    public static int[] twoProduct(int[] arr, int n) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0 && n == 0) {
                return new int[]{0, 0}; // Специальный случай, если n равно 0
            }

            if (arr[i] != 0 && n % arr[i] == 0) {
                int complement = n / arr[i];
                if (map.containsKey(complement)) {
                    return new int[]{complement, arr[i]};
                }
            }
            map.put(arr[i], i);
        }
        return new int[]{}; // Возвращает пустой массив, если пара не найдена
    }
}

class FactorialChecker {

    public static int[] isExact(int number) {
        return isExactHelper(number, 1, 1);
    }

    private static int[] isExactHelper(int number, int n, int factorial) {
        if (factorial == number) {
            return new int[]{number, n};
        } else if (factorial > number) {
            return new int[]{};
        }
        return isExactHelper(number, n + 1, factorial * (n + 1));
    }
}

class DecimalToFraction {
    public static String fractions(String decimal) {
        if (!decimal.contains("(")) {
            return decimalToFraction(decimal);
        }

        int indexOfOpeningParenthesis = decimal.indexOf('(');
        String nonRepeatingPart = decimal.substring(0, indexOfOpeningParenthesis);
        String repeatingPart = decimal.substring(indexOfOpeningParenthesis + 1, decimal.indexOf(')'));

        BigInteger wholePart = new BigInteger(nonRepeatingPart.replace(".", ""));
        int nonRepeatingDecimalPlaces = nonRepeatingPart.length() - nonRepeatingPart.indexOf('.') - 1;
        BigInteger repeatingPartBigInt = new BigInteger(repeatingPart);
        BigInteger repeatingBase = BigInteger.TEN.pow(repeatingPart.length()).subtract(BigInteger.ONE);

        BigInteger numerator = wholePart.multiply(repeatingBase)
                .add(repeatingPartBigInt);
        BigInteger denominator = repeatingBase.multiply(BigInteger.TEN.pow(nonRepeatingDecimalPlaces));

        BigInteger gcd = numerator.gcd(denominator);

        return numerator.divide(gcd) + "/" + denominator.divide(gcd);
    }

    private static String decimalToFraction(String decimal) {
        int decimalPlaces = decimal.length() - decimal.indexOf('.') - 1;
        BigInteger denominator = BigInteger.TEN.pow(decimalPlaces);
        BigInteger numerator = new BigInteger(decimal.replace(".", ""));

        BigInteger gcd = numerator.gcd(denominator);
        return numerator.divide(gcd) + "/" + denominator.divide(gcd);
    }
}

class PilishString {

    public static String pilish_string(String txt) {
        int[] piDigits = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5, 8, 9, 7, 9};
        StringBuilder result = new StringBuilder();
        int txtIndex = 0;

        for (int digit : piDigits) {
            if (txtIndex >= txt.length()) {
                break;
            }

            if (txtIndex + digit > txt.length()) {
                char lastChar = txt.charAt(txt.length() - 1);
                result.append(txt.substring(txtIndex)).append(String.valueOf(lastChar).repeat(digit - (txt.length() - txtIndex)));
                break;
            }

            result.append(txt, txtIndex, txtIndex + digit).append(" ");
            txtIndex += digit;
        }

        return "\"" + result.toString().trim() + "\"";
    }
}
class ExpressionCalculator {
    public static int generateNonconsecutive(String expression) {
        try {
            Stack<Integer> numbers = new Stack<>();
            Stack<Character> operators = new Stack<>();

            for (int i = 0; i < expression.length(); i++) {
                char c = expression.charAt(i);

                if (c == ' ') {
                    continue;
                }

                if (c >= '0' && c <= '9') {
                    StringBuilder sb = new StringBuilder();
                    while (i < expression.length() && expression.charAt(i) >= '0' && expression.charAt(i) <= '9') {
                        sb.append(expression.charAt(i++));
                    }
                    i--;
                    numbers.push(Integer.parseInt(sb.toString()));
                } else if (c == '(') {
                    operators.push(c);
                } else if (c == ')') {
                    while (operators.peek() != '(') {
                        numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.pop()));
                    }
                    operators.pop();
                } else if (isOperator(c)) {
                    while (!operators.empty() && precedence(c) <= precedence(operators.peek())) {
                        numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.pop()));
                    }
                    operators.push(c);
                }
            }

            while (!operators.empty()) {
                numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.pop()));
            }

            return numbers.pop();
        } catch (Exception e) {
            e.printStackTrace();
            return Integer.MIN_VALUE;
        }
    }

    private static int applyOperation(char op, int b, int a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new UnsupportedOperationException("Cannot divide by zero");
                }
                return a / b;
        }
        return 0;
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static int precedence(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        return -1;
    }
}

class SherlockStringValidator {

    public static String isValid(String s) {
        Map<Character, Integer> charFrequency = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            charFrequency.put(c, charFrequency.getOrDefault(c, 0) + 1);
        }

        Map<Integer, Integer> frequencyOfFrequencies = new HashMap<>();
        for (int freq : charFrequency.values()) {
            frequencyOfFrequencies.put(freq, frequencyOfFrequencies.getOrDefault(freq, 0) + 1);
        }

        if (frequencyOfFrequencies.size() == 1) {
            return "YES";
        } else if (frequencyOfFrequencies.size() == 2) {
            int minFreq = Integer.MAX_VALUE;
            int maxFreq = Integer.MIN_VALUE;
            for (int freq : frequencyOfFrequencies.keySet()) {
                if (freq < minFreq) {
                    minFreq = freq;
                }
                if (freq > maxFreq) {
                    maxFreq = freq;
                }
            }

            if ((frequencyOfFrequencies.get(minFreq) == 1 && (minFreq == 1 || minFreq - 1 == maxFreq)) ||
                    (frequencyOfFrequencies.get(maxFreq) == 1 && maxFreq - minFreq == 1)) {
                return "YES";
            }
        }

        return "NO";
    }
}

class LCS {

    // Функция для нахождения наибольшей общей подпоследовательности
    public static String findLCS(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        // Построение таблицы dp в порядке нисходящего динамического программирования
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0)
                    dp[i][j] = 0;
                else if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        // Восстановление LCS из таблицы dp
        int index = dp[m][n];
        char[] lcs = new char[index];
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                lcs[index - 1] = s1.charAt(i - 1);
                i--;
                j--;
                index--;
            } else if (dp[i - 1][j] > dp[i][j - 1])
                i--;
            else
                j--;
        }

        return new String(lcs);
    }
}