package stair1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static int CNT;

    public static LinkedList<Integer> queue = new LinkedList<Integer>();

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        solve(0);
        System.out.println(CNT);
    }

    public static void solve(int number) {
        if (number > N) {
            return;
        }

        if (number == N) {
            CNT++;
            return;
        }

        solve(number + 1);
        solve(number + 2);
    }

}
