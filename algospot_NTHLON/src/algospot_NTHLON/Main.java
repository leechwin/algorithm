package algospot_NTHLON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static int C;
    public static int M;
    public static int a[];
    public static int b[];
    public static ArrayList<Pair> adj[] = new ArrayList[410];

    public static int MAX = 9876543;

    public static void main(String[] args) throws Exception {
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 410; i++) {
            adj[i] = new ArrayList();
        }

        for (int i = 0; i < C; i++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            a = new int[M];
            b = new int[M];
            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                a[j] = Integer.parseInt(st.nextToken());
                b[j] = Integer.parseInt(st.nextToken());
            }

            Integer result = dijkstra();
            if (result == MAX) {
                bw.write("IMPOSSIBLE");
                bw.newLine();
            } else {
                bw.write(result.toString());
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();
    }

    private static int dijkstra() {
        int START = 401;
        for (int i = 0; i < 410; i++) {
            adj[i].clear();
        }

        for (int i = 0; i < M; i++) {
            int delta = a[i] - b[i];
            adj[START].add(new Pair(delta + 200, a[i]));
        }

        for (int delta = -200; delta <= 200; delta++) {
            for (int i = 0; i < M; i++) {
                int next = delta + a[i] - b[i];
                if (Math.abs(next) > 200) {
                    continue;
                }
                adj[delta + 200].add(new Pair(next + 200, a[i]));
            }
        }

        PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
        pq.add(new Pair(0, START));
        int distance[] = new int[410];
        Arrays.fill(distance, MAX);
        distance[START] = 0;
        while (!pq.isEmpty()) {
            Pair pair = pq.poll();
            int cost = -pair.delta;
            int here = pair.a;

            if (distance[here] < cost) {
                continue;
            }

            for (int i = 0; i < adj[here].size(); i++) {
                int there = adj[here].get(i).delta;
                int nextdist = cost + adj[here].get(i).a;
                if (distance[there] > nextdist) {
                    distance[there] = nextdist;
                    pq.add(new Pair(-distance[there], there));
                }
            }
        }

        return distance[0 + 200];
    }

    static class Pair implements Comparable<Pair> {
        public int delta;
        public int a;

        public Pair(int delta, int a) {
            this.delta = delta;
            this.a = a;
        }

        @Override
        public int compareTo(Pair pair) {
            if (this.delta > pair.delta) {
                return 1;
            }
            return -1;
        }
    }
}
