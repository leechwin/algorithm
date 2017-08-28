package baekjoon_2507_princess;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static int P[];
    public static int D[];
    public static int G[];
    public static int MOD = 1000;

    public static int DP[][];

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        P = new int[N];
        D = new int[N];
        G = new int[N];
        DP = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            P[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
            G[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            Arrays.fill(DP[i], -1);
        }
        int result = dp(0, 0, 1);
        System.out.println(result);
    }

    private static int dp(int a, int b, int k) {
        if (DP[a][b] == -1) {
            if (k == N - 1) {
                if (distA(a, k) && distB(b, k)) {
                    return 1;
                } else {
                    return 0;
                }
            }
            DP[a][b] = dp(a, b, k + 1);
            if (distA(a, k)) {
                DP[a][b] += dp(k, b, k + 1);
            }
            if (distB(b, k) && G[k] == 1) {
                DP[a][b] += dp(a, k, k + 1);
            }
            DP[a][b] %= MOD;
        }
        return DP[a][b];
    }

    // tracking
    private static boolean distA(int a, int k) {
        if (P[k] <= (P[a] + D[a])) {
            return true;
        } else {
            return false;
        }
    }

    // backtracking
    private static boolean distB(int b, int k) {
        if (P[k] <= (P[b] + D[k])) {
            return true;
        } else {
            return false;
        }
    }

}
