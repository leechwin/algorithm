package baekjoon_11404_floyd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int N; // 1 ≤ n ≤ 100
    public static int M; // 1 ≤ m ≤ 100,000
    public static int ADJ[][];
    public static int INF = 987654321;

    public static void main(String[] args) throws Exception {
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        ADJ = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(ADJ[i], INF);
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if (ADJ[a][b] > cost) {
                ADJ[a][b] = cost;
            }
            ADJ[a][a] = 0;
            ADJ[b][b] = 0;
        }
        floyd();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int min = ADJ[i][j];
                if (min == INF) {
                    min = 0;
                }
                bw.write(min + " ");
                bw.flush();
            }
            bw.newLine();
            bw.flush();
        }
        bw.close();
        br.close();
    }

    private static void floyd() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (ADJ[i][j] > ADJ[i][k] + ADJ[k][j]) {
                        ADJ[i][j] = ADJ[i][k] + ADJ[k][j];
                    }
                }
            }
        }
    }

}
