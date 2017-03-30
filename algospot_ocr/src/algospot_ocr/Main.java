package algospot_ocr;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int M;
    public static int Q;

    public static String STR_M[];
    public static double B[];
    public static double T[][];
    public static double MM[][];

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        STR_M = new String[M];
        for (int i = 0; i < M; i++) {
            STR_M[i] = st.nextToken();
        }

        st = new StringTokenizer(br.readLine());
        B = new double[M];
        for (int i = 0; i < M; i++) {
            B[i] = Double.parseDouble(st.nextToken());
        }

        T = new double[M][M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                T[i][j] = Double.parseDouble(st.nextToken());
            }
        }

        MM = new double[M][M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                MM[i][j] = Double.parseDouble(st.nextToken());
            }
        }

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            String str[] = new String[n];
            for (int j = 0; j < n; j++) {
                str[j] = st.nextToken();
            }
            solve(n, str);

        }
    }

    private static void solve(int n, String[] str) {
        // TODO Auto-generated method stub
    }

}
