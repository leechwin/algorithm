package minimum_sum1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static int MAP[][];
    public static int result = 0;
    public static int VISITED[];

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        VISITED = new int[N];
        MAP = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                MAP[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            dfs(i, 0, 0);
        }
        System.out.println(result);
    }

    public static void dfs(int xy, int cnt, int sum) {
        if (cnt == N) {
            if (result == 0) {
                result = sum;
            } else {
                if (result > sum) {
                    result = sum;
                }
            }
            return;
        }

        for (int i = 0; i < N; i++) {
            if (i != xy && VISITED[xy] != 1) {
                VISITED[xy] = 1;
                dfs(i, cnt + 1, sum + MAP[xy][i]);
                VISITED[xy] = 0;
            }
        }
    }

}
