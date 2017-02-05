package dfs1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static int MAP[][];
    public static int MARK = 2;
    public static int CNT = 0;
    public static int CNT_LIST[];

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);
        long startTime = System.currentTimeMillis();

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        CNT_LIST = new int[N];
        MAP = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                MAP[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();
        int tot = 0;
        for (int i = 0; i < CNT_LIST.length; i++) {
            if (CNT_LIST[i] != 0) {
                tot++;
            }
        }
        System.out.println(tot);
        for (int i = CNT_LIST.length - 1; i >= 0; i--) {
            if (CNT_LIST[i] != 0) {
                System.out.println(CNT_LIST[i]);
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + " ms");
    }

    public static void solve() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (MAP[i][j] == 1) {
                    CNT = 0;
                    dfs(i, j);
                    CNT_LIST[MARK] = CNT;
                    MARK++;

                }
            }
        }
        Arrays.sort(CNT_LIST);
    }

    public static boolean isSafe(int x, int y) {
        if (x >= N || y >= N) {
            return false;
        } else if (x < 0 || y < 0) {
            return false;
        }
        return true;
    }

    public static void dfs(int x, int y) {
        CNT++;
        MAP[x][y] = MARK;
        if (isSafe(x - 1, y) && MAP[x - 1][y] == 1) {
            dfs(x - 1, y);
        }
        if (isSafe(x + 1, y) && MAP[x + 1][y] == 1) {
            dfs(x + 1, y);
        }
        if (isSafe(x, y - 1) && MAP[x][y - 1] == 1) {
            dfs(x, y - 1);
        }
        if (isSafe(x, y + 1) && MAP[x][y + 1] == 1) {
            dfs(x, y + 1);
        }
    }

}
