package jungol_1278_knapsack;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static int W;
    public static int WI[];
    public static int PI[];
    public static int[][] DP;

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        WI = new int[N]; // weight
        PI = new int[N]; // value
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            WI[i] = Integer.parseInt(st.nextToken());
            PI[i] = Integer.parseInt(st.nextToken());
        }
        DP = new int[N + 1][W + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(DP[i], -1);
        }

        System.out.println(dp(N - 1, W));
    }

    private static int dp(int i, int w) {
        // ���� ������ �����Ƿ� 0�� ����
        if (i < 0) {
            return 0;
        }
        // �迭�� �̠��� ����� ���� ����
        if (DP[i][w] != -1) {
            return DP[i][w];
        }

        // V(i,w) = MAX{ V(i-1, w), V(i-1, w - WI[i]) + CI[i] }
        if (w - WI[i] >= 0) {
            // i��° ������ ��� ��� �賶�� ���� �뷮�� ������ ���Ժ��� ũ�ų� ���ƾ� ����.
            DP[i][w] = Math.max(dp(i - 1, w), dp(i - 1, w - WI[i]) + PI[i]);
        } else {
            // i��° ������ ���� �ʴ� ���
            DP[i][w] = dp(i - 1, w);
        }
        return DP[i][w];
    }
}
