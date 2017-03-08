package algospot_fence;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static int C;
    public static int N;
    public static int FENCE[];

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
            FENCE = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                FENCE[j] = Integer.parseInt(st.nextToken());
            }
            FENCE[N] = 0; // end

            System.out.println(solve());
        }

    }

    private static long solve() {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);

        long result = 0;

        for (int i = 1; i <= N; i++) {
            if (FENCE[stack.peek()] < FENCE[i]) {
                stack.push(i);
            } else {
                while (!stack.isEmpty() && FENCE[stack.peek()] > FENCE[i]) {
                    // size = height * (right - left)
                    // size = FENCE[top] * (i - prev stack index - 1)
                    int top = stack.pop();
                    if (stack.isEmpty()) {
                        result = Math.max(result, FENCE[top] * i);
                    } else {
                        result = Math.max(result, FENCE[top] * (i - stack.peek() - 1));
                    }
                }
                stack.push(i);
            }
        }

        return result;
    }

}
