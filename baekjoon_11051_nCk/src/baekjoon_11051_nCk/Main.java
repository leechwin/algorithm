package baekjoon_11051_nCk;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static int K;
    public static int memo[][];

    public static int MOD = 10007;

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        memo = new int[N + 1][N + 1];

        System.out.println(calc(N, K));
    }

    // nCk = n-1Ck-1 + n-1Ck
    private static int calc(int n, int k) {
        if (k == 0 || n == k) {
            return 1;
        }
        if (memo[n][k] > 0) {
            return memo[n][k];
        }

        memo[n][k] = (calc(n - 1, k - 1) + calc(n - 1, k)) % MOD;
        return memo[n][k];
    }

}
