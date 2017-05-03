package algospot_SORTGAME;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static int NUMBER[];

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        for (int i = 0; i < C; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            NUMBER = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                NUMBER[j] = Integer.parseInt(st.nextToken());
            }

            solve();
        }
    }

    private static void solve() {

    }

}
