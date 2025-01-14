import java.io.*;
import java.util.*;

public class Task2 {
    public static void main(String[] args) throws IOException {
        File circle = new File(args[0]);
        File point = new File(args[1]);
        Scanner sc1 = new Scanner(new FileInputStream(circle));
        Scanner sc2 = new Scanner(new FileInputStream(point));
        ArrayList<Integer> arrList = new ArrayList<>();
        ArrayList<Integer> pointList = new ArrayList<>();
        int result;

        while (sc1.hasNextInt()) {
            arrList.add(sc1.nextInt());
        }
        while (sc2.hasNextInt()) {
            pointList.add(sc2.nextInt());
        }

        int x = arrList.get(0);
        int y = arrList.get(1);
        int rad = arrList.get(2);

        for (int i = 0; i < pointList.size() - 1; i += 2) {

            int x1_coord = pointList.get(i);
            int y1_coord = pointList.get(i+1);

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

            /*if (x == x1_coord && Math.abs(y1_coord - y) == rad || y == y1_coord && Math.abs(x1_coord - x) == rad) {
                result = 0;
            } else if (x1_coord >= Math.abs(x + rad) || y1_coord >= Math.abs(y + rad)){
                result = 2;
            } else {
                result = 1;
            }*/

            System.out.println(result);
        }

    }

}
