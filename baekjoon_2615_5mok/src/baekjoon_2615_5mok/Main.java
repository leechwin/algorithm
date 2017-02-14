package baekjoon_2615_5mok;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int SIZE = 19;
    public static int MAP[][] = new int[SIZE + 2][SIZE + 2];

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        for (int i = 1; i <= SIZE; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= SIZE; j++) {
                MAP[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if (!solve()) {
            System.out.println(0);
        }
    }

    public static boolean solve() {
        for (int i = 1; i <= SIZE; i++) {
            for (int j = 1; j <= SIZE; j++) {
                if (MAP[i][j] != 0) {
                    // find: left -> right
                    if (MAP[i][j - 1] != MAP[i][j] && search1(MAP[i][j], i, j, 1)) {
                        System.out.println(MAP[i][j]);
                        System.out.println(i + " " + j);
                        return true;
                    }
                    // find: left top -> right bottom
                    if (MAP[i - 1][j - 1] != MAP[i][j] && search2(MAP[i][j], i, j, 1)) {
                        System.out.println(MAP[i][j]);
                        System.out.println(i + " " + j);
                        return true;
                    }
                    // find: top -> bottom
                    if (MAP[i - 1][j] != MAP[i][j] && search3(MAP[i][j], i, j, 1)) {
                        System.out.println(MAP[i][j]);
                        System.out.println(i + " " + j);
                        return true;
                    }
                    // find: left bottom -> right top
                    if (MAP[i + 1][j - 1] != MAP[i][j] && search4(MAP[i][j], i, j, 1)) {
                        System.out.println(MAP[i][j]);
                        System.out.println(i + " " + j);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // find: left -> right
    public static boolean search1(int color, int i, int j, int cnt) {
        for (; MAP[i][j + 1] == color; j++) {
            cnt++;
        }
        if (cnt == 5) {
            return true;
        } else {
            return false;
        }
    }

    // find: left top -> right bottom
    public static boolean search2(int color, int i, int j, int cnt) {
        for (; MAP[i + 1][j + 1] == color; i++, j++) {
            cnt++;
        }
        if (cnt == 5) {
            return true;
        } else {
            return false;
        }
    }

    // find: top -> bottom
    public static boolean search3(int color, int i, int j, int cnt) {
        for (; MAP[i + 1][j] == color; i++) {
            cnt++;
        }
        if (cnt == 5) {
            return true;
        } else {
            return false;
        }
    }

    // find: left bottom -> right top
    public static boolean search4(int color, int i, int j, int cnt) {
        for (; MAP[i - 1][j + 1] == color; i--, j++) {
            cnt++;
        }
        if (cnt == 5) {
            return true;
        } else {
            return false;
        }
    }

}
