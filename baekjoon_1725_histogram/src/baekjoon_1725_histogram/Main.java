package baekjoon_1725_histogram;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static int HEIGHT[];
    public static long MAX = 0;

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        HEIGHT = new int[N + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            HEIGHT[i] = Integer.parseInt(st.nextToken());
        }
        HEIGHT[N] = 0; // end

        solve();
        System.out.println(MAX);
    }

    private static void solve() {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);
        for (int i = 1; i <= N; i++) {
            while (!stack.isEmpty() && HEIGHT[stack.peek()] > HEIGHT[i]) {
                int topIndex = stack.pop();
                // size = height * right - left
                // size = HEIGHT[topIndex] * ((i-1) - prev stack index)
                if (stack.isEmpty()) {
                    MAX = Math.max(MAX, HEIGHT[topIndex] * i);
                } else {
                    MAX = Math.max(MAX, HEIGHT[topIndex] * ((i - 1) - stack.peek()));
                }
            }
            stack.push(i);
        }
    }

}
