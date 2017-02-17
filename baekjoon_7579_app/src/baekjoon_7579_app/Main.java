package baekjoon_7579_app;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static int M;
    public static int APP[];
    public static int COST[];
    public static int DP[] = new int[10001];

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        APP = new int[N];
        COST = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            APP[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            COST[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(DP, -1);
        dp();

        for (int i = 0; i < 10001; i++) {
            if (DP[i] >= M) {
                System.out.println(i);
                break;
            }

        }
    }

    public static void dp() {
        DP[0] = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 10; j++) {
                if (DP[j] != -1) {
                    if (DP[j + COST[i]] == -1) {
                        DP[j + COST[i]] = DP[j] + APP[i];
                    } else if (DP[j + COST[i]] > DP[j] + APP[i]) {
                        DP[j + COST[i]] = DP[j] + APP[i];
                    }
                }
            }

        }
    }

}
