import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Task4 {

    public static void main(String[] args) throws IOException {
        File coordsFile = new File(args[0]);
        //BufferedReader reader = new BufferedReader(new FileReader("task4/src/dataSource.txt"));
        BufferedReader reader = new BufferedReader(new FileReader(coordsFile));

        ArrayList<Integer> arrList = new ArrayList<>();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                arrList.add(Integer.parseInt(line));
            }
        } finally {
            reader.close();
        }
        int counter = 0;
        int resultNum = (arrList.stream()
                .mapToInt(num -> num)
                .sum())/arrList.size();


        for (int m = 0; m < arrList.size(); m++) {
            while (arrList.get(m) != resultNum) {
                if(arrList.get(m) < resultNum) {
                    arrList.set(m, arrList.get(m) + 1);
                    counter++;
                } else {
                    arrList.set(m, arrList.get(m) - 1);
                    counter++;
                }
            }
        }
        System.out.println(counter);
    }
}
