package algospot_fence;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static int C;
    public static int N;
    public static int HEIGHT[];
    public static int VALUE[];

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
            HEIGHT = new int[N];
            VALUE = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                HEIGHT[j] = Integer.parseInt(st.nextToken());
            }

            solve();
        }

    }

    private static void solve() {
        // 1 5 6 100 > 6
        //
        // 1 5 10 100 100
        int topInex = 0;
        int maxValue = HEIGHT[0];

        Stack<Integer> stack = new Stack<>();
        stack.push(HEIGHT[0]);

        for (int i = 1; i < HEIGHT.length; i++) {
            int preHeight = stack.peek();
            int curHeight = HEIGHT[i];
            if (preHeight > curHeight) {
                topInex = i;
                VALUE[i] = Math.max(maxValue, HEIGHT[i] * topInex + 1);
            } else {

            }
        }
    }

}
