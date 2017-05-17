package algospot_MORDOR;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int C;
    public static int N; // 등산로 표지판수
    public static int Q; // 등산로 수
    public static int H[];

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
            Q = Integer.parseInt(st.nextToken());
            H = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                H[j] = Integer.parseInt(st.nextToken());
            }
            RMQ rmq = new RMQ();
            for (int j = 0; j < Q; j++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());

                Level result = rmq.query(start, end, 1);
                System.out.println(result.high - result.low);
            }
        }

    }

    public static int getHeight(int x) {
        Double result = Math.log(x) / Math.log(2.0);
        result = Math.ceil(result);
        Double len = Math.pow(2, result + 1) - 1;
        return len.intValue();
    }

    static class RMQ {
        public int n;
        public Level range[];

        public RMQ() {
            n = N;
            range = new Level[getHeight(N)];
            init(0, N - 1, 1);
        }

        private Level init(int nodeLeft, int nodeRight, int node) {
            if (nodeLeft == nodeRight) {
                return range[node] = new Level(H[nodeLeft], H[nodeLeft]);
            }

            int mid = (nodeLeft + nodeRight) / 2;
            Level leftLevel = init(nodeLeft, mid, node * 2);
            Level rightLevel = init(mid + 1, nodeRight, node * 2 + 1);
            return range[node] = new Level(Math.max(leftLevel.high, rightLevel.high), Math.min(leftLevel.low, rightLevel.low));
        }

        private Level query(int left, int right, int node, int nodeLeft, int nodeRight) {
            if (right < nodeLeft || nodeRight < left) {
                return new Level(0, Integer.MAX_VALUE);
            }
            if (left <= nodeLeft && nodeRight <= right) {
                return range[node];
            }

            int mid = (nodeLeft + nodeRight) / 2;
            Level leftLevel = query(left, right, node * 2, nodeLeft, mid);
            Level rightLevel = query(left, right, node * 2 + 1, mid + 1, nodeRight);
            return new Level(Math.max(leftLevel.high, rightLevel.high), Math.min(leftLevel.low, rightLevel.low));
        }

        public Level query(int left, int right, int node) {
            return query(left, right, node, 0, n - 1);
        }
    }

    static class Level {
        public int high;
        public int low;

        public Level(int high, int low) {
            this.high = high;
            this.low = low;
        }
    }

}
