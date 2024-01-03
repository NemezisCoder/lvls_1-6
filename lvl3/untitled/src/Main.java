import java.util.*;


public class Main {
    public static void main(String[] args) {
        // Примеры использования функции
        System.out.println(StringManipulation.replaceVowels("apple")); // Выведет "*ppl*"

        // Дополнительный пример с предложением
        String sentence = "Even if you did this task not by yourself, you have to understand every single line of code.";
        System.out.println(StringManipulation.replaceVowels(sentence));
        System.out.println();

        System.out.println(StringTransformer.stringTransform("hello")); // Выведет "heDouble*llo"
        System.out.println(StringTransformer.stringTransform("bookkeeper")); // Выведет "boDouble*oDouble*keDouble*eDouble*per"
        System.out.println();

        System.out.println(BlockFitter.doesBlockFit(1, 3, 5, 4, 5)); // Выведет true
        System.out.println(BlockFitter.doesBlockFit(1, 8, 1, 1, 1)); // Выведет true
        System.out.println(BlockFitter.doesBlockFit(1, 2, 2, 1, 1)); // Выведет false
        System.out.println();

        System.out.println(NumberChecker.numCheck(243)); // Выведет true
        System.out.println(NumberChecker.numCheck(52)); // Выведет false
        System.out.println();

        System.out.println(QuadraticRootsCounter.countRoots(new int[]{1, -3, 2})); // Выведет 2
        System.out.println(QuadraticRootsCounter.countRoots(new int[]{2, 5, 2})); // Выведет 1
        System.out.println(QuadraticRootsCounter.countRoots(new int[]{1, -6, 9})); // Выведет 1
        System.out.println();

        String[][] data1 = {
                {"Apple", "Shop1", "Shop2", "Shop3", "Shop4"},
                {"Banana", "Shop2", "Shop3", "Shop4"},
                {"Orange", "Shop1", "Shop3", "Shop4"},
                {"Pear", "Shop2", "Shop4"}
        };
        System.out.println(SalesDataAnalyzer.salesData(data1)); // Выведет [Apple, Banana, Orange, Pear]

        String[][] data2 = {
                {"Fridge", "Shop2", "Shop3"},
                {"Microwave", "Shop1", "Shop2", "Shop3", "Shop4"},
                {"Laptop", "Shop3", "Shop4"},
                {"Phone", "Shop1", "Shop2", "Shop3", "Shop4"}
        };
        System.out.println(SalesDataAnalyzer.salesData(data2)); // Выведет [Fridge, Microwave, Laptop, Phone]
        System.out.println();

        System.out.println(SentenceSplitChecker.validSplit("apple eagle egg goat")); // Выведет true
        System.out.println(SentenceSplitChecker.validSplit("cat dog goose fish")); // Выведет false
        System.out.println();

        System.out.println(WaveArrayChecker.waveForm(new int[]{3, 1, 4, 2, 7, 5})); // Выведет true
        System.out.println(WaveArrayChecker.waveForm(new int[]{1, 2, 3, 4, 5})); // Выведет false
        System.out.println(WaveArrayChecker.waveForm(new int[]{1, 2, -6, 10, 3})); // Выведет true
        System.out.println();

        System.out.println(VowelFinder.commonVowel("Hello world")); // Выведет 'o'
        System.out.println(VowelFinder.commonVowel("Actions speak louder than words.")); // Выведет 'a'
        System.out.println();

        int[][] matrix1 = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {5, 5, 5, 5, 5},
                {7, 4, 3, 14, 2},
                {1, 0, 11, 10, 1}
        };

        int[][] matrix2 = {
                {6, 4, 19, 0, 0},
                {81, 25, 3, 1, 17},
                {48, 12, 60, 32, 14},
                {91, 47, 16, 65, 217},
                {5, 73, 0, 4, 21}
        };

        // Применяем функционал к первой матрице и печатаем результат
        DataScience.modifyMatrix(matrix1);
        DataScience.printMatrix(matrix1);

        // Применяем функционал ко второй матрице и печатаем результат
        DataScience.modifyMatrix(matrix2);
        DataScience.printMatrix(matrix2);
    }
}

class StringManipulation {

    public static String replaceVowels(String str) {
        return str.replaceAll("[AEIOUaeiou]", "*");
    }
}

class StringTransformer {

    public static String stringTransform(String str) {
        StringBuilder transformed = new StringBuilder();
        int i = 0;

        while (i < str.length()) {
            char currentChar = str.charAt(i);
            // Проверяем, не является ли следующий символ дублирующимся
            if (i + 1 < str.length() && currentChar == str.charAt(i + 1)) {
                transformed.append("Double");
                transformed.append(Character.toUpperCase(str.charAt(i + 1)));
                i += 2; // Пропускаем следующий символ, так как он уже обработан
            } else {
                transformed.append(currentChar);
                i++;
            }
        }

        return transformed.toString();
    }
}

