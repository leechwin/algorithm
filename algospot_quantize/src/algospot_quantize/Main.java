package algospot_quantize;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int C;
    public static int N, S;
    public static int NUMBER[];

    public static int P_SUM[];
    public static int VARIANCE[][];
    public static int DP[][];

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());
        for (int i = 0; i < C; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            S = Integer.parseInt(st.nextToken());
            NUMBER = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                NUMBER[j] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(NUMBER);
            partialSum();
            calcVariance();
            DP = new int[N][S + 1];
            for (int j = 0; j < N; j++) {
                Arrays.fill(DP[j], -1);
            }
            System.out.println(dp(0, S));
        }
    }

    private static void calcVariance() {
        VARIANCE = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                VARIANCE[i][j] = variance(i, j);
            }
        }
    }

    /**
     * variance: var(x) = E((x - u)^2)
     */
    private static int variance(int a, int b) {
        int aver = rangeAverage(a, b);
        int ret = 0;
        for (int i = a; i <= b; i++) {
            ret += (aver - NUMBER[i]) * (aver - NUMBER[i]);
        }
        return ret;
    }

    /**
     * p[i] = pSum[i] - pSum[i-1] <br>
     * pSum[a, b] = pSum[b] - pSum[a-1] <br>
     *
     * i: 0 1 2 3 4 5 6 7 8 <br>
     * num: 100 97 86 79 66 52 49 42 31 <br>
     * psum: 100 197 283 362 428 480 529 571 602 <br>
     */
    private static void partialSum() {
        P_SUM = new int[N];
        P_SUM[0] = NUMBER[0];
        for (int i = 1; i < N; i++) {
            P_SUM[i] = P_SUM[i - 1] + NUMBER[i];
        }
    }

    private static int rangeSum(int a, int b) {
        if (a == 0 || b == 0) {
            return P_SUM[b];
        }
        return P_SUM[b] - P_SUM[a - 1];
    }

    private static int rangeAverage(int a, int b) {
        return (int) (0.5 + (double) rangeSum(a, b) / (b - a + 1));
    }

    private static int dp(int idx, int s) {
        if (idx == N) {
            return 0;
        }
        if (s == 0) {
            return 50000000;
        }

        int ret = DP[idx][s];
        if (ret != -1) {
            return ret;
        }

        ret = 50000000;
        for (int i = idx; i < N; i++) {
            ret = Math.min(ret, VARIANCE[idx][i] + dp(i + 1, s - 1));
        }
        DP[idx][s] = ret;
        return ret;
    }

}
