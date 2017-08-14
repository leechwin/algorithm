package baekjoon_2618_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int N; // (5≤N≤1,000)
    public static int W; // (1≤W≤1,000)

    public static int EVENT[][] = new int[1002][2]; // 사건번호, 경찰차 x,y
    public static int DP[][] = new int[1002][1002];
    public static ArrayList<Integer> PATH = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        EVENT[0][0] = EVENT[0][1] = 1; // 첫번째 사건(첫번째 경찰차 위치)
        EVENT[1][0] = EVENT[1][1] = N; // 두번째 사건(두번째 경찰차 위치)
        for (int i = 2; i < W + 2; i++) {
            st = new StringTokenizer(br.readLine());
            EVENT[i][0] = Integer.parseInt(st.nextToken());
            EVENT[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < 1002; i++) {
            Arrays.fill(DP[i], -1);
        }

        int result = dp(0, 1);
        bw.write(result + "\n");
        bw.flush();

        path(0, 1);
        for (Integer path : PATH) {
            bw.write(path + "\n");
            bw.flush();
        }

        br.close();
        bw.close();
    }

    // 첫번째사건, 두번째 사건
    private static int dp(int first, int second) {
        if (first == W + 1 || second == W + 1) {
            return DP[first][second] = 0;
        }
        if (DP[first][second] != -1) {
            return DP[first][second];
        }

        int next = Math.max(first, second) + 1;
        int ff = dp(next, second) + dist(first, next);
        int ss = dp(first, next) + dist(second, next);
        return DP[first][second] = Math.min(ff, ss);
    }

    private static void path(int first, int second) {
        if (first == W + 1 || second == W + 1) {
            return;
        }

        int next = Math.max(first, second) + 1;
        int ff = DP[next][second] + dist(first, next);
        int ss = DP[first][next] + dist(second, next);
        if (ff < ss) {
            PATH.add(1);
            path(next, second);
        } else {
            PATH.add(2);
            path(first, next);
        }
        return;
    }

    public static int dist(int first, int second) {
        return Math.abs(EVENT[first][0] - EVENT[second][0]) + Math.abs(EVENT[first][1] - EVENT[second][1]);
    }
}
