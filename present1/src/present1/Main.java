package present1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static int NUMBER[];
    public static int A = 100;
    public static int B;
    public static int C;

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

        solve(0, 0, 0, 0);
        System.out.println(A + " " + B + " " + C);
    }

    private static void solve(int n, int a, int b, int c) {
        if (n < N) {
            solve(n + 1, a + NUMBER[n], b, c);
            solve(n + 1, a, b + NUMBER[n], c);
            solve(n + 1, a, b, c + NUMBER[n]);
        } else if (a >= b && b >= c && a < A) {
            A = a;
            B = b;
            C = c;
        }
    }

}
