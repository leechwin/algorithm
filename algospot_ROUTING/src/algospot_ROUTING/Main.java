package algospot_ROUTING;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static int C;
    public static int N;
    public static int M;
    public static LinkedList<Pair> ADJ[];
    public static double DIST[];

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        for (int i = 0; i < C; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // V
            M = Integer.parseInt(st.nextToken());

            DIST = new double[N];
            Arrays.fill(DIST, Double.MAX_VALUE);
            ADJ = new LinkedList[N];
            for (int j = 0; j < N; j++) {
                ADJ[j] = new LinkedList<Pair>();
            }

            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                double v = Double.parseDouble(st.nextToken());

                // 양방향그래프
                ADJ[start].add(new Pair(end, v));
                ADJ[end].add(new Pair(start, v));
            }

            dijkstra();

            System.out.printf("%.10f\n", DIST[N - 1]);
        }

    }

    private static void dijkstra() {
        DIST[0] = 1;
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
        pq.offer(new Pair(0, 1));
        while (!pq.isEmpty()) {
            Pair pair = pq.poll();
            int here = pair.here;
            double cost = pair.cost;

            if (DIST[here] < cost) {
                continue;
            }

            for (int i = 0; i < ADJ[here].size(); i++) {
                Pair next = ADJ[here].get(i);
                int there = next.here;
                double thereCost = cost * next.cost;
                if (DIST[there] > thereCost) {
                    DIST[there] = thereCost;
                    pq.offer(new Pair(there, thereCost));
                }
            }
        }
    }

    static class Pair implements Comparable<Pair> {
        public int here;
        public double cost;

        public Pair(int here, double cost) {
            this.here = here;
            this.cost = cost;
        }

        @Override
        public int compareTo(Pair other) {
            if (this.cost < other.cost) {
                return -1;
            }
            return 1;
        }
    }
}
