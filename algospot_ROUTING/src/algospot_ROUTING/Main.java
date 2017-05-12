package algospot_ROUTING;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int C;
    public static int N;
    public static int M;
    public static float MAP[][];

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
            MAP = new float[N][N];
            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                MAP[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = Float.parseFloat(st.nextToken());
            }
            solve();
        }

    }

    private static void solve() {
    }

}
