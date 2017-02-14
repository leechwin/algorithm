package dfs2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static int M;
    public static int MAP[][];
    public static int VISITED[];
    public static int SUM;

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);
        long startTime = System.currentTimeMillis();

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        MAP = new int[N + 1][N + 1];
        VISITED = new int[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            MAP[x][y] = MAP[y][x] = w;
        }

        VISITED[1] = 1;
        dfs(1, 0);
        System.out.println(SUM);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + " ms");
    }

    public static void dfs(int V, int W) {
        if (V == N) {
            if (SUM == 0) {
                SUM = W;
            } else if (SUM > W) {
                SUM = W;
            }
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (VISITED[i] == 0 && MAP[V][i] != 0) {
                VISITED[i] = 1;
                dfs(i, W + MAP[V][i]);
                VISITED[i] = 0;
            }
        }
    }

}
