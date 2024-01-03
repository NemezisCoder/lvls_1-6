import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneOffset;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        System.out.println(LetterPatternChecker.sameLetterPattern("ABAB", "CDCD")); // ➞ true
        System.out.println(LetterPatternChecker.sameLetterPattern("ABCBA", "BCDCB")); // ➞ true
        System.out.println(LetterPatternChecker.sameLetterPattern("FFGG", "CDCD")); // ➞ false
        System.out.println(LetterPatternChecker.sameLetterPattern("FFFF", "ABCD")); // ➞ false
        System.out.println();

        System.out.println(SpiderFlyPath.spiderVsFly("H3", "E2")); // ➞ "H3-H2-H1-A0-E1-E2"
        System.out.println(SpiderFlyPath.spiderVsFly("A4", "B2")); // ➞ "A4-A3-A2-B2"
        System.out.println(SpiderFlyPath.spiderVsFly("A4", "C2")); // ➞ "A4-A3-A2-B2-C2"
        System.out.println();

        System.out.println(NumberDigitsCounter.digitsCount(4666)); // ➞ 4
        System.out.println(NumberDigitsCounter.digitsCount(544)); // ➞ 3
        System.out.println(NumberDigitsCounter.digitsCount(121317)); // ➞ 6
        System.out.println(NumberDigitsCounter.digitsCount(0)); // ➞ 1
        System.out.println(NumberDigitsCounter.digitsCount(12345)); // ➞ 5
        System.out.println(NumberDigitsCounter.digitsCount(1289396387328L)); // ➞ 13
        System.out.println();

        System.out.println(WordGame.totalPoints(new String[]{"cat", "create", "sat"}, "caster")); // ➞ 2
        System.out.println(WordGame.totalPoints(new String[]{"trance", "recant"}, "recant")); // ➞ 108
        System.out.println(WordGame.totalPoints(new String[]{"dote", "dotes", "toes", "set", "dot", "dots", "sted"}, "tossed")); // ➞ 13
        System.out.println();

        System.out.println(SumPairs.sumsUp(new int[]{1, 2, 3, 4, 5})); // ➞ [[3, 5]]
        System.out.println(SumPairs.sumsUp(new int[]{1, 2, 3, 7, 9})); // ➞ [[1, 7]]
        System.out.println(SumPairs.sumsUp(new int[]{10, 9, 7, 2, 8})); // ➞ []
        System.out.println(SumPairs.sumsUp(new int[]{1, 6, 5, 4, 8, 2, 3, 7})); // ➞ [[2, 6], [3, 5], [1, 7]]
        System.out.println();

        System.out.println(TestScore.takeDownAverage(new String[]{"95%", "83%", "90%", "87%", "88%", "93%"})); // ➞ "54%"
        System.out.println(TestScore.takeDownAverage(new String[]{"10%"})); // ➞ "0%"
        System.out.println(TestScore.takeDownAverage(new String[]{"53%", "79%"})); // ➞ "51%"
        System.out.println();

        System.out.println(CaesarCipher.caesarCipher("encode", "hello world", 3)); // ➞ "KHOOR ZRUOG"
        System.out.println(CaesarCipher.caesarCipher("decode", "almost last task", 4)); // ➞ "HELLO WORLD"
        System.out.println();

        System.out.println(Combinatorics.setupSet(5, 3)); // ➞ 60
        System.out.println(Combinatorics.setupSet(7, 3)); // ➞ 210
        System.out.println();

        System.out.println(TimeDifferenceCalculator.timeDifference("Los Angeles", "April 1, 2011 23:23", "Canberra")); // ➞ "2011-4-2 17:23"
        System.out.println(TimeDifferenceCalculator.timeDifference("London", "July 31, 1983 23:01", "Rome")); // ➞ "1983-8-1 00:01"
        System.out.println(TimeDifferenceCalculator.timeDifference("New York", "December 31, 1970 13:40", "Beijing")); // ➞ "1971-1-1 02:40"
        System.out.println();

        System.out.println(NewNumberChecker.isNew(3)); // ➞ true
        System.out.println(NewNumberChecker.isNew(30)); // ➞ true
        System.out.println(NewNumberChecker.isNew(321)); // ➞ false
        System.out.println(NewNumberChecker.isNew(123)); // ➞ true
        System.out.println();
    }
}


