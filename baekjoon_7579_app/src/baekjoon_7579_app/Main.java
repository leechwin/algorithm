package baekjoon_7579_app;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int N; // 1 ≤ N ≤ 100
    public static int M; // 1 ≤ M ≤ 10,000,000
    public static int MEM[]; // 1 ≤ m1, ..., mN ≤ 10,000,000
    public static int COST[]; // 0 ≤ c1, ..., cN ≤ 100
    public static int DP[] = new int[10001];

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        MEM = new int[N];
        COST = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            MEM[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            COST[i] = Integer.parseInt(st.nextToken());
        }

        dp();

        for (int i = 0; i < 10001; i++) {
            if (DP[i] >= M) {
                System.out.println(i);
                break;
            }
        }
    }

    // V(COST) = MAX( V(COST), V(COST - COST[i]) + MEM[i] )
    public static void dp() {
        int cost_sum = 0;
        for (int i = 0; i < COST.length; i++) {
            cost_sum += COST[i];
        }
        for (int i = 0; i < N; i++) {
            for (int j = cost_sum; j >= COST[i]; j--) {
                if (DP[j] < DP[j - COST[i]] + MEM[i]) {
                    DP[j] = DP[j - COST[i]] + MEM[i];
                }
            }
        }
    }

}
