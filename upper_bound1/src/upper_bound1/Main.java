package upper_bound1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static int NUM[];
    public static int K;

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);
        long startTime = System.currentTimeMillis();

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        NUM = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            NUM[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());

        System.out.println(solve(0, N - 1));

        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + " ms");

    }

    public static int solve(int s, int e) {
        if (s >= e) {
            return s + 1;
        }

        int middle = (s + e) / 2;
        if (NUM[middle] <= K) { // lower bound와의 차이는 "< 가 <= 로 됨"
            return solve(middle + 1, e);
        } else {
            return solve(s, middle - 1);
        }
    }

}
