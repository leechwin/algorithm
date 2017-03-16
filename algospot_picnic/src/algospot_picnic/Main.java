package algospot_picnic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int C;
    public static int N;
    public static int M;
    public static int MAX_FRIEND;
    public static int TOT_FRIEND;
    public static int FRIEND[][];
    public static int VISITED[];

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
            M = Integer.parseInt(st.nextToken());
            MAX_FRIEND = N / 2;
            TOT_FRIEND = 0;
            FRIEND = new int[N][N];
            VISITED = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                FRIEND[a][b] = 1;
            }

            dp(0, 0);
            System.out.println(TOT_FRIEND);
        }
    }

    private static void dp(int n, int cnt) {
        if (cnt == MAX_FRIEND) {
            TOT_FRIEND++;
            return;
        }
        if (n >= N) {
            return;
        }

        for (int i = 0; i < N; i++) {
            if (FRIEND[n][i] == 1 && VISITED[i] != 1 && VISITED[n] != 1) {
                VISITED[n] = 1;
                VISITED[i] = 1;
                dp(n + 1, cnt + 1);
                VISITED[n] = 0;
                VISITED[i] = 0;
            }
        }
        dp(n + 1, cnt);
    }

}
