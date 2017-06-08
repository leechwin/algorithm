package baekjoon_6497_mst;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static int m; // 1 ¡Â m ¡Â 200000
    public static int n; // m-1 ¡Â n ¡Â 200000

    public static int p[];
    public static PriorityQueue<Node> queue = new PriorityQueue<Node>();

    public static void main(String[] args) throws Exception {
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            if (m == 0 && n == 0) {
                break;
            }
            p = new int[m];
            Arrays.fill(p, -1);
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                queue.offer(new Node(u, v, cost));
            }

            bw.write(mst() + "\n");
            bw.flush();
        }
        bw.close();
        br.close();
    }

    private static int mst() {
        int tot = 0;
        int result = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int u = node.u;
            int v = node.v;
            tot += node.cost;
            if (find(u) != find(v)) {
                merge(u, v);
                result += node.cost;
            }
        }
        return tot - result;
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
