package baekjoon_3653_dvd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static int T;
    public static int N;
    public static int M;
    public static int index[];

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            index = new int[N + 1];

            RMQ rmq = new RMQ();
            // init
            for (int i = 1; i < N + 1; i++) {
                // 쿼리만큼의 여유공간을 확보하고, 세그먼트트리 업데이트
                index[i] = M + i;
                rmq.update(index[i], 1);
            }

            // query
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                int dvdNum = Integer.parseInt(st.nextToken());
                // 0 ~ (index-1)
                int result = rmq.query(0, index[dvdNum] - 1);
                bw.write(result + " ");
                rmq.update(index[dvdNum], -1);
                index[dvdNum] = M - m;
                rmq.update(index[dvdNum], 1);
            }
            bw.write("\n");
        }

        br.close();
        bw.close();
    }

    static class RMQ {
        public int n;
        public int range[];

        public RMQ() {
            this.n = N + M;
            this.range = new int[getSize(n)];
        }

        private int getSize(int n) {
            Double H = Math.ceil(Math.log(n) / Math.log(2.0));
            return 1 << (H.intValue() + 1);
        }

        private void update(int left, int right, int diff, int node, int nodeLeft, int nodeRight) {
            if (right < nodeLeft || nodeRight < left) {
                return;
            }
            if (left <= nodeLeft && nodeRight <= right) {
                range[node] += diff;
                return;
            }
            int mid = (nodeLeft + nodeRight) / 2;
            update(left, right, diff, node * 2, nodeLeft, mid);
            update(left, right, diff, node * 2 + 1, mid + 1, nodeRight);
            range[node] = range[node * 2] + range[node * 2 + 1];
        }

        public void update(int index, int diff) {
            update(index, index, diff, 1, 0, n - 1);
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

        public int query(int left, int right) {
            return query(left, right, 1, 0, n - 1);
        }

    }
}
