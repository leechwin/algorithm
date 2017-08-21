package segment_tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static int T;
    public static int N = 100000; // 1 <= N <= 100,000
    public static int Q; // 1 <= Q <= 300,000

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        float start = System.currentTimeMillis();

        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= T; i++) {
            bw.write("#" + i);
            RMQ rmq = new RMQ(N);
            st = new StringTokenizer(br.readLine());
            int Q = Integer.parseInt(st.nextToken());
            for (int q = 0; q < Q; q++) {
                st = new StringTokenizer(br.readLine());
                int type = Integer.parseInt(st.nextToken());
                int num = Integer.parseInt(st.nextToken());
                if (type == 1) {
                    // add
                    rmq.update(num, 1, 1, 0, N - 1);
                } else {
                    // query and remove
                    int result = rmq.query(num, 1, 0, N - 1);
                    bw.write(" " + result);
                    rmq.update(num, -1, 1, 0, N - 1);
                }
            }
            bw.write("\n");
        }

        float end = System.currentTimeMillis();
        bw.write("time: " + ((end - start) / 1000.0) + " sec.");

        br.close();
        bw.close();
    }

    static class RMQ {
        int n;
        int range[];

        public RMQ(int n) {
            this.n = n;
            range = new int[getSize(n)];
        }

        public int getSize(int n) {
            Double H = Math.ceil(Math.log(n) / Math.log(2.0));
            return 1 << (H.intValue() + 1);
        }

        public void update(int index, int diff, int node, int nodeLeft, int nodeRight) {
            if (index < nodeLeft || nodeRight < index) {
                return;
            }
            range[node] += diff;
            if (nodeLeft != nodeRight) {
                int mid = (nodeLeft + nodeRight) / 2;
                update(index, diff, node * 2, nodeLeft, mid);
                update(index, diff, node * 2 + 1, mid + 1, nodeRight);
                range[node] = range[node * 2] + range[node * 2 + 1];
            }
        }

        public int query(int k, int node, int nodeLeft, int nodeRight) {
            if (nodeLeft == nodeRight) {
                return nodeLeft;
            }
            int mid = (nodeLeft + nodeRight) / 2;
            int leftK = range[node * 2];

            if (leftK < k) {
                // right
                return query(k - leftK, node * 2 + 1, mid + 1, nodeRight);
            } else {
                // left
                return query(k, node * 2, nodeLeft, mid);
            }
        }
    }

}
