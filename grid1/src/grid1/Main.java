package grid1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int H;
    public static int W;
    public static int CNT = 0;
    public static int MAP[][];

    public static void main(String[] args) throws Exception {

        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken()) + 1;
        W = Integer.parseInt(st.nextToken()) + 1;
        MAP = new int[H + 1][W + 1];

        solve(0, 0);
        System.out.println("Total: " + CNT);
    }

    private static void solve(int h, int w) {
        if (h == H - 1 && w == W - 1) {
            CNT++;
            return;
        }
        if (h < 0 || h > H || w < 0 || w > W) {
            return;
        }

        MAP[h][w] = 1;
        // h - w >= 0
        if (h - w >= 0) {
            // down
            if (h + 1 < H && MAP[h + 1][w] == 0) {
                solve(h + 1, w);
            }
            // right
            if (w + 1 < W && MAP[h][w + 1] == 0) {
                solve(h, w + 1);
            }
        }
        MAP[h][w] = 0;
    }

}
