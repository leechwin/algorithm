package baekjoon_2357_minmax;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int N; // 정수 갯수
    public static int M; // a,b 쌍의 갯수
    public static long NUMBER[];

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        NUMBER = new long[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            NUMBER[i] = Long.parseLong(st.nextToken());
        }

        RMQ rmq = new RMQ();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            MinMax result = rmq.query(1, start - 1, end - 1);
            System.out.printf("%d %d\n", result.min, result.max);
        }
    }

    // Range Minimum Query
    static class RMQ {
        int n;
        MinMax range[];

        public RMQ() {
            this.n = N;
            this.range = new MinMax[getSize(n)];
            init(1, 0, n - 1);
        }

        private int getSize(int n) {
            Double H = Math.ceil((Math.log(n) / Math.log(2.0)));
            int size = 1 << (int) (H.intValue() + 1);
            return size;
        }

        private MinMax init(int node, int nodeLeft, int nodeRight) {
            if (nodeLeft == nodeRight) {
                return range[node] = new MinMax(NUMBER[nodeLeft], NUMBER[nodeLeft]);
            }

            int mid = (nodeLeft + nodeRight) / 2;
            MinMax leftResult = init(node * 2, nodeLeft, mid);
            MinMax rightResult = init(node * 2 + 1, mid + 1, nodeRight);
            return range[node] = new MinMax(Math.min(leftResult.min, rightResult.min), Math.max(leftResult.max, rightResult.max));
        }

        private MinMax query(int left, int right, int node, int nodeLeft, int nodeRight) {
            if (right < nodeLeft || nodeRight < left) {
                return new MinMax(Long.MAX_VALUE, Long.MIN_VALUE);
            }

            if (left <= nodeLeft && nodeRight <= right) {
                return range[node];
            }

            int mid = (nodeLeft + nodeRight) / 2;
            MinMax leftQuery = query(left, right, node * 2, nodeLeft, mid);
            MinMax rightQuery = query(left, right, node * 2 + 1, mid + 1, nodeRight);
            return new MinMax(Math.min(leftQuery.min, rightQuery.min), Math.max(leftQuery.max, rightQuery.max));
        }

        public MinMax query(int node, int left, int right) {
            return query(left, right, node, 0, n - 1);
        }

    }

    static class MinMax {
        public long min;
        public long max;

        public MinMax(long min, long max) {
            this.min = min;
            this.max = max;
        }
    }

}
