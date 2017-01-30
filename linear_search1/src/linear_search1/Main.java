package linear_search1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static int NUM[];
    public static int T;

    public static void main(String[] args) throws Exception {
        /* For local test */
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);
        long startTime = System.currentTimeMillis();

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        NUM = new int[N];

        for (int i = 0; i < N; i++) {
            NUM[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        System.out.println(solve(0, N - 1));
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }

    public static int solve(int s, int e) {
        if (s >= e) {
            return -1;
        }
        int mid = (s + e) / 2;
        if (NUM[mid] == T) {
            return mid + 1;
        } else if (NUM[mid] < T) {
            return solve(mid + 1, e);
        } else {
            return solve(s, mid - 1);
        }
    }

}
