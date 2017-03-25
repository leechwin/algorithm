package algospot_asymtiling;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int MOD = 1000000007;
    public static int CACHE[] = new int[101];

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        for (int i = 0; i < c; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            System.out.println(asymSolve(n));
        }
    }

    // odd: solve(n) - solve(n/2)
    // even: solve(n) - solve(n/2) - solve(n/2-1)
    private static int asymSolve(int n) {
        if (n % 2 == 1) { // odd
            return (solve(n) - (solve(n / 2)) + MOD) % MOD;
        } else { // even
            int tot = solve(n);
            tot = (tot - (solve(n / 2)) + MOD) % MOD;
            tot = (tot - (solve(n / 2 - 1)) + MOD) % MOD;
            return tot;
        }
    }

    private static int solve(int n) {
        if (n <= 1) {
            return 1;
        }

        if (CACHE[n] != 0) {
            return CACHE[n];
        }
        CACHE[n] = (solve(n - 1) + solve(n - 2)) % MOD;
        return CACHE[n];
    }

}
