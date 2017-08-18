package baekjoon_11050_nCk;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static int K;

    public static int memo[][];

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
    // memo[n][k] = memo[n-1][k-1] + memo[n-1][k]
    private static int calc(int n, int k) {
        if (k == 0 || n == k) {
            return 1;
        }
        if (memo[n][k] > 0) {
            return memo[n][k];
        }

        memo[n][k] = calc(n - 1, k - 1) + calc(n - 1, k);
        return memo[n][k];
    }

    // nCk = n-1Ck-1 + n-1Ck
    // memo[n][k] = memo[n-1][k-1] + memo[n-1][k]
    private static void calc2(int n, int k) {
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < i + 1; j++) {
                if (j == 0 || i == j) {
                    memo[i][j] = 1;
                } else {
                    memo[i][j] = memo[i - 1][j - 1] + memo[i - 1][j];
                }
            }
        }
    }

}
