package algospot_TPATH;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int C;
    public static int N; // N <= 2000
    public static int M; // M <= 4000
    public static int p[];
    public static Node edges[];
    public static int weights[];
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
            p = new int[N];
            edges = new Node[M];
            weights = new int[M];
            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                edges[j] = new Node(u, v, cost);
                weights[j] = cost;
            }
            Arrays.sort(weights);
            Arrays.sort(edges);
            bw.write(minWeightDifference() + "\n");
            bw.flush();
        }
        bw.close();
        br.close();
    }

    // 0과 V-1을 연결하는 경로 중 가중치의 상한과 하한 차이의 최소값을 계산한다.
    public static int minWeightDifference() {
        int ret = INF;
        for (int i = 0; i < weights.length; i++) {
            ret = Math.min(ret, kruskalMinUpperBound(i) - weights[i]);
        }
        return ret;
    }

    public static int kruskalMinUpperBound(int low) {
        // init for union-find
        Arrays.fill(p, -1);
        for (int i = 0; i < edges.length; i++) {
            if (edges[i].cost < weights[low]) {
                continue;
            }
            merge(edges[i].u, edges[i].v);
            if (find(0) == find(N - 1)) {
                return edges[i].cost;
            }
        }
        return INF;
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
