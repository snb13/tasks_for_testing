import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Task4 {

    public static void main(String[] args) throws IOException {
        File coordsFile = new File(args[0]);
        Scanner scanner = new Scanner(new FileInputStream(coordsFile));
        ArrayList<Integer> arrList = new ArrayList<>();


        while (scanner.hasNextInt()) {
            arrList.add(scanner.nextInt());
        }

        int tempNum = (arrList.stream()
                .mapToInt(num -> num)
                .sum());
        double t = (double) tempNum / arrList.size();

        int counter = 0;

        int resultNum;
        if ((t % (int) t) >= 0.5) {
                resultNum = (int) Math.ceil(t);
        }
        else if (t >= 0.5 && t < 1.0) {
                resultNum = 1;
        } else  {
            resultNum = (int) Math.floor(t);
        }



        for (int m = 0; m < arrList.size(); m++) {
            while (arrList.get(m) != resultNum) {
                if (arrList.get(m) < resultNum) {
                    arrList.set(m, arrList.get(m) + 1);
                }
                if (arrList.get(m) > resultNum) {
                    arrList.set(m, arrList.get(m) - 1);
                }
                counter++;
            }
        }
        System.out.println(counter);
    }
}