class LetterPatternChecker {
    public static boolean sameLetterPattern(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }

        return transformString(str1).equals(transformString(str2));
    }

    private static String transformString(String str) {
        Map<Character, Integer> map = new HashMap<>();
        StringBuilder transformed = new StringBuilder();

        for (char ch : str.toCharArray()) {
            map.putIfAbsent(ch, map.size());
            transformed.append(map.get(ch));
        }

        return transformed.toString();
    }
}

class SpiderFlyPath {
    public static String spiderVsFly(String spider, String fly) {
        char spiderRadial = spider.charAt(0);
        int spiderRing = Character.getNumericValue(spider.charAt(1));
        char flyRadial = fly.charAt(0);
        int flyRing = Character.getNumericValue(fly.charAt(1));

        // Путь, который будет возвращен
        StringBuilder path = new StringBuilder();

        // Если паук и муха на одном радиале
        if (spiderRadial == flyRadial) {
            for (int i = spiderRing; i >= flyRing; i--) {
                path.append(spiderRadial).append(i);
                if (i != flyRing) {
                    path.append("-");
                }
            }
        } else {
            // Рассчитываем угловое расстояние между радиалами
            int angleDistance = Math.abs(spiderRadial - flyRadial);
            if (angleDistance > 8) {
                angleDistance = 16 - angleDistance; // Если идем через A0
            }

            // Если угловое расстояние больше 2, быстрее через центр
            if (angleDistance > 2 || spiderRing > flyRing + angleDistance) {
                // Двигаемся к центру
                for (int i = spiderRing; i > 0; i--) {
                    path.append(spiderRadial).append(i).append("-");
                }
                path.append("A0-");
                // Выходим на нужный радиал
                for (int i = 1; i <= flyRing; i++) {
                    path.append(flyRadial).append(i);
                    if (i != flyRing) {
                        path.append("-");
                    }
                }
            } else {
                // Паук идет напрямую к мухе
                if (spiderRing < flyRing) {
                    // Поднимаемся до уровня мухи
                    for (int i = spiderRing; i < flyRing; i++) {
                        path.append(spiderRadial).append(i).append("-");
                    }
                } else if (spiderRing > flyRing) {
                    // Спускаемся до уровня мухи
                    for (int i = spiderRing; i > flyRing; i--) {
                        path.append(spiderRadial).append(i).append("-");
                    }
                }
                // Двигаемся к мухе по кольцу
                if (spiderRadial < flyRadial) {
                    for (char r = spiderRadial; r != flyRadial; r = r == 'H' ? 'A' : (char) (r + 1)) {
                        path.append(r).append(flyRing).append("-");
                    }
                } else {
                    for (char r = spiderRadial; r != flyRadial; r = r == 'A' ? 'H' : (char) (r - 1)) {
                        path.append(r).append(flyRing).append("-");
                    }
                }
                path.append(fly);
            }
        }

        return path.toString();
    }
}

class NumberDigitsCounter {
    public static int digitsCount(long n) {
        if (n == 0) {
            return 1;
        }
        return n < 10 ? 1 : 1 + digitsCount(n / 10);
    }
}

class WordGame {
    public static int totalPoints(String[] guesses, String word) {
        int score = 0;
        for (String guess : guesses) {
            if (isWordValid(guess, word)) {
                score += scoreWord(guess);
            }
        }
        return score;
    }

