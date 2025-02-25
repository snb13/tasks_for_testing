import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
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

        Collections.sort(arrList);

        int med = arrList.get(arrList.size() / 2);

        int counter = 0;
        for (int num : arrList) {
            counter += Math.abs(num - med);

        }
        System.out.println(counter);
    }
}
