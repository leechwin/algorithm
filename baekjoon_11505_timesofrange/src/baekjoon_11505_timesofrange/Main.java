package baekjoon_11505_timesofrange;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static long MOD = 1000000007;
    public static int N; // 1<=N<=1,000,000
    public static int M; // 1<=M<=10,000
    public static int K; // 1<=K<=10,000
    public static int NN[];

    public static void main(String[] args) throws Exception {
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        NN = new int[N];
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
                rmq.update(a - 1, b, 1);
            } else {
                // print
                Long result = rmq.query(a - 1, b - 1, 1);
                bw.write(result.toString());
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();
    }

    static class RMQ {
        int n;
        long range[];

        public RMQ() {
            n = N;
            range = new long[getSize(n)];
            init(1, 0, n - 1);
        }

        private int getSize(int n) {
            Double H = Math.ceil(Math.log(n) / Math.log(2.0));
            return 1 << (H.intValue() + 1);
        }

        private long init(int node, int nodeLeft, int nodeRight) {
            if (nodeLeft == nodeRight) {
                return range[node] = NN[nodeLeft];
            }
            int mid = (nodeLeft + nodeRight) / 2;
            long leftInit = init(node * 2, nodeLeft, mid);
            long rightInit = init(node * 2 + 1, mid + 1, nodeRight);
            return range[node] = (leftInit * rightInit) % MOD;
        }

        private void update(int index, int value, int node, int nodeLeft, int nodeRight) {
            if (index < nodeLeft || nodeRight < index) {
                return;
            }

            if (nodeLeft == nodeRight) {
                range[node] = value;
                return;
            }
            int mid = (nodeLeft + nodeRight) / 2;
            update(index, value, node * 2, nodeLeft, mid);
            update(index, value, node * 2 + 1, mid + 1, nodeRight);
            range[node] = (range[node * 2] * range[node * 2 + 1]) % MOD;
        }

        public void update(int index, int value, int node) {
            update(index, value, node, 0, n - 1);
        }

        private long query(int left, int right, int node, int nodeLeft, int nodeRight) {
            if (right < nodeLeft || nodeRight < left) {
                return 1;
            }
            if (left <= nodeLeft && nodeRight <= right) {
                return range[node];
            }
            int mid = (nodeLeft + nodeRight) / 2;
            long leftQuery = query(left, right, node * 2, nodeLeft, mid);
            long rightQuery = query(left, right, node * 2 + 1, mid + 1, nodeRight);
            return leftQuery * rightQuery % MOD;
        }

        public long query(int left, int right, int node) {
            return query(left, right, node, 0, n - 1);
        }
    }

}
