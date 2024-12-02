import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class DataProcessor {

    private List<String> data;

    public DataProcessor() {
        this.data = new ArrayList<>();
    }

    // Чтение данных из файла
    public void readFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line.trim());
            }
        }
    }

    // Сортировка данных
    public void sortData() {
        Collections.sort(data);
    }

    // Фильтрация данных по ключевому слову
    public List<String> filterData(String keyword) {
        return data.stream()
                .filter(line -> line.toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Анализ данных: подсчёт количества строк
    public int countLines() {
        return data.size();
    }

    // Добавление данных пользователем
    public void addData(String newData) {
        data.add(newData);
    }

    // Сохранение данных в файл
    public void saveToFile(String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    // Печать данных на консоль
    public void printData() {
        data.forEach(System.out::println);
    }
}

public class Main {
    public static void main(String[] args) {
        DataProcessor processor = new DataProcessor();
        Scanner scanner = new Scanner(System.in);

        try {
            // Чтение файла
            System.out.println("Введите путь к файлу для чтения данных:");
            String inputFile = scanner.nextLine();
            processor.readFile(inputFile);

            System.out.println("Данные из файла:");
            processor.printData();

            // Сортировка данных
            processor.sortData();
            System.out.println("\nДанные после сортировки:");
            processor.printData();

            // Фильтрация данных
            System.out.println("\nВведите ключевое слово для фильтрации:");
            String keyword = scanner.nextLine();
            List<String> filteredData = processor.filterData(keyword);
            System.out.println("\nОтфильтрованные данные:");
            filteredData.forEach(System.out::println);

            // Анализ данных
            int lineCount = processor.countLines();
            System.out.println("\nОбщее количество строк: " + lineCount);

            // Добавление новых данных
            System.out.println("\nВведите новые данные для добавления:");
            String newData = scanner.nextLine();
            processor.addData(newData);

            // Сохранение данных в файл
            System.out.println("\nВведите путь к файлу для сохранения данных:");
            String outputFile = scanner.nextLine();
            processor.saveToFile(outputFile);
            System.out.println("Данные успешно сохранены в файл: " + outputFile);

        } catch (IOException e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
