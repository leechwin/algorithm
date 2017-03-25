package algospot_snail;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int C;

    public static double CACHE[][] = new double[1001][1001];

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 1001; i++) {
            Arrays.fill(CACHE[i], -1);
        }
        for (int i = 0; i < C; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            System.out.println(solve(n, m));
        }

    }

    // solve(n, m) = 0.75 * solve(n-2, m-1) + 0.25 * solve(n-1, m-1)
    private static double solve(int n, int m) {
        if (n <= 0) {
            if (m >= 0) {
                return 1;
            } else {
                return 0;
            }
        }
        if (n > 0 && m < 0) {
            return 0;
        }

        if (CACHE[n][m] != -1) {
            return CACHE[n][m];
        }
        CACHE[n][m] = 0.75 * solve(n - 2, m - 1) + 0.25 * solve(n - 1, m - 1);
        return CACHE[n][m];
    }

}