class BlockFitter {

    public static boolean doesBlockFit(int a, int b, int c, int w, int h) {
        // Проверяем, может ли блок поместиться в отверстие, где b не влияет на расположение
        return (a <= w && c <= h) || (a <= h && c <= w);
    }
}

class NumberChecker {

    // Функция, проверяющая, совпадает ли четность суммы квадратов цифр числа с четностью самого числа
    public static boolean numCheck(int number) {
        int sumOfSquares = 0;
        int originalNumber = number;

        // Расчет суммы квадратов цифр числа
        while (number > 0) {
            int digit = number % 10;
            sumOfSquares += digit * digit;
            number /= 10;
        }

        // Проверка четности суммы и числа
        return (sumOfSquares % 2 == 0) == (originalNumber % 2 == 0);
    }
}

class QuadraticRootsCounter {

    public static int countRoots(int[] coefficients) {
        int a = coefficients[0];
        int b = coefficients[1];
        int c = coefficients[2];

        // Вычисление дискриминанта
        int discriminant = b * b - 4 * a * c;

        // Проверка наличия корней
        if (discriminant < 0) {
            return 0; // Нет реальных корней
        } else {
            // Вычисление корней
            double sqrtDiscriminant = Math.sqrt(discriminant);
            double root1 = (-b + sqrtDiscriminant) / (2 * a);
            double root2 = (-b - sqrtDiscriminant) / (2 * a);

            // Проверка, являются ли корни целыми числами
            boolean root1IsInteger = root1 == Math.floor(root1);
            boolean root2IsInteger = root2 == Math.floor(root2);

            if (discriminant == 0) {
                return root1IsInteger ? 1 : 0;
            } else {
                int count = 0;
                if (root1IsInteger) count++;
                if (root2IsInteger) count++;
                return count;
            }
        }
    }
}
class SalesDataAnalyzer {

    public static List<String> salesData(String[][] data) {
        List<Set<String>> listOfStoreSets = new ArrayList<>();
        for (String[] productData : data) {
            Set<String> storesSet = new HashSet<>(Arrays.asList(productData).subList(1, productData.length));
            listOfStoreSets.add(storesSet);
        }

        // Находим пересечение всех множеств магазинов
        Set<String> commonStores = new HashSet<>(listOfStoreSets.get(0));
        for (Set<String> storeSet : listOfStoreSets) {
            commonStores.retainAll(storeSet);
        }

        // Составляем список продуктов, продаваемых во всех общих магазинах
        List<String> result = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            Set<String> stores = new HashSet<>(Arrays.asList(data[i]).subList(1, data[i].length));
            if (stores.containsAll(commonStores)) {
                result.add(data[i][0]);
            }
        }

        return result;
    }
}
class SentenceSplitChecker {

    public static boolean validSplit(String sentence) {
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length - 1; i++) {
            if (words[i].charAt(words[i].length() - 1) != words[i + 1].charAt(0)) {
                return false;
            }
        }
        return true;
    }
}

class WaveArrayChecker {

    public static boolean waveForm(int[] array) {
        if (array == null || array.length < 2) {
            return false; // Массив не может быть волнообразным, если в нем меньше двух элементов
        }

        for (int i = 0; i < array.length - 1; i++) {
            // Проверяем, чередуется ли разница между соседними элементами
            if (!((array[i] > array[i + 1] && (i + 2 >= array.length || array[i + 1] < array[i + 2])) ||
                    (array[i] < array[i + 1] && (i + 2 >= array.length || array[i + 1] > array[i + 2])))) {
                return false;
            }
        }
        return true;
    }
}

class VowelFinder {
    public static String commonVowel(String sentence) {
        Map<Character, Integer> vowelCount = new HashMap<>();
        String vowels = "aeiou";
        char mostCommon = ' ';
        int maxCount = 0;

        for (char ch : sentence.toLowerCase().toCharArray()) {
            if (vowels.indexOf(ch) != -1) {
                vowelCount.put(ch, vowelCount.getOrDefault(ch, 0) + 1);
                if (vowelCount.get(ch) > maxCount) {
                    maxCount = vowelCount.get(ch);
                    mostCommon = ch;
                }
            }
        }

        return maxCount > 0 ? String.valueOf(mostCommon) : "No vowels found";
    }
}

class DataScience {

    public static void modifyMatrix(int[][] matrix) {
        int n = matrix.length; // Получаем размерность матрицы

        for (int i = 0; i < n; i++) {
            // Сумма элементов n-го столбца, исключая n-й элемент
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if (j != i) { // Исключаем n-й элемент
                    sum += matrix[j][i];
                }
            }

            // Вычисляем среднее арифметическое
            float average = (float)sum / (n - 1);

            // Обновляем n-й элемент n-го массива
            // Нужно округлить среднее арифметическое до ближайшего целого числа
            matrix[i][i] = Math.round(average);
        }
    }

    // Метод для печати матрицы
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}