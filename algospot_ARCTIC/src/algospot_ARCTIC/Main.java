package algospot_ARCTIC;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static int C;
    public static int N;

    public static double X[];
    public static double Y[];
    public static double DIST[][];
    public static double MAX_DIST;
    public static int parent[];
    public static int rank[];
    public static PriorityQueue<Node> pqueue = new PriorityQueue<>();

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

            X = new double[N];
            Y = new double[N];
            parent = new int[N];
            Arrays.fill(parent, -1);
            rank = new int[N];
            Arrays.fill(rank, -1);
            DIST = new double[N][N];

            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                X[j] = Double.parseDouble(st.nextToken());
                Y[j] = Double.parseDouble(st.nextToken());
            }

            calcDist();
            mst();
            System.out.printf("%.2f\n", MAX_DIST);
        }
    }

    private static void mst() {
        pqueue.clear();
        for (int i = 0; i < N; i++) {
            pqueue.offer(new Node(0, i, DIST[0][i]));
        }
        MAX_DIST = 0;

        int cnt = 0;
        while (!pqueue.isEmpty()) {
            if (cnt == N - 1) {
                break;
            }
            Node node = pqueue.poll();
            if (find(node.u) != find(node.v)) {
                merge(node.u, node.v);
                if (MAX_DIST < node.dist) {
                    MAX_DIST = node.dist;
                }
                for (int i = 0; i < N; i++) {
                    pqueue.offer(new Node(node.v, i, DIST[node.v][i]));
                }
                cnt++;
            }
        }
    }

    private static void merge(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v) {
            return;
        }
        // 부모가될 v 가 더 크게 조정
        if (rank[u] > rank[v]) {
            int tmp = u;
            u = v;
            v = tmp;
        }
        if (rank[u] == rank[v]) {
            rank[v]++;
        }

        parent[u] = v;
    }

    private static int find(int u) {
        if (parent[u] == -1) {
            return u;
        }
        parent[u] = find(parent[u]);
        return parent[u];
    }

    private static void calcDist() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                DIST[i][j] = getRadius(X[i], X[j], Y[i], Y[j]);
            }
        }
    }

    private static double getRadius(double x1, double x2, double y1, double y2) {
        return Math.sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
    }

    static class Node implements Comparable<Node> {
        public int u;
        public int v;
        public double dist;

        public Node(int u, int v, double dist) {
            this.u = u;
            this.v = v;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node other) {
            if (this.dist > other.dist) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
