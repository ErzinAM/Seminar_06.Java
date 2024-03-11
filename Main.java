import java.util.*;
import java.util.stream.Collectors;

/*
Подумать над структурой класса Ноутбук для магазина техники - выделить поля и методы. Реализовать в java.
Создать множество ноутбуков.
Написать метод, который будет запрашивать у пользователя критерий (или критерии) фильтрации и выведет ноутбуки,
отвечающие фильтру. Критерии фильтрации можно хранить в Map.
Например:
“Введите цифру, соответствующую необходимому критерию:
1 - ОЗУ
2 - Объем ЖД
3 - Операционная система
4 - Цвет …
5 - поиск
Далее нужно запросить минимальные значения для указанных критериев - сохранить параметры фильтрации можно
также в Map.
Отфильтровать ноутбуки из первоначального множества и вывести проходящие по условиям.

Частые ошибки:
1. Заставляете пользователя вводить все существующие критерии фильтрации
2. Невозможно использовать более одного критерия фильтрации одновременно
3. При выборке выводятся ноутбуки, которые удовлетворяют только одному фильтру, а не всем введенным пользователем
4. Работа выполнена только для каких то конкретных ноутбуков, и если поменять характеристики ноутбуков
или добавить еще ноутбук, то программа начинает работать некорректно
*/
public class Main {
    public static void main(String[] args) {
        Notebook nb1 = new Notebook();
        nb1.model = "LG";
        nb1.ssd = 256;
        nb1.color = "black";
        nb1.ram = 8;
        nb1.opSystem = "Windows10";

        Notebook nb2 = new Notebook();
        nb2.model = "Lenovo";
        nb2.ssd = 128;
        nb2.color = "black";
        nb2.ram = 6;
        nb2.opSystem = "Windows10";

        Notebook nb3 = new Notebook();
        nb3.model = "Acer";
        nb3.ssd = 512;
        nb3.color = "white";
        nb3.ram = 16;
        nb3.opSystem = "Windows11";

        Notebook nb4 = new Notebook();
        nb4.model = "Asus";
        nb4.ssd = 256;
        nb4.color = "black";
        nb4.ram = 16;
        nb4.opSystem = "Windows11";

        Notebook nb5 = new Notebook();
        nb5.model = "Huawei";
        nb5.ssd = 128;
        nb5.color = "red";
        nb5.ram = 8;
        nb5.opSystem = "Linux";

        Notebook nb6 = new Notebook();
        nb6.model = "Tecno";
        nb6.ssd = 512;
        nb6.color = "white";
        nb6.ram = 6;
        nb6.opSystem = "Linux";

        Set<Notebook> notebooks = new HashSet<>();
        notebooks.add(nb1);
        notebooks.add(nb2);
        notebooks.add(nb3);
        notebooks.add(nb4);
        notebooks.add(nb5);
        notebooks.add(nb6);

        filterAsFilter(notebooks);
    }

    static void filterAsFilter(Set<Notebook> notebooks) {
        Map<String, Object> filters = new HashMap<>();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите критерии для фильтрации:");
        System.out.println("1 - Объем ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");
        System.out.println("5 - Вывести на экран весь список");
        System.out.println("0 - Выполнить поиск");

        int choice;
        while (true) {
            choice = scanner.nextInt();
            if (choice == 0) {
                break;
            }
            switch (choice) {
                case 1:
                    System.out.println("Минимальный объем ОЗУ");
                    filters.put("ram", scanner.nextInt());
                    break;
                case 2:
                    System.out.println("Минимальный объем ЖД");
                    filters.put("hdd", scanner.nextInt());
                    break;
                case 3:
                    System.out.println("Операционная система");
                    filters.put("os", scanner.next());
                    break;
                case 4:
                    System.out.println("Цвет");
                    filters.put("color", scanner.next());
                    break;
                case 5:
                    notebooks.forEach(System.out::println);
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }

        Set<Notebook> filteredNotebooks = notebooks.stream()
                .filter(notebook -> filters.getOrDefault("ram", 0) instanceof Integer && notebook.ram >= (int) filters.getOrDefault("ram", 0))
                .filter(notebook -> filters.getOrDefault("ssd", 0) instanceof Integer && notebook.ssd >= (int) filters.getOrDefault("hdd", 0))
                .filter(notebook -> filters.getOrDefault("os", "").equals("") || notebook.opSystem.equalsIgnoreCase((String) filters.getOrDefault("os", "")))
                .filter(notebook -> filters.getOrDefault("color", "").equals("") || notebook.color.equalsIgnoreCase((String) filters.getOrDefault("color", "")))
                .collect(Collectors.toSet());

        System.out.println("Подобрали для вас:");
        for (Notebook notebook : filteredNotebooks) {
            System.out.println(notebook);
        }
    }
}
