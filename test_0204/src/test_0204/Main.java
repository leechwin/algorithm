package test_0204;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int C;
    public static int N; // 탱크의 갯수 (1<=N<=100,000)
    public static Tank TANKS[];
    // #1 20
    // #2 87

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
            TANKS = new Tank[N];
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int point = Integer.parseInt(st.nextToken());
                TANKS[j] = new Tank(x, y, point);
            }
            // RMQ rmq = new RMQ();
            System.out.println("aa");
        }
    }

    static class Tank {
        int x;
        int y;
        int point;

        public Tank(int x, int y, int point) {
            this.x = x;
            this.y = y;
            this.point = point;
        }
    }
}
