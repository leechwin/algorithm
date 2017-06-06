package baekjoon_1922_network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static int N; // 1<=N<=1000
    public static int M; // 1<=M<=100,000

    public static PriorityQueue<Node> queue;
    public static int p[];

    public static void main(String[] args) throws Exception {
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        queue = new PriorityQueue<Node>();
        p = new int[N + 1];
        Arrays.fill(p, -1);
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            queue.offer(new Node(a, b, cost));
        }
        int result = mst();
        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int mst() {
        int result = 0;
        int count = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (merge(node.u, node.v)) {
                result += node.cost;
                count++;
                if (count == N) {
                    break;
                }
            }
        }
        return result;
    }

    private static boolean merge(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v) {
            return false;
        }
        p[u] = v;
        return true;
    }

    private static int find(int n) {
        if (p[n] < 0) {
            return n;
        }
        return p[n] = find(p[n]);
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
            } else {
                return -1;
            }
        }
    }
}
