package algospot_PROMISES;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int C; // 1 <= C <= 50
    public static int V; // 2 <= V <= 200
    public static int M; // 0 <= M+N <= 1000
    public static int N;
    public static int ADJ[][] = new int[201][201];
    public static int INF = 987654321;

    public static void main(String[] args) throws Exception {
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        for (int i = 0; i < C; i++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            for (int j = 0; j < V; j++) {
                Arrays.fill(ADJ[j], INF);
                ADJ[j][j] = 0;
            }
            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                ADJ[a][b] = ADJ[b][a] = cost;
            }

            floyd();

            int result = 0;
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                if (ADJ[u][v] <= cost) {
                    result++;
                    continue;
                }
                update(u, v, cost);
            }
            bw.write(result + "\n");
            bw.flush();
        }

        bw.close();
        br.close();
    }

    private static void update(int u, int v, int cost) {
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                ADJ[i][j] = Math.min(ADJ[i][j], Math.min(ADJ[i][u] + ADJ[v][j] + cost, ADJ[i][v] + ADJ[u][j] + cost));
            }
        }
    }

    private static void floyd() {
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    ADJ[i][j] = Math.min(ADJ[i][j], ADJ[i][k] + ADJ[k][j]);
                }
            }
        }
    }

}
