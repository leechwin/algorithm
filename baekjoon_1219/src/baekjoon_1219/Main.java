package baekjoon_1219;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int N; // 도시의 수
    public static int S; // 시작 도시
    public static int E; // 도착 도시
    public static int M; // 교통수단의 갯수

    public static ArrayList<Vertex> ADJ[];
    public static int MONEY[]; // 도시에서 벌 수 있는 돈의 최댓값
    public static boolean REACHABLE[][];

    public static void main(String[] args) throws Exception {
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ADJ = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            ADJ[i] = new ArrayList<>();
        }
        REACHABLE = new boolean[N][N];
        MONEY = new int[N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            ADJ[a].add(new Vertex(b, cost));
            REACHABLE[a][b] = true;
            REACHABLE[a][a] = true;
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            MONEY[i] = Integer.parseInt(st.nextToken());
        }

        long result = bellmanFord(S, E);
        if (result == -Long.MAX_VALUE) {
            bw.write("Gee\n");
        } else if (result == Long.MAX_VALUE) {
            bw.write("gg\n");
        } else {
            bw.write(-result + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static long bellmanFord(int start, int end) {
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (REACHABLE[i][k] && REACHABLE[k][j]) {
                        REACHABLE[i][j] = true;
                    }
                }
            }
        }

        long dist[] = new long[N];
        Arrays.fill(dist, Long.MAX_VALUE);

        dist[start] = -MONEY[start];
        boolean cycle = false;
        boolean updated = false;
        for (int i = 0; i < N; i++) {
            updated = false;
            for (int here = 0; here < N; here++) {
                for (int j = 0; j < ADJ[here].size(); j++) {
                    Vertex vertex = ADJ[here].get(j);
                    int there = vertex.index;
                    long thereCost = dist[here] + vertex.cost - MONEY[there];
                    if (dist[here] != Long.MAX_VALUE && dist[there] > thereCost) {
                        dist[there] = thereCost;
                        updated = true;
                        if (i == N - 1 && REACHABLE[there][end]) {
                            cycle = true;
                        }
                    }
                }
            }
            if (!updated) {
                break;
            }
        }

        if (cycle) {
            return -Long.MAX_VALUE;
        }

        return dist[end];
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
