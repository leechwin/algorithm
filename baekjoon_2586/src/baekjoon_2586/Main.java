package baekjoon_2586;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int P; // 1≤P≤100,000
    public static int F; // 1≤F≤100,000
    public static int PP[];
    public static int FP[];

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        P = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());
        PP = new int[P];
        FP = new int[F];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < P; i++) {
            PP[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < F; i++) {
            FP[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println();
    }

}
