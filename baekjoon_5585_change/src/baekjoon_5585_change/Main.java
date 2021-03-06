package baekjoon_5585_change;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int M;
    public static int MONEY[] = { 500, 100, 50, 10, 5, 1 };
    public static int CNT;

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        int result = solve(1000 - M);
        if (result != 0) {
            System.out.println(-1);
        } else {
            System.out.println(CNT);
        }
    }

    private static int solve(int m) {
        int money = m;
        for (int i = 0; i < MONEY.length; i++) {
            if (money >= MONEY[i]) {
                int multi = money / MONEY[i];
                CNT += multi;
                money -= (MONEY[i] * multi);
            }
        }
        return money;
    }
}
