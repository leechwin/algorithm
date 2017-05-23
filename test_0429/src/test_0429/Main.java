package test_0429;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int C;
    public static int N; // 과녘수: 1 <= N <=100,000
    public static int M; // 쏜사람수: 1<= N <=100,000
    public static int NN[];
    public static int MM[];

    public static void main(String[] args) throws Exception {
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
            NN = new int[N];
            Arrays.fill(NN, 1);
            MM = new int[M];
            Arrays.fill(MM, 0);
            RMQ rmq = new RMQ();
            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                MM[j] = rmq.query(start - 1, end - 1, 1);
                rmq.update(start - 1, end - 1, 1);
            }
            long result = MM[0];
            for (int j = 1; j < M; j++) {
                if (j % 2 == 1) {
                    result -= MM[j];
                } else {
                    result += MM[j];
                }
            }
            System.out.println(result);
        }
    }

    static class RMQ {
        int n;
        int range[];

        public RMQ() {
            n = N;
            range = new int[getSize(n)];
            init(1, 0, n - 1);
        }

        private int getSize(int n) {
            Double H = Math.ceil(Math.log(n) / Math.log(2.0));
            return 1 << (H.intValue() + 1);
        }

        private int init(int node, int nodeLeft, int nodeRight) {
            if (nodeLeft == nodeRight) {
                return range[node] = NN[nodeLeft];
            }
            int mid = (nodeLeft + nodeRight) / 2;
            int leftInit = init(node * 2, nodeLeft, mid);
            int rightInit = init(node * 2 + 1, mid + 1, nodeRight);
            return range[node] = leftInit + rightInit;
        }

        private void update(int left, int right, int node, int nodeLeft, int nodeRight) {
            if (right < nodeLeft || nodeRight < left) {
                return;
            }
            if (left <= nodeLeft && nodeRight <= right) {
                range[node] = 0;
                if (nodeLeft != nodeRight) {
                    range[node * 2] = range[node * 2 + 1] = 0;
                }
                return;
            }
            int mid = (nodeLeft + nodeRight) / 2;
            update(left, right, node * 2, nodeLeft, mid);
            update(left, right, node * 2 + 1, mid + 1, nodeRight);
            range[node] = range[node * 2] + range[node * 2 + 1];
        }

        public void update(int left, int right, int node) {
            update(left, right, node, 0, n - 1);
        }

        private int query(int left, int right, int node, int nodeLeft, int nodeRight) {
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
