package baekjoon_10999_sumofrange2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static int M; // 변경 회수
    public static int K; // 구간합 횟수
    public static long NUMBER[];

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        NUMBER = new long[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            NUMBER[i] = Long.parseLong(st.nextToken());
        }

        RMQ rmq = new RMQ();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int kind = Integer.parseInt(st.nextToken());
            if (kind == 1) {
                // update
                int start = Integer.parseInt(st.nextToken()) - 1;
                int end = Integer.parseInt(st.nextToken()) - 1;
                long diff = Long.parseLong(st.nextToken());
                rmq.update(start, end, diff, 1);
            } else {
                // query
                int start = Integer.parseInt(st.nextToken()) - 1;
                int end = Integer.parseInt(st.nextToken()) - 1;
                System.out.println(rmq.query(start, end, 1));
            }
        }
    }

    static class RMQ {
        int n;
        long range[];
        long lazy[];

        public RMQ() {
            n = N;
            range = new long[getSize(n)];
            lazy = new long[getSize(n)];
            init(1, 0, n - 1);
        }

        private static int getSize(int n) {
            Double H = Math.ceil(Math.log(n) / Math.log(2.0));
            return 1 << (H.intValue() + 1);
        }

        private long init(int node, int nodeLeft, int nodeRight) {
            if (nodeLeft == nodeRight) {
                return range[node] = NUMBER[nodeLeft];
            }
            int mid = (nodeLeft + nodeRight) / 2;
            long leftSum = init(node * 2, nodeLeft, mid);
            long rightSum = init(node * 2 + 1, mid + 1, nodeRight);
            return range[node] = leftSum + rightSum;
        }

        private long query(int left, int right, int node, int nodeLeft, int nodeRight) {
            lazyUpdate(node, nodeLeft, nodeRight);
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
            return query(left, right, node, 0, n - 1);
        }

        private void lazyUpdate(int node, int nodeLeft, int nodeRight) {
            if (lazy[node] != 0) {
                range[node] += (nodeRight - nodeLeft + 1) * lazy[node];

                // leaf node 가 아니면 child lazy 값을 업데이트
                if (nodeLeft != nodeRight) {
                    lazy[node * 2] += lazy[node];
                    lazy[node * 2 + 1] += lazy[node];
                }
                lazy[node] = 0;
            }
        }

        private void update(int left, int right, long diff, int node, int nodeLeft, int nodeRight) {
            lazyUpdate(node, nodeLeft, nodeRight);
            if (right < nodeLeft || nodeRight < left) {
                return;
            }

            if (left <= nodeLeft && nodeRight <= right) {
                range[node] += (nodeRight - nodeLeft + 1) * diff;
                if (nodeLeft != nodeRight) {
                    lazy[node * 2] += diff;
                    lazy[node * 2 + 1] += diff;
                }
                return;
            }

            int mid = (nodeLeft + nodeRight) / 2;
            update(left, right, diff, node * 2, nodeLeft, mid);
            update(left, right, diff, node * 2 + 1, mid + 1, nodeRight);
            range[node] = range[node * 2] + range[node * 2 + 1];
        }

        public void update(int left, int right, long diff, int node) {
            update(left, right, diff, node, 0, n - 1);
        }
    }
}
