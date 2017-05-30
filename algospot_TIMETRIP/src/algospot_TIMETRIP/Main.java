package algospot_TIMETRIP;

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
    public static int V; // 은하계수 V(2 <= V <= 100
    public static int W; // 웜홀의수 W(0 <= W <= 1000)

    public static int INF = 987654321;
    public static int M = 98765432;
    public static ArrayList<Vertex> ADJ[];

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
            W = Integer.parseInt(st.nextToken());

            ADJ = new ArrayList[V];
            for (int j = 0; j < V; j++) {
                ADJ[j] = new ArrayList<>();
            }

            for (int j = 0; j < W; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                ADJ[a].add(new Vertex(b, d));
            }

            int min = bellmanFord();
            if (min == -INF) {
                bw.write("UNREACHABLE\n");
                bw.flush();
                continue;
            }

            if (min == INF) {
                bw.write("INFINITY ");
            } else {
                bw.write(min + " ");
            }
            bw.flush();

            // reverse
            for (int j = 0; j < V; j++) {
                for (int k = 0; k < ADJ[j].size(); k++) {
                    ADJ[j].get(k).cost = -ADJ[j].get(k).cost;
                }
            }

            int max = bellmanFord();
            if (max == INF) {
                bw.write("INFINITY\n");
            } else {
                bw.write(-max + "\n");
            }
            bw.flush();
        }
        bw.close();
        br.close();
    }

    private static int bellmanFord() {
        int dist[] = new int[V];
        Arrays.fill(dist, INF);
        dist[0] = 0;

        boolean updated = false;
        boolean cycle = false;
        // (N-1) + 1번의 루프. 마지막은 음의 싸이클 존재 여부 확인
        for (int i = 0; i < V; i++) {
            updated = false;
            for (int here = 0; here < V; here++) {
                // N-1번의 루프에 걸쳐 각 정점이 i+1개 정점을 거쳐오는 최단경로 갱신
                for (int j = 0; j < ADJ[here].size(); j++) {
                    Vertex vertex = ADJ[here].get(j);
                    int there = vertex.index;
                    int thereCost = dist[here] + vertex.cost;
                    if (dist[there] > thereCost) {
                        // N번째 루프에 값이 갱신되면 음의 싸이클이 존재
                        if (i == V - 1) {
                            cycle = true;
                        }
                        dist[there] = thereCost;
                        updated = true;
                    }
                }
            }
            if (updated == false) {
                break;
            }
        }

        // 값이 변경되지 않았다면 도달하지 않음
        if (dist[1] >= INF - M) {
            return -INF; // UNREACHABLE
        }
        // 사이클이 존재
        if (cycle) {
            return INF; // CYCLE
        }
        return dist[1];
    }

    static class Vertex {
        public int index;
        public int cost;

        public Vertex(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }
    }

}
