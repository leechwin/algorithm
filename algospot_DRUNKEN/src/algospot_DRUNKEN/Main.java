package algospot_DRUNKEN;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    public static int V; // 장소의 수 (1 <= V <= 500)
    public static int E; // 도로의 수 (0 <= E <= V (V + 1)/2)
    public static int T[];

    public static int ADJ[][];
    public static int COST[][];
    public static int INF = 987654321;

    public static void main(String[] args) throws Exception {
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        T = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            T[i] = Integer.parseInt(st.nextToken());
        }
        ADJ = new int[V + 1][V + 1];
        COST = new int[V + 1][V + 1];
        for (int i = 0; i <= V; i++) {
            Arrays.fill(ADJ[i], INF);
            Arrays.fill(COST[i], INF);
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            ADJ[a][b] = cost;
            ADJ[b][a] = cost;
            ADJ[a][a] = 0;
            ADJ[b][b] = 0;
        }

        floydWarshall();

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            bw.write(COST[start][end] + "\n");
            bw.flush();
        }
        bw.close();
        br.close();
    }

    private static void floydWarshall() {
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                COST[i][j] = ADJ[i][j];
            }
        }

        ArrayList<Vertex> list = new ArrayList<Vertex>();
        for (int i = 1; i <= V; i++) {
            list.add(new Vertex(i, T[i]));
        }
        Collections.sort(list, new Comparator<Vertex>() {
            @Override
            public int compare(Vertex v1, Vertex v2) {
                if (v1.cost > v2.cost) {
                    return 1;
                }
                return -1;
            }
        });
        for (int k = 1; k <= V; k++) {
            // 예상시간이 적게걸리는 정점 w 까지를 지나서 얻을 수 있는 최단거리
            int w = list.get(k - 1).index;
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    if (ADJ[i][j] > ADJ[i][w] + ADJ[w][j]) {
                        ADJ[i][j] = ADJ[i][w] + ADJ[w][j];
                    }
                    if (COST[i][j] > ADJ[i][w] + ADJ[w][j] + T[w]) {
                        COST[i][j] = ADJ[i][w] + ADJ[w][j] + T[w];
                    }
                }
            }
        }
    }

    static class Vertex {
        int index;
        int cost;

        public Vertex(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }
    }
}
