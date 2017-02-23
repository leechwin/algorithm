package budget;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int B;
    public static int N;
    public static int MONEY[];
    public static int RESULT;

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        B = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        MONEY = new int[N];
        for (int i = 0; i < N; i++) {
            MONEY[i] = Integer.parseInt(st.nextToken());
        }

        dp(0, 0);
        System.out.println(RESULT);
    }

    private static void dp(int i, int sum) {
        if (i >= N) {
            if (sum <= B && sum > RESULT) {
                RESULT = sum;
            }
            return;
        }
        dp(i + 1, sum);
        dp(i + 1, sum + MONEY[i]);
    }

}
