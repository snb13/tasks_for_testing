import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class TestReportGenerator {


    private static String readJsonFromFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }


    private static void fillReport(JSONObject testsJson, Map<Integer, String> valuesMap) {
        JSONArray tests = testsJson.getJSONArray("tests");

        for (int i = 0; i < tests.length(); i++) {
            JSONObject test = tests.getJSONObject(i);
            int testId = test.getInt("id");


            if (valuesMap.containsKey(testId)) {
                test.put("value", valuesMap.get(testId));
            }


            if (test.has("values")) {
                JSONArray subTests = test.getJSONArray("values");
                fillSubTests(subTests, valuesMap);
            }
        }
    }


    private static void fillSubTests(JSONArray subTests, Map<Integer, String> valuesMap) {
        for (int i = 0; i < subTests.length(); i++) {
            JSONObject subTest = subTests.getJSONObject(i);
            int subTestId = subTest.getInt("id");


            if (valuesMap.containsKey(subTestId)) {
                subTest.put("value", valuesMap.get(subTestId));
            }


            if (subTest.has("values")) {
                JSONArray nestedSubTests = subTest.getJSONArray("values");
                fillSubTests(nestedSubTests, valuesMap);
            }
        }
    }


    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            System.out.println("Неизвестный аргумаент");
            return;
        }

        String valuesFilePath = args[0];
        String testsFilePath = args[1];
        String reportFilePath = args[2];


        String valuesJsonString = readJsonFromFile(valuesFilePath);
        String testsJsonString = readJsonFromFile(testsFilePath);


        JSONObject valuesJson = new JSONObject(valuesJsonString);
        JSONObject testsJson = new JSONObject(testsJsonString);


        JSONArray valuesArray = valuesJson.getJSONArray("values");
        Map<Integer, String> valuesMap = new HashMap<>();
        for (int i = 0; i < valuesArray.length(); i++) {
            JSONObject valueObj = valuesArray.getJSONObject(i);
            valuesMap.put(valueObj.getInt("id"), valueObj.getString("value"));
        }


        fillReport(testsJson, valuesMap);


        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(reportFilePath))) {
            writer.write(testsJson.toString(4));
        }

        System.out.println("Отчет успешно создан: " + reportFilePath);
    }
}
