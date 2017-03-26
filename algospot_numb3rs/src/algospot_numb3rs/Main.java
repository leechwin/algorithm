package algospot_numb3rs;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int N; // number
    public static int D; // day
    public static int P; // prison
    public static int Q; // target num

    public static int MAP[][] = new int[51][51];
    public static double NUMBER[] = new double[51]; // probability
    public static double CACHE[][] = new double[51][101];

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        for (int i = 0; i < c; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());
            P = Integer.parseInt(st.nextToken());

            for (int j = 0; j < N; j++) {
                Arrays.fill(MAP[j], -1);
            }
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                int cnt = 0;
                for (int j2 = 0; j2 < N; j2++) {
                    int value = Integer.parseInt(st.nextToken());
                    MAP[j][j2] = MAP[j2][j] = value;
                    if (value == 1) {
                        cnt++;
                    }
                }
                NUMBER[j] = (double) 1 / cnt;
            }
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < t; j++) {
                for (int k = 0; k < N; k++) {
                    Arrays.fill(CACHE[k], -1);
                }
                Q = Integer.parseInt(st.nextToken());
                System.out.printf("%.8f ", solve(P, 0));
            }
            System.out.println();
        }
    }

    private static double solve(int n, int day) {
        if (CACHE[n][day] != -1) {
            return CACHE[n][day];
        }

        if (day == D) {
            if (n == Q) {
                return 1;
            } else {
                return 0;
            }
        }

        CACHE[n][day] = 0;
        for (int i = 0; i < MAP[n].length; i++) {
            if (MAP[n][i] == 1) {
                CACHE[n][day] += solve(i, day + 1) * NUMBER[n];
            }
        }
        return CACHE[n][day];
    }

}
