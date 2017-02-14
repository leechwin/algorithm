package baekjoon_9506_sum_of_divisor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    public static int N = 0;
    public static Set<Integer> DIVISOR = new HashSet<Integer>(100001);

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        while (N != -1) {
            DIVISOR.clear();
            DIVISOR.add(1);
            for (int i = 2; i <= Math.sqrt(N); i++) {
                if (N % i == 0) {
                    DIVISOR.add(i);
                    DIVISOR.add(N / i);
                }
            }
            int sum = 0;
            for (Iterator<Integer> iterator = DIVISOR.iterator(); iterator.hasNext();) {
                sum += (Integer) iterator.next();
            }
            if (sum == N) {
                System.out.print(N + " = ");
                for (Iterator<Integer> iterator = DIVISOR.iterator(); iterator.hasNext();) {
                    System.out.print(iterator.next());
                    if (iterator.hasNext()) {
                        System.out.print(" + ");
                    } else {
                        System.out.println();
                    }
                }
            } else {
                System.out.println(N + " is NOT perfect.");
            }

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
        }
    }

}
