package jungol_1889_N_Queen;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static int COL[];
    public static int INC[]; // x + y
    public static int DEC[]; // x - y
    public static int CNT = 0;

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        COL = new int[N];
        INC = new int[N + N];
        DEC = new int[N + N];

        dfs(0); // start
        System.out.println(CNT);
    }

    public static void dfs(int col) {
        if (col >= N) {
            CNT++;
            return;
        }

        for (int row = 0; row < N; row++) {
            if (COL[row] == 0 && INC[col + row] == 0 && DEC[(col - row) + N] == 0) {
                COL[row] = INC[col + row] = DEC[(col - row) + N] = 1;
                dfs(col + 1);
                COL[row] = INC[col + row] = DEC[(col - row) + N] = 0;
            }
        }
    }

}
