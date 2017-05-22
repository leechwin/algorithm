package baekjoon_1395_switch;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static int M;

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        RMQ rmq = new RMQ();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            if (type == 0) {
                // update
                rmq.update(start - 1, end - 1, 1);
            } else {
                // query
                int result = rmq.query(start - 1, end - 1, 1);
                System.out.println(result);
            }
        }
    }

    static class RMQ {
        int n;
        int range[];
        boolean lazy[];

        public RMQ() {
            n = N;
            int size = getSize(n);
            range = new int[size];
            lazy = new boolean[size];
            init(1, 0, n - 1);
        }

        private int getSize(int n) {
            Double H = Math.ceil(Math.log(n) / Math.log(2.0));
            return 1 << (H.intValue() + 1);
        }

        private void init(int node, int nodeLeft, int nodeRight) {
            Arrays.fill(range, 0);
            Arrays.fill(lazy, false);
        }

        private void lazyUpdate(int node, int nodeLeft, int nodeRight) {
            if (lazy[node]) {
                range[node] = (nodeRight - nodeLeft + 1) - range[node];
                if (nodeLeft != nodeRight) {
                    lazy[node * 2] = !lazy[node * 2];
                    lazy[node * 2 + 1] = !lazy[node * 2 + 1];
                }
                lazy[node] = false;
            }
        }

        private void update(int left, int right, int node, int nodeLeft, int nodeRight) {
            lazyUpdate(node, nodeLeft, nodeRight);
            if (right < nodeLeft || nodeRight < left) {
                return;
            }
            if (left <= nodeLeft && nodeRight <= right) {
                range[node] = (nodeRight - nodeLeft + 1) - range[node];
                if (nodeLeft != nodeRight) {
                    lazy[node * 2] = !lazy[node * 2];
                    lazy[node * 2 + 1] = !lazy[node * 2 + 1];
                }
                return;
            }
            /*
            if (nodeLeft == nodeRight) {
                // TODO: apply lazy update
                range[node] = (nodeRight - nodeLeft + 1) - range[node];
                return;
            }
            */
            int mid = (nodeLeft + nodeRight) / 2;
            update(left, right, node * 2, nodeLeft, mid);
            update(left, right, node * 2 + 1, mid + 1, nodeRight);
            range[node] = range[node * 2] + range[node * 2 + 1];
        }

        public void update(int left, int right, int node) {
            update(left, right, node, 0, n - 1);
        }

        private int query(int left, int right, int node, int nodeLeft, int nodeRight) {
            lazyUpdate(node, nodeLeft, nodeRight);
            if (right < nodeLeft || nodeRight < left) {
                return 0;
            }
            if (left <= nodeLeft && nodeRight <= right) {
                return range[node];
            }

            int mid = (nodeLeft + nodeRight) / 2;
            int leftQuery = query(left, right, node * 2, nodeLeft, mid);
            int rightQuery = query(left, right, node * 2 + 1, mid + 1, nodeRight);
            return leftQuery + rightQuery;
        }

        public int query(int left, int right, int node) {
            return query(left, right, node, 0, n - 1);
        }
    }
}
