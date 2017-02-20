package bicoloring;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static int M;
    public static int G[][];
    public static int VISITED_COLOR[];

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        // FileInputStream fis = new FileInputStream("input2.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        G = new int[N][N];
        VISITED_COLOR = new int[N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            G[start][end] = G[end][start] = 1;
        }

        solve();
    }

    public static void solve() {
        VISITED_COLOR[0] = 1; // 0: no visit, 1: black, 2: red
        if (dfs(0, 1)) {
            System.out.println("OK");
        } else {
            System.out.println("IMPOSSIBLE");
        }
    }

    public static boolean dfs(int num, int color) {
        for (int i = 0; i < G[num].length; i++) {
            if (i != num && G[num][i] == 1) {
                // 인접된 노드가 같은 색일 경우 불가능
                if (VISITED_COLOR[i] == color) {
                    return false;
                } else {
                    // 인접된 노드가 색이 없을경우 다른 색을 색칠
                    if (VISITED_COLOR[i] == 0) {
                        if (color == 1) {
                            VISITED_COLOR[i] = 2;
                            if (!dfs(i, 2)) {
                                return false;
                            }
                        } else {
                            VISITED_COLOR[i] = 1;
                            if (!dfs(i, 1)) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

}
