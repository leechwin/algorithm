package algospot_TIMETRIP;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int C;
    public static int V; // 은하계수 V(2 <= V <= 100
    public static int W; // 웜홀의수 W(0 <= W <= 1000)

    public static ArrayList<Vertex> ADJ[] = new ArrayList[101];
    public static boolean reachable[][] = new boolean[101][101];

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        for (int j = 0; j < 101; j++) {
            ADJ[j] = new ArrayList<>();
        }
        for (int i = 0; i < C; i++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 101; j++) {
                ADJ[j].clear();
                Arrays.fill(reachable[j], false);
            }
            for (int j = 0; j < 101; j++) {
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

            for (int m = 0; m < V; m++) {
                for (int j = 0; j < V; j++) {
                    for (int k = 0; k < ADJ.length; k++) {
                        if (reachable[j][m] && reachable[m][k]) {
                            reachable[j][k] = true;
                        }
                    }
                }
            }

            int resultFirst = bellmanFord();
            if (resultFirst == Integer.MAX_VALUE) {
                System.out.print("INFINITY");
            } else if (resultFirst == Integer.MIN_VALUE) {
                System.out.println("UNREACHABLE");
                continue;
            } else {
                System.out.print(resultFirst);
            }

            System.out.print(" ");

            for (int j = 0; j < W; j++) {
                for (int k = 0; k < ADJ[j].size(); k++) {
                    ADJ[j].get(k).cost = -ADJ[j].get(k).cost;
                }
            }
            int resultSecond = bellmanFord();
            if (resultSecond == Integer.MAX_VALUE) {
                System.out.println("INFINITY");
            } else if (resultSecond == Integer.MIN_VALUE) {
                System.out.println("UNREACHABLE");
            } else {
                System.out.println(-resultSecond);
            }
        }
    }

    private static int bellmanFord() {
        int dist[] = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
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
                        dist[next] = nextCost;
                        updated = true;
                        if (i == V - 1 && (reachable[next][1] || reachable[here][1])) {
                            cycle = true;
                        }
                    }
                }
            }
            if (!updated) {
                break;
            }
        }
        if (dist[1] >= Integer.MAX_VALUE) {
            return Integer.MIN_VALUE; // UNREACHABLE
        }
        if (cycle) {
            return Integer.MAX_VALUE; // CYCLE
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
