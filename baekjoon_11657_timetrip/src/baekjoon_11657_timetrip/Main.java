package baekjoon_11657_timetrip;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int N; // 도시의 개수 N (1 ≤ N ≤ 500)
    public static int M; // 버스 노선의 개수 M (1 ≤ M ≤ 6,000)

    public static int INF = 987654321;
    public static ArrayList<Vertex> ADJ[];

    public static void main(String[] args) throws Exception {
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ADJ = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            ADJ[i] = new ArrayList<Vertex>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            ADJ[a].add(new Vertex(b, cost));
        }

        int result[] = bellmanFord();
        if (result[1] != 0) {
            bw.write(-1 + "\n");
            bw.flush();
        } else {
            for (int i = 2; i < result.length; i++) {
                if (result[i] >= INF - 98765432) {
                    bw.write(-1 + "\n");
                } else {
                    bw.write(result[i] + "\n");
                }
                bw.flush();
            }
        }
        bw.close();
    }

    private static int[] bellmanFord() {
        int dist[] = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[0] = dist[1] = 0;

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
                        updated = true;
                        if (i == N) {
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
            dist[1] = -1;
        }
        return dist;
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
