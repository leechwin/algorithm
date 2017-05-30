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
    public static boolean reachable[][] = new boolean[101][101];

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
            for (int j = 0; j < V; j++) {
                Arrays.fill(reachable[j], false);
                reachable[j][j] = true;
            }

            for (int j = 0; j < W; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                ADJ[a].add(new Vertex(b, d));
                reachable[a][b] = true;
            }

            Integer resultFirst = bellmanFord();
            if (resultFirst == -INF) {
                bw.write("UNREACHABLE");
                bw.newLine();
                continue;
            }

            if (resultFirst == INF) {
                bw.write("INFINITY ");
            } else {
                bw.write(resultFirst.toString() + " ");
            }

            for (int j = 0; j < V; j++) {
                for (int k = 0; k < ADJ[j].size(); k++) {
                    ADJ[j].get(k).cost = -ADJ[j].get(k).cost;
                }
            }
            Integer resultSecond = bellmanFord();
            if (resultSecond == INF) {
                bw.write("INFINITY");
                bw.newLine();
            } else {
                resultSecond = -resultSecond;
                bw.write(resultSecond.toString());
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();
    }

    private static int bellmanFord() {
        for (int m = 0; m < V; m++) {
            for (int j = 0; j < V; j++) {
                for (int k = 0; k < V; k++) {
                    if (reachable[j][m] && reachable[m][k]) {
                        reachable[j][k] = true;
                    }
                }
            }
        }

        int dist[] = new int[V];
        Arrays.fill(dist, INF);
        dist[0] = 0;

        boolean updated = false;
        boolean cycle = false;
        for (int i = 0; i < V; i++) {
            updated = false;
            for (int here = 0; here < V; here++) {
                for (int j = 0; j < ADJ[here].size(); j++) {
                    Vertex there = ADJ[here].get(j);
                    int next = there.index;
                    int nextCost = dist[here] + there.cost;
                    if (dist[next] > nextCost) {
                        if (i == V - 1 && (reachable[next][1] || reachable[here][1])) {
                            cycle = true;
                        }
                        dist[next] = nextCost;
                        updated = true;
                    }
                }
            }
            if (!updated) {
                break;
            }
        }
        if (dist[1] >= INF - M) {
            return -INF; // UNREACHABLE
        }
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
