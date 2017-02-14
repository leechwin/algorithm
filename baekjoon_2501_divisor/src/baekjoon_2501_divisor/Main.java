package baekjoon_2501_divisor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static int K;

    public static HashSet<Integer> DIVISOR = new HashSet<Integer>(10001);

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        solve();
    }

    public static void solve() {
        DIVISOR.add(1);
        for (int i = 2; i <= Math.sqrt(N); i++) {
            if (N % i == 0) {
                DIVISOR.add(i);
                DIVISOR.add(N / i);
            }
        }
        DIVISOR.add(N);
        ArrayList<Integer> list = new ArrayList<Integer>(DIVISOR);
        if (list.size() < K) {
            System.out.println(0);
        } else {
            System.out.println(list.get(K - 1));
        }
    }

}
