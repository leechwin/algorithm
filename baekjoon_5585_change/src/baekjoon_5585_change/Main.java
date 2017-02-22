package baekjoon_5585_change;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int M;
    public static int N;
    public static int MONEY[];

    // 6
    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        MONEY = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            MONEY[i] = Integer.parseInt(st.nextToken());
        }

        int k = dp(0, M);
        System.out.println(k);
    }

    private static int dp(int i, int m) {
        return 1;
    }
}
