package algospot_tiling2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int MOD = 1000000007;
    public static int C;
    public static int CACHE[] = new int[101];

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        for (int i = 0; i < C; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            System.out.println(solve(n));
        }
    }

    private static int solve(int n) {
        if (n <= 1) {
            return 1;
        }
        if (CACHE[n] != 0) {
            return CACHE[n];
        }

        int result = (solve(n - 1) + solve(n - 2)) % MOD;
        CACHE[n] = result;
        return result;
    }

}
