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

    // A섬, B섬, 다음섬
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

    // 현재섬에서 다음섬으로 갈 수 있는가
    private static boolean distA(int a, int k) {
        // 다음섬 <= 현재섬 + 현재섬 점프가능거리
        if (P[k] <= (P[a] + D[a])) {
            return true;
        } else {
            return false;
        }
    }

    // 다음섬에서 현재섬으로 올 수 있는가
    private static boolean distB(int b, int k) {
        // 다음섬 - 다음섬 점프가능거리 <= 현재섬
        if (P[k] - D[k] <= P[b]) {
            return true;
        } else {
            return false;
        }
    }

}
