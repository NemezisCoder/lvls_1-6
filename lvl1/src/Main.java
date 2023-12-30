//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println(Converter.convertGallonsToLiters(5)); // Выведет примерно 18.925
        System.out.println(Converter.convertGallonsToLiters(3)); // Выведет примерно 11.355
        System.out.println(Converter.convertGallonsToLiters(8)); // Выведет примерно 30.28
        System.out.println();

        System.out.println(FitnessCalculator.fitCalc(15, 1)); // Выведет 15
        System.out.println(FitnessCalculator.fitCalc(24, 2)); // Выведет 48
        System.out.println(FitnessCalculator.fitCalc(41, 3)); // Выведет 123
        System.out.println();

        System.out.println(WarehouseCalculator.containers(3, 4, 2)); // Выведет 460
        System.out.println(WarehouseCalculator.containers(5, 0, 2)); // Выведет 300
        System.out.println(WarehouseCalculator.containers(4, 1, 4)); // Выведет 530
        System.out.println();

        System.out.println(TriangleClassifier.triangleType(5, 5, 5)); // Выведет "equilateral"
        System.out.println(TriangleClassifier.triangleType(5, 4, 5)); // Выведет "isosceles"
        System.out.println(TriangleClassifier.triangleType(3, 4, 5)); // Выведет "different-sided"
        System.out.println(TriangleClassifier.triangleType(5, 1, 1)); // Выведет "not a triangle"
        System.out.println();

        System.out.println(Comparison.ternaryEvaluation(8, 4)); // Выведет 8
        System.out.println(Comparison.ternaryEvaluation(1, 11)); // Выведет 11
        System.out.println(Comparison.ternaryEvaluation(5, 9)); // Выведет 9
        System.out.println();

        System.out.println(FabricCalculator.howManyItems(22, 1.4, 2)); // Выведет 3
        System.out.println(FabricCalculator.howManyItems(45, 1.8, 1.9)); // Выведет 6
        System.out.println(FabricCalculator.howManyItems(100, 2, 2)); // Выведет 12
        System.out.println();

        System.out.println(FactorialCalculator.factorial(3)); // Выведет 6
        System.out.println(FactorialCalculator.factorial(5)); // Выведет 120
        System.out.println(FactorialCalculator.factorial(7)); // Выведет 5040
        System.out.println();

        System.out.println(GCD.gcd(48, 18)); // Выведет 6
        System.out.println(GCD.gcd(52, 8)); // Выведет 4
        System.out.println(GCD.gcd(259, 28)); // Выведет 1
        System.out.println();

        System.out.println(TicketSales.ticketSaler(70, 1500)); // Выведет 75600 * (1 - 0.10)
        System.out.println(TicketSales.ticketSaler(24, 950)); // Выведет 24 * 950 * (1 - 0.10)
        System.out.println(TicketSales.ticketSaler(53, 1250)); // Выведет 53 * 1250 * (1 - 0.10)
        System.out.println();

        System.out.println(Classroom.tables(5, 2)); // Выведет 1
        System.out.println(Classroom.tables(31, 20)); // Выведет 0
        System.out.println(Classroom.tables(123, 58)); // Выведет 4
        System.out.println();

    }
}

class Converter {

    // Функция для преобразования галлонов в литры
    public static double convertGallonsToLiters(int gallons) {
        // Константа для преобразования галлонов в литры
        final double LITERS_PER_GALLON = 3.785;

        // Выполнение преобразования
        return gallons * LITERS_PER_GALLON;
    }
}

class FitnessCalculator {

    // Функция для расчета калорий
    public static int fitCalc(int minutes, int intensity) {
        // Коэффициенты для расчета калорий в зависимости от интенсивности
        final int LOW_INTENSITY_CALORIES_PER_MINUTE = 1;
        final int MEDIUM_INTENSITY_CALORIES_PER_MINUTE = 2;
        final int HIGH_INTENSITY_CALORIES_PER_MINUTE = 3;

        // Выбор коэффициента в зависимости от интенсивности тренировки
        int caloriesPerMinute = 0;
        switch (intensity) {
            case 1:
                caloriesPerMinute = LOW_INTENSITY_CALORIES_PER_MINUTE;
                break;
            case 2:
                caloriesPerMinute = MEDIUM_INTENSITY_CALORIES_PER_MINUTE;
                break;
            case 3:
                caloriesPerMinute = HIGH_INTENSITY_CALORIES_PER_MINUTE;
                break;
            default:
                // Если указана неверная интенсивность, можно выбросить исключение или вернуть 0
                return 0;
        }

        // Расчет общего количества сожженных калорий
        return minutes * caloriesPerMinute;
    }
}
class WarehouseCalculator {

    // Константы для количества товаров в разных типах контейнеров
    private static final int ITEMS_PER_BOX = 20;
    private static final int ITEMS_PER_BAG = 50;
    private static final int ITEMS_PER_BARREL = 100;

    // Функция для расчета общего количества товаров на складе
    public static int containers(int boxes, int bags, int barrels) {
        return (boxes * ITEMS_PER_BOX) + (bags * ITEMS_PER_BAG) + (barrels * ITEMS_PER_BARREL);
    }
}
class TriangleClassifier {

    public static String triangleType(int x, int y, int z) {
        // Проверка на существование треугольника
        if (x <= 0 || y <= 0 || z <= 0 || x + y <= z || x + z <= y || y + z <= x) {
            return "not a triangle";
        }

        // Определение типа треугольника
        if (x == y && y == z) {
            return "equilateral";
        } else if (x == y || y == z || x == z) {
            return "isosceles";
        } else {
            return "different-sided";
        }
    }
}

class Comparison {

    // Функция, использующая тернарный оператор для определения большего числа
    public static int ternaryEvaluation(int a, int b) {
        // Тернарный оператор сравнивает два числа и возвращает большее
        return (a > b) ? a : b;
    }
}

class FabricCalculator {

    // Функция для расчета количества пододеяльников
    public static int howManyItems(double lengthOfFabric, double widthItem, double heightItem) {
        // Расчет количества пододеяльников, которые могут быть изготовлены
        int count = (int) (lengthOfFabric / (widthItem * heightItem));
        return count;
    }
}

class FactorialCalculator {

    // Функция для вычисления факториала числа
    public static int factorial(int n) {
        if (n <= 1) {
            return 1;
        } else {
            return n * factorial(n - 1); // Рекурсивный вызов функции
        }
    }
}

class GCD {

    // Функция для нахождения НОД двух чисел
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}

class TicketSales {

    // Предположим, что комиссия составляет 10%
    private static final double COMMISSION_RATE = 0.10;

    // Функция для расчета общей выручки от продажи билетов с учетом комиссии
    public static double ticketSaler(int ticketsSold, double ticketPrice) {
        double grossIncome = ticketsSold * ticketPrice;
        double commission = grossIncome * COMMISSION_RATE;
        double netIncome = grossIncome - commission;
        return netIncome;
    }
}

class Classroom {

    // Функция для расчета необходимого количества столов
    public static int tables(int students, int tablesAvailable) {
        // Поскольку за одним столом могут сидеть два студента, удваиваем количество доступных столов
        int totalSeats = tablesAvailable * 2;

        // Вычисляем, сколько мест не хватает
        int additionalTablesNeeded = (students > totalSeats) ? (int) Math.ceil((students - totalSeats) / 2.0) : 0;

        return additionalTablesNeeded;
    }
}