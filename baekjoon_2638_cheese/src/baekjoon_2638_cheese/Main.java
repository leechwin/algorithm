package baekjoon_2638_cheese;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static int M;
    public static int MAP[][];
    public static int MAP_TEMP[][];
    public static int CNT;

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        MAP = new int[N][M];
        MAP_TEMP = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                MAP[i][j] = Integer.parseInt(st.nextToken());
                MAP_TEMP[i][j] = MAP[i][j];
            }
        }

        solve();
        System.out.println(CNT);
    }

    public static void solve() {
        boolean isDirty = true;
        while (isDirty) {
            fillAir(0, 0);
            isDirty = setAir();
            copy();
            if (isDirty) {
                CNT++;
            }
        }
    }

    public static void copy() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                MAP[i][j] = MAP_TEMP[i][j];
            }
        }
    }

    public static void fillAir(int x, int y) {
        if (x < 0 || y < 0 || x >= N || y >= M) {
            return;
        }
        if (MAP[x][y] == 0) {
            MAP[x][y] = 2;
            fillAir(x - 1, y);
            fillAir(x + 1, y);
            fillAir(x, y - 1);
            fillAir(x, y + 1);
        }
    }

    public static boolean isMelt(int x, int y) {
        int cnt = 0;
        if (x - 1 >= 0 && MAP[x - 1][y] == 2) {
            cnt++;
        }
        if (x + 1 < N && MAP[x + 1][y] == 2) {
            cnt++;
        }
        if (y - 1 >= 0 && MAP[x][y - 1] == 2) {
            cnt++;
        }
        if (y + 1 < M && MAP[x][y + 1] == 2) {
            cnt++;
        }
        if (cnt >= 2) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean setAir() {
        boolean isDirty = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (MAP[i][j] == 1 && isMelt(i, j)) {
                    MAP_TEMP[i][j] = 0;
                    isDirty = true;
                }
            }
        }
        return isDirty;
    }
}
