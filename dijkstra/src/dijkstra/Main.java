package dijkstra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static int T;
    public static int N; // 1 <= N <= 100
    public static int map[][];

    public static int dist[][];
    public static boolean visited[][];
    public static int INF = 987654321;

    public static PriorityQueue<Node> pq = new PriorityQueue<Node>();

    public static void main(String[] args) throws Exception {
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            for (int j = 0; j < N; j++) {
                String row = br.readLine();
                for (int k = 0; k < N; k++) {
                    map[j][k] = Integer.parseInt(String.valueOf(row.charAt(k)));
                }
            }
            dist = new int[N][N];
            for (int j = 0; j < N; j++) {
                Arrays.fill(dist[j], INF);
            }
            visited = new boolean[N][N];

            dijkstra();

            bw.write("#" + i + " " + dist[N - 1][N - 1] + "\n");
            bw.flush();
        }
        bw.close();
        br.close();
    }

    private static void dijkstra() {
        dist[0][0] = map[0][0];
        pq.clear();
        pq.offer(new Node(0, 0, map[0][0]));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int row = node.row;
            int col = node.col;
            int cost = node.cost;
            if (dist[row][col] < cost) {
                continue;
            }

            if (row - 1 >= 0) {
                Node next = new Node(row - 1, col, map[row - 1][col]);
                if (dist[next.row][next.col] > dist[row][col] + next.cost) {
                    dist[next.row][next.col] = dist[row][col] + next.cost;
                    pq.offer(next);
                }
            }
            if (row + 1 < N) {
                Node next = new Node(row + 1, col, map[row + 1][col]);
                if (dist[next.row][next.col] > dist[row][col] + next.cost) {
                    dist[next.row][next.col] = dist[row][col] + next.cost;
                    pq.offer(next);
                }
            }
            if (col - 1 >= 0) {
                Node next = new Node(row, col - 1, map[row][col - 1]);
                if (dist[next.row][next.col] > dist[row][col] + next.cost) {
                    dist[next.row][next.col] = dist[row][col] + next.cost;
                    pq.offer(next);
                }
            }
            if (col + 1 < N) {
                Node next = new Node(row, col + 1, map[row][col + 1]);
                if (dist[next.row][next.col] > dist[row][col] + next.cost) {
                    dist[next.row][next.col] = dist[row][col] + next.cost;
                    pq.offer(next);
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        public int row;
        public int col;
        public int cost;

        public Node(int row, int col, int cost) {
            this.row = row;
            this.col = col;
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
