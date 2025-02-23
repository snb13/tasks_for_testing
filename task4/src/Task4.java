import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Task4 {


    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Не указан файл с координатами.");
            return;
        }

        File coordsFile = new File(args[0]);
        Scanner scanner = new Scanner(new FileInputStream(coordsFile));
        ArrayList<Integer> arrList = new ArrayList<>();

        while (scanner.hasNextInt()) {
            arrList.add(scanner.nextInt());
        }

        double sum = arrList.stream().mapToInt(Integer::intValue).sum();
        double average = sum / arrList.size();

        int resultNum = (int) Math.round(average);

        int counter = 0;
        for (int m = 0; m < arrList.size(); m++) {
            counter += Math.abs(arrList.get(m) - resultNum);
        }

        System.out.println(counter);
    }
}
