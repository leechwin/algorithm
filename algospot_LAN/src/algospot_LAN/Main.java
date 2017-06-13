package algospot_LAN;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static int C;
    public static int N; // N <= 500
    public static int M; // M <= 2000

    public static int X[];
    public static int Y[];
    public static int p[];
    public static PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>();

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
            X = new int[N];
            Y = new int[N];
            p = new int[N];
            Arrays.fill(p, -1);
            priorityQueue.clear();
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                X[j] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                Y[j] = Integer.parseInt(st.nextToken());
            }
            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                double cost = 0;
                priorityQueue.add(new Node(u, v, cost));
            }

            for (int j = 0; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    int x1 = X[j];
                    int x2 = X[k];
                    if (x1 > x2) {
                        x1 = X[k];
                        x2 = X[j];
                    }
                    int y1 = Y[j];
                    int y2 = Y[k];
                    if (y1 > y2) {
                        y1 = Y[k];
                        y2 = Y[j];
                    }
                    double cost = dist(x2 - x1, y2 - y1);
                    priorityQueue.offer(new Node(j, k, cost));
                }
            }

            bw.write(mst() + "\n");
            bw.flush();
        }
        bw.close();
        br.close();
    }

    private static double mst() {
        int cnt = 0;
        double result = 0;
        while (!priorityQueue.isEmpty()) {
            Node node = priorityQueue.poll();
            int u = node.u;
            int v = node.v;
            if (find(u) != find(v)) {
                cnt++;
                merge(u, v);
                result += node.cost;
                if (cnt == N - 1) {
                    break;
                }
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

    private static double dist(int x, int y) {
        return Math.sqrt((x * x) + (y * y));
    }

    static class Node implements Comparable<Node> {
        int u;
        int v;
        double cost;

        public Node(int u, int v, double cost) {
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
