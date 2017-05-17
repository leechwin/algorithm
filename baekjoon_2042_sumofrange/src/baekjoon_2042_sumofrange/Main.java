package baekjoon_2042_sumofrange;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int N; // 수의 개수
    public static int M; // 수의 변경이 일어나는 회수
    public static int K; // 구간합을 구하는 회수
    public static long NN[];

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        NN = new long[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            NN[i] = Integer.parseInt(st.nextToken());
        }

        RMQ rmq = new RMQ();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (type == 1) {
                // update
                long diff = b - NN[a - 1];
                NN[a - 1] = b;
                rmq.update(a - 1, diff);
            } else {
                // query
                System.out.println(rmq.query(a - 1, b - 1, 1));
            }
        }
    }

    // Range Minimum Query
    static class RMQ {
        public int n;
        public long range[];

        public RMQ() {
            this.n = N;
            this.range = new long[getSize(N)];
            init(0, N - 1, 1);
        }

        private long init(int nodeLeft, int nodeRight, int node) {
            if (nodeLeft == nodeRight) {
                return range[node] = NN[nodeLeft];
            }
            int mid = (nodeLeft + nodeRight) / 2;
            long leftSum = init(nodeLeft, mid, node * 2);
            long rightSum = init(mid + 1, nodeRight, node * 2 + 1);
            return range[node] = leftSum + rightSum;
        }

        private int getSize(int n) {
            Double H = (Math.log(n) / Math.log(2.0));
            H = Math.ceil(H);
            Double size = Math.pow(2, H + 1) - 1;
            return size.intValue();
        }

        private long query(int left, int right, int node, int nodeLeft, int nodeRight) {
            if (right < nodeLeft || nodeRight < left) {
                return 0;
            }
            if (left <= nodeLeft && nodeRight <= right) {
                return range[node];
            }
            int mid = (nodeLeft + nodeRight) / 2;
            long leftQuery = query(left, right, node * 2, nodeLeft, mid);
            long rightQuery = query(left, right, node * 2 + 1, mid + 1, nodeRight);
            return leftQuery + rightQuery;
        }

        public long query(int left, int right, int node) {
            return query(left, right, node, 0, N - 1);
        }

        private void update(int index, long diff, int node, int nodeLeft, int nodeRight) {
            if (index < nodeLeft || nodeRight < index) {
                return;
            }

            // leaf node
            if (nodeLeft == nodeRight) {
                range[node] = NN[index];
                return;
            }

            range[node] = range[node] + diff;
            int mid = (nodeLeft + nodeRight) / 2;
            update(index, diff, node * 2, nodeLeft, mid);
            update(index, diff, node * 2 + 1, mid + 1, nodeRight);
        }

        public void update(int index, long diff) {
            update(index, diff, 1, 0, N - 1);
        }
    }

}