    private static boolean isWordValid(String guess, String word) {
        if (guess.length() > word.length()) {
            return false;
        }
        int[] letters = new int[26];
        for (char c : word.toCharArray()) {
            letters[c - 'a']++;
        }
        for (char c : guess.toCharArray()) {
            if (--letters[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    private static int scoreWord(String word) {
        switch (word.length()) {
            case 3:
                return 1;
            case 4:
                return 2;
            case 5:
                return 3;
            case 6:
                return 54; // 4 points + 50 bonus points
            default:
                return 0;
        }
    }
}

class SumPairs {
    public static List<List<Integer>> sumsUp(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> result = new ArrayList<>();
        int left = 0, right = arr.length - 1;

        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == 8) {
                result.add(Arrays.asList(arr[left], arr[right]));
                left++;
                right--;
            } else if (sum < 8) {
                left++;
            } else {
                right--;
            }
        }

        return result;
    }
}

class TestScore {
    public static String takeDownAverage(String[] scores) {
        int sum = 0;
        for (String score : scores) {
            sum += Integer.parseInt(score.replace("%", ""));
        }
        double currentAverage = sum / (double) scores.length;
        double newAverage = currentAverage - 5;

        // Расчёт требуемой оценки
        int requiredScore = (int) Math.round(newAverage * (scores.length + 1) - sum);

        // Ограничение значения оценки 0 и 100 процентами
        requiredScore = Math.max(requiredScore, 0);
        requiredScore = Math.min(requiredScore, 100);

        return requiredScore + "%";
    }
}
class CaesarCipher {
    public static String caesarCipher(String mode, String message, int shift) {
        StringBuilder result = new StringBuilder();
        for (char character : message.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isUpperCase(character) ? 'A' : 'a';
                if (mode.equals("encode")) {
                    character = (char) ((character - base + shift) % 26 + base);
                } else if (mode.equals("decode")) {
                    character = (char) ((character - base - shift + 26) % 26 + base);
                }
            }
            result.append(character);
        }
        return result.toString().toUpperCase();
    }
}

class Combinatorics {

    public static int setupSet(int n, int k) {
        return factorial(n) / factorial(n - k);
    }

    private static int factorial(int number) {
        if (number <= 1) {
            return 1;
        }
        return number * factorial(number - 1);
    }
}

class TimeDifferenceCalculator {
    private static final Map<String, ZoneOffset> CITY_OFFSETS = new HashMap<>();

    static {
        CITY_OFFSETS.put("Los Angeles", ZoneOffset.ofHours(-8));
        CITY_OFFSETS.put("New York", ZoneOffset.ofHours(-5));
        CITY_OFFSETS.put("Caracas", ZoneOffset.ofHoursMinutes(-4, -30));
        CITY_OFFSETS.put("Buenos Aires", ZoneOffset.ofHours(-3));
        CITY_OFFSETS.put("London", ZoneOffset.ofHours(0));
        CITY_OFFSETS.put("Rome", ZoneOffset.ofHours(1));
        CITY_OFFSETS.put("Moscow", ZoneOffset.ofHours(3));
        CITY_OFFSETS.put("Tehran", ZoneOffset.ofHoursMinutes(3, 30));
        CITY_OFFSETS.put("New Delhi", ZoneOffset.ofHoursMinutes(5, 30));
        CITY_OFFSETS.put("Beijing", ZoneOffset.ofHours(8));
        CITY_OFFSETS.put("Canberra", ZoneOffset.ofHours(10));
    }

    public static String timeDifference(String cityA, String timestamp, String cityB) {
        LocalDateTime timeInCityA = parseToLocalDateTime(timestamp);
        ZoneOffset offsetA = CITY_OFFSETS.get(cityA);
        ZoneOffset offsetB = CITY_OFFSETS.get(cityB);

        LocalDateTime timeInCityB = timeInCityA
                .atOffset(offsetA)
                .withOffsetSameInstant(offsetB)
                .toLocalDateTime();

        return timeInCityB.format(DateTimeFormatter.ofPattern("yyyy-M-d HH:mm"));
    }
    private static LocalDateTime parseToLocalDateTime(String timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy HH:mm", Locale.ENGLISH);
        return LocalDateTime.parse(timestamp, formatter);
    }
}

class NewNumberChecker {
    public static boolean isNew(int number) {
        String numberStr = Integer.toString(number);
        int length = numberStr.length();

        for (int i = 1; i < length; i++) {
            if (numberStr.charAt(i) != '0' && numberStr.charAt(i) < numberStr.charAt(0)) {
                return false;
            }
        }

        for (int i = 1; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (numberStr.charAt(j) < numberStr.charAt(i)) {
                    return false;
                }
            }
        }

        return true;
    }
}