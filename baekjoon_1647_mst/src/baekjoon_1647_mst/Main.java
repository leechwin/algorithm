package baekjoon_1647_mst;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static int N; // N은 2이상 100,000이하
    public static int M; // M은 1이상 1,000,000이하

    public static int p[];
    public static PriorityQueue<Node> queue = new PriorityQueue<Node>();

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
        p = new int[N + 1];
        Arrays.fill(p, -1);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            queue.offer(new Node(u, v, cost));
        }
        bw.write(mst() + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int mst() {
        int result = 0;
        int cnt = 0;
        while (!queue.isEmpty()) {
            if (cnt == N - 2) {
                break;
            }
            Node node = queue.poll();
            int u = node.u;
            int v = node.v;
            if (find(u) != find(v)) {
                merge(u, v);
                result += node.cost;
                cnt++;
            }
        }
        return result;
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

        Node(int u, int v, int cost) {
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
