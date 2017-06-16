package algospot_FIRETRUCKS;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static int C;
    public static int V;
    public static int E;
    public static int n; // 화재장소 수
    public static int m; // 소방서 수

    public static LinkedList<Vertex> ADJ[];
    public static int DIST[];

    public static int[] N;
    public static int[] M;

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        for (int i = 0; i < C; i++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            DIST = new int[V + 1];
            Arrays.fill(DIST, Integer.MAX_VALUE);

            ADJ = new LinkedList[V + 1];
            for (int j = 0; j <= V; j++) {
                ADJ[j] = new LinkedList<>();
            }
            for (int j = 0; j < E; j++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                ADJ[start].add(new Vertex(end, cost));
                ADJ[end].add(new Vertex(start, cost));
            }

            N = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                N[j] = Integer.parseInt(st.nextToken());
            }

            M = new int[m];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                M[j] = Integer.parseInt(st.nextToken());
            }

            dijkstra();
            int result = 0;
            for (int j = 0; j < n; j++) {
                result += DIST[N[j]];
            }
            System.out.println(result);
        }

    }

    private static void dijkstra() {
        PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
        for (int i = 0; i < m; i++) {
            DIST[M[i]] = 0;
            pq.offer(new Vertex(M[i], 0));
        }

        while (!pq.isEmpty()) {
            Vertex vertex = pq.poll();
            int here = vertex.here;
            int cost = vertex.cost;
            if (DIST[here] < cost) {
                continue;
            }

            for (int i = 0; i < ADJ[here].size(); i++) {
                Vertex thereVertex = ADJ[here].get(i);
                int there = thereVertex.here;
                int thereCost = cost + thereVertex.cost;
                if (DIST[there] > thereCost) {
                    DIST[there] = thereCost;
                    pq.offer(new Vertex(there, thereCost));
                }
            }
        }
    }

    static class Vertex implements Comparable<Vertex> {
        public int here;
        public int cost;

        public Vertex(int here, int cost) {
            this.here = here;
            this.cost = cost;
        }

        @Override
        public int compareTo(Vertex othere) {
            if (this.cost > othere.cost) {
                return 1;
            }
            return -1;
        }

    }
}
