package algospot_TPATH;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static int C;
    public static int N; // N <= 2000
    public static int M; // M <= 4000
    public static int p[];
    public static PriorityQueue<Node> queue = new PriorityQueue<Node>();

    public static void main(String[] args) throws Exception {
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
            p = new int[N];
            Arrays.fill(p, -1);
            queue.clear();
            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                queue.offer(new Node(u, v, cost));
            }
            System.out.println(mst());
        }

    }

    private static int mst() {
        int min = -1;
        int max = -1;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int u = node.u;
            int v = node.v;
            int cost = node.cost;
            if (min == -1) {
                min = cost;
            }
            if (max == -1) {
                max = cost;
            }
            if (find(u) != find(v)) {
                merge(u, v);
                min = Math.min(min, cost);
                max = Math.max(max, cost);
            }
            if (find(0) == find(N - 1)) {
                break;
            }

        }

        return max - min;
    }

    private static void merge(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v) {
            return;
        }
        p[v] = u;
    }

    private static int find(int n) {
        if (p[n] < 0) {
            return n;
        }
        p[n] = find(p[n]);
        return p[n];
    }

    static class Node implements Comparable<Node> {
        int u;
        int v;
        int cost;

        public Node(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            if (this.cost > other.cost) {
                return 1;
            }
            return -1;
        }
    }
}
