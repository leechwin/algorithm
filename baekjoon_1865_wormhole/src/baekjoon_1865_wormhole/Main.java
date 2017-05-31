package baekjoon_1865_wormhole;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int C;

    public static int N; // 지점의 수 N(1≤N≤500)
    public static int M; // 도로의 개수 M(1≤M≤2500)
    public static int W; // 웜홀의 개수(1≤W≤200)

    public static ArrayList<Vertex> ADJ[];

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
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            ADJ = new ArrayList[N + 1];
            for (int j = 0; j <= N; j++) {
                ADJ[j] = new ArrayList<>();
            }

            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                ADJ[s].add(new Vertex(e, t));
                ADJ[e].add(new Vertex(s, t));
            }
            for (int j = 0; j < W; j++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                ADJ[s].add(new Vertex(e, -t));
            }

            VISITED = new int[N + 1];
            boolean isCycle = false;
            for (int j = 1; j <= N; j++) {
                if (VISITED[j] == 0) {
                    int result = bellmanFord(j);
                    if (result == -INF) {
                        isCycle = true;
                        break;
                    }

                }
            }

            if (isCycle) {
                bw.write("YES\n");
            } else {
                bw.write("NO\n");
            }
            bw.flush();
        }
        bw.close();
        br.close();
    }

    public static int VISITED[];

    private static int bellmanFord(int start) {
        VISITED[start] = 1;
        int dist[] = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        boolean updated = false;
        boolean cycle = false;
        for (int i = 1; i <= N; i++) {
            updated = false;
            for (int here = 1; here <= N; here++) {
                for (int j = 0; j < ADJ[here].size(); j++) {
                    Vertex vertex = ADJ[here].get(j);
                    int there = vertex.index;
                    int thereCost = vertex.cost + dist[here];
                    if (dist[there] > thereCost) {
                        dist[there] = thereCost;
                        if (i == N) {
                            cycle = true;
                        }
                        updated = true;
                    }
                }
            }
            if (!updated) {
                break;
            }
        }

        if (cycle) {
            return -INF;
        }
        return dist[start];
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
