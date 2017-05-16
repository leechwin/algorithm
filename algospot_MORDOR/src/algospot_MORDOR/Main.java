package algospot_MORDOR;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int C;
    public static int N; // 등산로 표지판수
    public static int Q; // 등산로 수
    public static int H[];

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
            Q = Integer.parseInt(st.nextToken());
            H = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                H[j] = Integer.parseInt(st.nextToken());
            }
            makeRMQ();
            for (int j = 0; j < Q; j++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());

                solve();
                System.out.println("test");
            }
        }

    }

    private static void makeRMQ() {
        // TODO Auto-generated method stub

    }

    private static void solve() {
        // TODO Auto-generated method stub

    }

}
