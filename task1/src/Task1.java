import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) {

        int m = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);
        System.out.println(m);
        System.out.println(n);
        int[] array = new int[m];
        ArrayList<Integer> result = new ArrayList<>();
        int x = 0;
        for (int i = 0; i < m; i++) {
            array[i] = ++x;
        }
        ArrayList<Integer> arrList = new ArrayList<>();

        int num = 0;
        while (num < m * n) {
            for (int i = 0; i < m; i++) {
                arrList.add(array[i]);
                num++;
            }
        }
        ArrayList<Integer> temp = new ArrayList<>();
        int lastNum = 0;
        while (lastNum != array[0]) {
            for (int i = 0; i < n; i++) {
                temp.add(arrList.get(i));

            }

            result.add(temp.getFirst());
            for (int i = 0; i < n - 1 ; i++) {
                arrList.removeFirst();
            }

            lastNum = temp.getLast();
            temp.clear();
        }

        result.forEach(System.out::print);

    }

}
