package baekjoon_10211_maximum_subarray;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int T;
    public static int N;
    public static int NUMBER[];

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            NUMBER = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                NUMBER[j] = Integer.parseInt(st.nextToken());
            }
            System.out.println(solve());
        }
    }

    public static int solve() {
        int cur_sum = -1000;
        int cur_s = 0;
        int cur_e = 0;

        int max_sum = -1000;
        int max_s = 0;
        int max_e = 0;
        for (int s = 0; s < N; s++) {
            if (NUMBER[s] < NUMBER[s] + cur_sum) {
                cur_sum = NUMBER[s] + cur_sum;
                cur_e = s;
            } else {
                cur_sum = NUMBER[s];
                cur_s = s;
            }
            if (max_sum < cur_sum) {
                max_sum = cur_sum;
                max_s = cur_s;
                max_e = cur_e;
            }
        }

        return max_sum;
    }
}
