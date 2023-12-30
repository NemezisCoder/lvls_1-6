import java.util.HashMap;
import java.util.Arrays;
import java.security.SecureRandom;

public class Main {
    public static void main(String[] args) {
        System.out.println(StringDuplicateChecker.duplicateChars("Donald")); // Должно вывести true
        System.out.println(StringDuplicateChecker.duplicateChars("orange")); // Должно вывести false
        System.out.println();

        System.out.println(InitialsExtractor.getInitials("Ryan Gosling")); // Выведет "RG"
        System.out.println(InitialsExtractor.getInitials("Barack Obama")); // Выведет "BO"
        System.out.println();


        System.out.println(DifferenceCalculator.differenceEvenOdd(new int[]{44, 32, 86, 19})); // Выведет 143
        System.out.println(DifferenceCalculator.differenceEvenOdd(new int[]{22, 50, 16, 63, 31, 55})); // Выведет 61
        System.out.println();

        System.out.println(ArrayChecker.equalToAvg(new int[]{1, 2, 3, 4, 5})); // Выведет true
        System.out.println(ArrayChecker.equalToAvg(new int[]{1, 2, 3, 4, 6})); // Выведет false
        System.out.println();


        int[] result1 = IndexMultiplier.indexMult(new int[]{1, 2, 3});
        System.out.println(java.util.Arrays.toString(result1)); // Выведет [0, 2, 6]

        int[] result2 = IndexMultiplier.indexMult(new int[]{3, -2, 408, 3, 31});
        System.out.println(java.util.Arrays.toString(result2)); // Выведет [0, -2, 816, 9, 124]
        System.out.println();

        System.out.println(StringReverser.reverse("Hello World")); // Выведет "dlroW olleH"
        System.out.println(StringReverser.reverse("The quick brown fox.")); // Выведет ".xof nworb kciuq ehT"
        System.out.println();

        System.out.println(Tribonacci.tribonacci(7)); // Выведет 7
        System.out.println(Tribonacci.tribonacci(11)); // Выведет 81
        System.out.println();

        System.out.println(HashGenerator.pseudoHash(5)); // Выведет строку из 5 символов, например "04bf2"
        System.out.println(HashGenerator.pseudoHash(10)); // Выведет строку из 10 символов, например "2d9c45e1f3"
        System.out.println(HashGenerator.pseudoHash(0)); // Выведет пустую строку ""
        System.out.println();

        System.out.println(BotHelper.botHelper("Hello, I’m under the water, please help me")); // Выведет "Calling for a staff member"
        System.out.println(BotHelper.botHelper("Two pepperoni pizzas please")); // Выведет "Keep waiting"
        System.out.println();

        System.out.println(AnagramChecker.isAnagram("listen", "silent")); // Выведет true
        System.out.println(AnagramChecker.isAnagram("eleven plus two", "twelve plus one")); // Выведет true
        System.out.println(AnagramChecker.isAnagram("hello", "world")); // Выведет false
        System.out.println();
    }
}

class StringDuplicateChecker {

    // Функция для определения наличия дубликатов символов в строке
    public static boolean duplicateChars(String str) {
        HashMap<Character, Integer> charCountMap = new HashMap<>();

        // Проходим по всем символам строки и заполняем карту подсчета символов
        for (char c : str.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }

        // Проверяем карту на наличие символов с количеством больше 1
        for (int count : charCountMap.values()) {
            if (count > 1) {
                return true; // Найден дубликат
            }
        }

        return false; // Дубликатов не найдено
    }
}

class InitialsExtractor {

    // Метод для получения инициалов из полного имени
    public static String getInitials(String fullName) {
        String[] parts = fullName.split("\\s+");
        StringBuilder initials = new StringBuilder();

        for (String part : parts) {
            if (!part.isEmpty()) {
                initials.append(part.charAt(0));
            }
        }

        return initials.toString().toUpperCase();
    }
}

class DifferenceCalculator {

    // Функция для расчета разницы между суммой четных и нечетных чисел
    public static int differenceEvenOdd(int[] numbers) {
        int sumEven = 0;
        int sumOdd = 0;

        // Перебираем числа и суммируем четные и нечетные отдельно
        for (int number : numbers) {
            if (number % 2 == 0) {
                sumEven += number;
            } else {
                sumOdd += number;
            }
        }

        // Возвращаем абсолютное значение разницы
        return Math.abs(sumEven - sumOdd);
    }
}

class ArrayChecker {

    // Функция для проверки, есть ли в массиве элемент равный среднему значению всех элементов
    public static boolean equalToAvg(int[] numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        double average = (double) sum / numbers.length;

        for (int number : numbers) {
            if (number == average) {
                return true;
            }
        }
        return false;
    }
}

class IndexMultiplier {

    // Метод, который умножает каждый элемент массива на его индекс
    public static int[] indexMult(int[] numbers) {
        int[] result = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            result[i] = numbers[i] * i;
        }

        return result;
    }
}

class StringReverser {

    // Метод для переворачивания строки
    public static String reverse(String input) {
        return new StringBuilder(input).reverse().toString();
    }
}

class Tribonacci {

    // Функция для вычисления n-го числа в последовательности Трибоначчи
    public static int tribonacci(int n) {
        if (n == 0 || n == 1) {
            return 0;
        } else if (n == 2) {
            return 1;
        } else {
            int a = 0, b = 0, c = 1, d = 0;
            for (int i = 3; i < n; i++) { // Изменено на i < n
                d = a + b + c;
                a = b;
                b = c;
                c = d;
            }
            return d; // Возвращаем d, которое представляет последнее вычисленное значение
        }
    }
}

class HashGenerator {

    // Функция для генерации псевдо-хеша заданной длины
    public static String pseudoHash(int length) {
        SecureRandom random = new SecureRandom();
        if (length <= 0) {
            return "";
        }

        // Символы, используемые в хеше
        String characters = "0123456789abcdef";
        StringBuilder hash = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            hash.append(characters.charAt(index));
        }

        return hash.toString();
    }
}

class BotHelper {

    // Функция, определяющая наличие слова "help" и возвращающая соответствующий ответ
    public static String botHelper(String message) {
        // Приведение строки к нижнему регистру для поиска без учета регистра
        String lowerCaseMessage = message.toLowerCase();

        // Поиск слова "help" в строке
        if (lowerCaseMessage.contains("help")) {
            return "Calling for a staff member";
        } else {
            return "Keep waiting";
        }
    }
}

class AnagramChecker {

    // Функция для проверки, являются ли две строки анаграммами
    public static boolean isAnagram(String firstStr, String secondStr) {
        // Удаление всех пробелов и приведение строк к нижнему регистру
        String normalizedFirst = firstStr.replaceAll("\\s", "").toLowerCase();
        String normalizedSecond = secondStr.replaceAll("\\s", "").toLowerCase();

        // Преобразование строк в массивы символов
        char[] firstArray = normalizedFirst.toCharArray();
        char[] secondArray = normalizedSecond.toCharArray();

        // Сортировка массивов символов
        Arrays.sort(firstArray);
        Arrays.sort(secondArray);

        // Сравнение отсортированных массивов
        return Arrays.equals(firstArray, secondArray);
    }
}

