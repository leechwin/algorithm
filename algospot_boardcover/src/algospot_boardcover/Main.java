package algospot_boardcover;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int C;
    public static int MAP[][];
    public static int H, W;
    public static int CNT;

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        for (int i = 0; i < C; i++) {
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            MAP = new int[H][W];
            CNT = 0;
            for (int h = 0; h < H; h++) {
                st = new StringTokenizer(br.readLine());
                String line = st.nextToken();
                for (int w = 0; w < W; w++) {
                    if (line.charAt(w) == '#') {
                        MAP[h][w] = 1; // wall
                    } else {
                        MAP[h][w] = 0;
                    }
                }
            }

            dp();
            System.out.println(CNT);
        }
    }

    /*
     * case 1:  |
     *         --
     * case 2: |
     *         --
     * case 3: --
     *         |
     * case 4: --
     *          |
     */
    public static boolean isPossible(int h, int w) {
        if (MAP[h][w] == 1) {
            return false;
        }
        // case 1
        if (h + 1 < H && w - 1 >= 0 && MAP[h + 1][w] == 0 && MAP[h + 1][w - 1] == 0) {
            return true;
        }
        // case 2
        if (h + 1 < H && w + 1 < W && MAP[h + 1][w] == 0 && MAP[h + 1][w + 1] == 0) {
            return true;
        }
        // case 3
        if (h + 1 < H && w + 1 < W && MAP[h][w + 1] == 0 && MAP[h + 1][w] == 0) {
            return true;
        }
        // case 4
        if (h + 1 < H && w + 1 < W && MAP[h][w + 1] == 0 && MAP[h + 1][w + 1] == 0) {
            return true;
        }
        return false;
    }

    public static void dp() {
        boolean isFull = true;
        int h = -1, w = -1;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (MAP[i][j] == 0) {
                    h = i;
                    w = j;
                    isFull = false;
                    break;
                }
            }
            if (h != -1) {
                break;
            }
        }

        if (isFull) {
            CNT++;
            return;
        }

        if (h != -1 && w != -1 && isPossible(h, w)) {
            if (h + 1 < H && w - 1 >= 0 && MAP[h + 1][w] == 0 && MAP[h + 1][w - 1] == 0) {
                MAP[h][w] = MAP[h + 1][w] = MAP[h + 1][w - 1] = 1;
                dp();
                MAP[h][w] = MAP[h + 1][w] = MAP[h + 1][w - 1] = 0;
            }
            if (h + 1 < H && w + 1 < W && MAP[h + 1][w] == 0 && MAP[h + 1][w + 1] == 0) {
                MAP[h][w] = MAP[h + 1][w] = MAP[h + 1][w + 1] = 1;
                dp();
                MAP[h][w] = MAP[h + 1][w] = MAP[h + 1][w + 1] = 0;
            }
            if (h + 1 < H && w + 1 < W && MAP[h][w + 1] == 0 && MAP[h + 1][w] == 0) {
                MAP[h][w] = MAP[h][w + 1] = MAP[h + 1][w] = 1;
                dp();
                MAP[h][w] = MAP[h][w + 1] = MAP[h + 1][w] = 0;
            }
            if (h + 1 < H && w + 1 < W && MAP[h][w + 1] == 0 && MAP[h + 1][w + 1] == 0) {
                MAP[h][w] = MAP[h][w + 1] = MAP[h + 1][w + 1] = 1;
                dp();
                MAP[h][w] = MAP[h][w + 1] = MAP[h + 1][w + 1] = 0;
            }
        }
    }

}
