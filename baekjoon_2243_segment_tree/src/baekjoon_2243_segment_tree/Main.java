package baekjoon_2243_segment_tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static int N; // n(1≤n≤100,000)
    public static int C = 1000000;

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        RMQ rmq = new RMQ();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            if (type == 1) {
                // query and remove
                int k = Integer.parseInt(st.nextToken());
                int num = rmq.query(k);
                bw.write(num + "\n");
                rmq.update(num, -1);
            } else {
                // update
                int k = Integer.parseInt(st.nextToken());
                int n = Integer.parseInt(st.nextToken());
                rmq.update(k, n);
            }
        }

        bw.close();
        br.close();
    }

    static class RMQ {
        int n;
        int range[];

        public RMQ() {
            n = C;
            range = new int[getSize(C)];
        }

        private int getSize(int n) {
            Double H = Math.ceil(Math.log(n) / Math.log(2.0));
            return 1 << (H.intValue() + 1);
        }

        private void update(int index, int diff, int node, int nodeLeft, int nodeRight) {
            if (index < nodeLeft || nodeRight < index) {
                return;
            }
            if (index <= nodeLeft && nodeRight <= index) {
                range[node] += diff;
                return;
            }

            int mid = (nodeLeft + nodeRight) / 2;
            update(index, diff, node * 2, nodeLeft, mid);
            update(index, diff, node * 2 + 1, mid + 1, nodeRight);
            range[node] = range[node * 2] + range[node * 2 + 1];
        }

        public void update(int index, int diff) {
            update(index, diff, 1, 0, n - 1);
        }

        private int query(int k, int node, int nodeLeft, int nodeRight) {
            if (nodeLeft == nodeRight) {
                return nodeLeft;
            }

            int mid = (nodeLeft + nodeRight) / 2;
            int leftK = range[node * 2];
            if (leftK < k) {
                // right node
                return query(k - leftK, node * 2 + 1, mid + 1, nodeRight);
            } else {
                // left node
                return query(k, node * 2, nodeLeft, mid);
            }
        }

        public int query(int k) {
            return query(k, 1, 0, n - 1);
        }
    }

}
