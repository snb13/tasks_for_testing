import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class TestReportGenerator {

    // Чтение JSON из файла
    private static String readJsonFromFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    // Заполнение структуры отчетов на основе values
    private static void fillReport(JSONObject testsJson, Map<Integer, String> valuesMap) {
        JSONArray tests = testsJson.getJSONArray("tests");

        // Обрабатываем каждый тест
        for (int i = 0; i < tests.length(); i++) {
            JSONObject test = tests.getJSONObject(i);
            int testId = test.getInt("id");

            // Заполнение значения теста
            if (valuesMap.containsKey(testId)) {
                test.put("value", valuesMap.get(testId));
            }

            // Рекурсивно обрабатываем вложенные значения (если есть)
            if (test.has("values")) {
                JSONArray subTests = test.getJSONArray("values");
                fillSubTests(subTests, valuesMap);
            }
        }
    }

    // Рекурсивная обработка вложенных тестов
    private static void fillSubTests(JSONArray subTests, Map<Integer, String> valuesMap) {
        for (int i = 0; i < subTests.length(); i++) {
            JSONObject subTest = subTests.getJSONObject(i);
            int subTestId = subTest.getInt("id");

            // Заполнение значения под-теста
            if (valuesMap.containsKey(subTestId)) {
                subTest.put("value", valuesMap.get(subTestId));
            }

            // Рекурсивно обрабатываем вложенные тесты внутри под-теста
            if (subTest.has("values")) {
                JSONArray nestedSubTests = subTest.getJSONArray("values");
                fillSubTests(nestedSubTests, valuesMap);
            }
        }
    }

    // Основная функция
    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            System.out.println("Неизвестный аргумаент");
            return;
        }

        String valuesFilePath = args[0];
        String testsFilePath = args[1];
        String reportFilePath = args[2];

        // Чтение данных из файлов
        String valuesJsonString = readJsonFromFile(valuesFilePath);
        String testsJsonString = readJsonFromFile(testsFilePath);

        // Преобразуем строки JSON в объекты
        JSONObject valuesJson = new JSONObject(valuesJsonString);
        JSONObject testsJson = new JSONObject(testsJsonString);

        // Создаем отображение для значений по id
        JSONArray valuesArray = valuesJson.getJSONArray("values");
        Map<Integer, String> valuesMap = new HashMap<>();
        for (int i = 0; i < valuesArray.length(); i++) {
            JSONObject valueObj = valuesArray.getJSONObject(i);
            valuesMap.put(valueObj.getInt("id"), valueObj.getString("value"));
        }

        // Заполнение отчета
        fillReport(testsJson, valuesMap);

        // Запись заполненного отчета в файл
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(reportFilePath))) {
            writer.write(testsJson.toString(4)); // Форматируем JSON с отступами
        }

        System.out.println("Отчет успешно создан: " + reportFilePath);
    }
}
