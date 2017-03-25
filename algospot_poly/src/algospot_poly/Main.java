package algospot_poly;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int MOD = 10000000;
    public static int CACHE[][] = new int[101][101];

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 101; i++) {
            Arrays.fill(CACHE[i], -1);
        }
        for (int i = 0; i < c; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int result = 0;
            for (int first = 1; first <= n; first++) {
                result += solve(n, first);
            }
            System.out.println(result % MOD);
        }

    }

    // solve(n, first) = (first + second - 1) * solve(n - first, second)
    private static int solve(int n, int first) {
        if (n == first) {
            return 1;
        }
        if (CACHE[n][first] != -1) {
            return CACHE[n][first];
        }
        int result = 0;
        for (int second = 1; second <= n - first; ++second) {
            result += ((first + second - 1) * solve(n - first, second)) % MOD;
        }
        CACHE[n][first] = result % MOD;
        return CACHE[n][first];
    }

}
