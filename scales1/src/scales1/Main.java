package scales1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static int WEIGHT[] = { 1, 3, 9, 27, 81, 243, 729 };
    public static int MAX = 0;
    public static int USED[] = new int[7]; // 1: left, 2: right, 3: unused

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < WEIGHT.length; i++) {
            MAX += WEIGHT[i];
        }

        int result = dp(N, 0);
        if (result != -1) {
            System.out.print(N);
            for (int i = 0; i < USED.length; i++) {
                if (USED[i] != 0 && USED[i] % 2 != 0) {
                    System.out.print(" " + WEIGHT[i]);
                }
            }
            System.out.print(" 0");
            for (int i = 0; i < USED.length; i++) {
                if (USED[i] != 0 && USED[i] % 2 == 0) {
                    System.out.print(" " + WEIGHT[i]);
                }
            }
        } else {
            System.out.println("Cannot same.");
        }
    }

    public static int dp(int n, int sum) {
        if (n == sum) {
            return sum;
        }

        if (n > MAX || sum > MAX) {
            return -1;
        }

        for (int i = 0; i < WEIGHT.length; i++) {
            if (USED[i] == 0) {
                USED[i] = 1;
                int result = dp(n + WEIGHT[i], sum);
                if (result != -1) {
                    return result;
                } ;
                USED[i] = 2;
                result = dp(n, sum + WEIGHT[i]);
                if (result != -1) {
                    return result;
                } ;
                USED[i] = 0;
            }
        }
        return -1;
    }

}
