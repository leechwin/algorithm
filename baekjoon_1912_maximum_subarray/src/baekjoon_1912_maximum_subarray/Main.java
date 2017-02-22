package baekjoon_1912_maximum_subarray;

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
        N = Integer.parseInt(st.nextToken());
        NUMBER = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            NUMBER[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solve());
    }

    public static int solve() {
        int cur_sum = -1000;
        int max_sum = -1000;

        for (int i = 0; i < N; i++) {
            if (NUMBER[i] < NUMBER[i] + cur_sum) {
                cur_sum = NUMBER[i] + cur_sum;
            } else {
                cur_sum = NUMBER[i];
            }

            if (max_sum < cur_sum) {
                max_sum = cur_sum;
            }
        }
        return max_sum;
    }

}
