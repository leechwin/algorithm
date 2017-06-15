package baekjoon_12844_seg_lazy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static int N; // 0 < n ≤ 500,000
    public static int NUM[];
    public static int M; // 0 < n ≤ 500,000

    public static void main(String[] args) throws Exception {
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        NUM = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            NUM[i] = Integer.parseInt(st.nextToken());
        }

        RMQ rmq = new RMQ();

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            if (type == 1) {
                // update
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                if (a > b) {
                    rmq.update(b, a, c);
                } else {
                    rmq.update(a, b, c);
                }
            } else {
                // query
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int result = 0;
                if (a > b) {
                    result = rmq.query(b, a);
                } else {
                    result = rmq.query(a, b);
                }
                bw.write(result + "\n");
                bw.flush();
            }
        }

        bw.close();
        br.close();
    }

    static class RMQ {
        int n;
        int range[];
        int lazy[];

        public RMQ() {
            this.n = N;
            int size = getSize(n);
            this.range = new int[size];
            this.lazy = new int[size];
            init(1, 0, n - 1);
        }

        private int getSize(int n) {
            Double H = Math.ceil(Math.log(n) / Math.log(2.0));
            return 1 << (H.intValue() + 1);
        }

        private int init(int node, int nodeLeft, int nodeRight) {
            if (nodeLeft == nodeRight) {
                return range[node] = NUM[nodeLeft];
            }
            int mid = (nodeLeft + nodeRight) / 2;
            int leftInit = init(node * 2, nodeLeft, mid);
            int rightInit = init(node * 2 + 1, mid + 1, nodeRight);
            return range[node] = leftInit ^ rightInit;
        }

        private int query(int left, int right, int node, int nodeLeft, int nodeRight) {
            lazy(node, nodeLeft, nodeRight);
            if (right < nodeLeft || nodeRight < left) {
                return 0;
            }
            if (left <= nodeLeft && nodeRight <= right) {
                return range[node];
            }
            int mid = (nodeLeft + nodeRight) / 2;
            int leftQuery = query(left, right, node * 2, nodeLeft, mid);
            int rightQuery = query(left, right, node * 2 + 1, mid + 1, nodeRight);
            return leftQuery ^ rightQuery;
        }

        public int query(int left, int right) {
            return query(left, right, 1, 0, n - 1);
        }

        private void lazy(int node, int nodeLeft, int nodeRight) {
            if (lazy[node] != 0) {
                if ((nodeRight - nodeLeft + 1) % 2 == 1) {
                    range[node] ^= lazy[node];
                }
                // not leaf node
                if (nodeLeft != nodeRight) {
                    lazy[node * 2] ^= lazy[node];
                    lazy[node * 2 + 1] ^= lazy[node];
                }
                lazy[node] = 0;
            }
        }

        private void update(int left, int right, int diff, int node, int nodeLeft, int nodeRight) {
            lazy(node, nodeLeft, nodeRight);
            if (right < nodeLeft || nodeRight < left) {
                return;
            }
            if (left <= nodeLeft && nodeRight <= right) {
                if ((nodeRight - nodeLeft + 1) % 2 == 1) {
                    range[node] ^= diff;
                }
                if (nodeLeft != nodeRight) {
                    lazy[node * 2] ^= diff;
                    lazy[node * 2 + 1] ^= diff;
                }
                return;
            }
            int mid = (nodeLeft + nodeRight) / 2;
            update(left, right, diff, node * 2, nodeLeft, mid);
            update(left, right, diff, node * 2 + 1, mid + 1, nodeRight);
            range[node] = range[node * 2] ^ range[node * 2 + 1];
        }

        public void update(int left, int right, int diff) {
            update(left, right, diff, 1, 0, n - 1);
        }
    }
}
