package baekjoon_10868_valueofmin;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static int M;
    public static int NUMBER[];

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        NUMBER = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            NUMBER[i] = Integer.parseInt(st.nextToken());
        }
        RMQ rmq = new RMQ();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int result = rmq.query(start, end, 1);
            System.out.println(result);
        }

    }

    // Range Minimum Query
    static class RMQ {
        public int n;
        public int range[];

        public RMQ() {
            n = N;
            range = new int[getSize(n)];
            init(1, n, 1);
        }

        private int getSize(int n) {
            return 1 << (int) (Math.ceil(Math.log(n) / Math.log(2.0)) + 1);
        }

        private int init(int nodeLeft, int nodeRight, int node) {
            if (nodeLeft == nodeRight) {
                return range[node] = NUMBER[nodeLeft];
            }
            int mid = (nodeLeft + nodeRight) / 2;
            int leftMin = init(nodeLeft, mid, node * 2);
            int rightMin = init(mid + 1, nodeRight, node * 2 + 1);
            return range[node] = Math.min(leftMin, rightMin);
        }

        private int query(int left, int right, int node, int nodeLeft, int nodeRight) {
            if (right < nodeLeft || nodeRight < left) {
                return Integer.MAX_VALUE;
            }
            if (left <= nodeLeft && nodeRight <= right) {
                return range[node];
            }
            int mid = (nodeLeft + nodeRight) / 2;
            int leftMin = query(left, right, node * 2, nodeLeft, mid);
            int rightMin = query(left, right, node * 2 + 1, mid + 1, nodeRight);
            return Math.min(leftMin, rightMin);
        }

        public int query(int left, int right, int node) {
            return query(left, right, node, 1, n);
        }
    }
}
