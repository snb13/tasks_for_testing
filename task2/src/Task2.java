import java.io.*;
import java.util.*;

public class Task2 {
    public static void main(String[] args) throws IOException {
        File circle = new File(args[0]);
        File point = new File(args[1]);
        Scanner sc1 = new Scanner(new FileInputStream(circle));
        Scanner sc2 = new Scanner(new FileInputStream(point));
        ArrayList<Double> arrList = new ArrayList<>();
        ArrayList<Double> pointList = new ArrayList<>();
        int result;

        while (sc1.hasNextDouble()) {
            arrList.add(sc1.nextDouble());
        }
        while (sc2.hasNextDouble()) {
            pointList.add(sc2.nextDouble());
        }

        double x = arrList.get(0);
        double y = arrList.get(1);
        double rad = arrList.get(2);


        for (int i = 0; i < pointList.size() - 1; i += 2) {

            double x1_coord = pointList.get(i);
            double y1_coord = pointList.get(i + 1);

            double s = ((x1_coord - x) * (x1_coord - x)) + ((y1_coord - y) * (y1_coord - x)) - (rad * rad);
            if (s < 0) {

                result = 1;
            }
            else if (s > 0) {

                result = 2;
            }
            else {

                result = 0;
            }


            System.out.println(result);
        }

    }

}
