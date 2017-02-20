package baekjoon_10211_maximum_subarray;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int T;
    public static int N;
    public static int NUMBER[] = new int[1001];

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        for (int i = 0; i < T; i++) {
            Arrays.fill(NUMBER, -1);
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                NUMBER[j] = Integer.parseInt(st.nextToken());
            }
            solve();
        }
    }

    public static void solve() {

    }
}
