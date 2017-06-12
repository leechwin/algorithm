package baekjoon_11438;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static int M;
    public static ArrayList<Integer> ADJ[];

    public static int no2serial[] = new int[1000000];
    public static int serial2no[] = new int[1000000];
    public static int nextSerial = 0;
    public static ArrayList<Integer> trip = new ArrayList<>();
    public static int locInTrip[] = new int[1000000];
    public static int depth[] = new int[1000000];
    public static boolean visited[] = new boolean[1000000];

    public static void main(String[] args) throws Exception {
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        ADJ = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            ADJ[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ADJ[a].add(b);
            ADJ[b].add(a);
        }

        dfs(1, 0, trip);
        RMQ rmq = new RMQ();

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int lu = locInTrip[a];
            int lv = locInTrip[b];
            int lca;
            if (lu > lv) {
                lca = serial2no[rmq.query(lv, lu)];
            } else {
                lca = serial2no[rmq.query(lu, lv)];
            }

            bw.write(lca + "\n");
            bw.flush();
        }
        bw.close();
        br.close();
    }

    private static void dfs(int here, int d, ArrayList<Integer> trip) {
        no2serial[here] = nextSerial;
        serial2no[nextSerial] = here;
        nextSerial++;
        visited[here] = true;

        depth[here] = d;
        locInTrip[here] = trip.size();
        trip.add(no2serial[here]);

        for (int i = 0; i < ADJ[here].size(); i++) {
            if (!visited[ADJ[here].get(i)]) {
                dfs(ADJ[here].get(i), d + 1, trip);
                trip.add(no2serial[here]);
            }
        }
    }

    static class RMQ {
        int n;
        int range[];

        public RMQ() {
            n = trip.size();
            range = new int[getSize(n)];
            init(1, 0, n - 1);
        }

        private int getSize(int n) {
            Double H = Math.ceil(Math.log(n) / Math.log(2.0));
            return 1 << (H.intValue() + 1);
        }

        private int init(int node, int nodeLeft, int nodeRight) {
            if (nodeLeft == nodeRight) {
                return range[node] = trip.get(nodeLeft);
            }
            int mid = (nodeLeft + nodeRight) / 2;
            int leftInit = init(node * 2, nodeLeft, mid);
            int rightInit = init(node * 2 + 1, mid + 1, nodeRight);
            return range[node] = Math.min(leftInit, rightInit);
        }

        private int query(int left, int right, int node, int nodeLeft, int nodeRight) {
            if (right < nodeLeft || nodeRight < left) {
                return Integer.MAX_VALUE;
            }
            if (left <= nodeLeft && nodeRight <= right) {
                return range[node];
            }
            int mid = (nodeLeft + nodeRight) / 2;
            int leftQuery = query(left, right, node * 2, nodeLeft, mid);
            int rightQuery = query(left, right, node * 2 + 1, mid + 1, nodeRight);
            return Math.min(leftQuery, rightQuery);
        }

        public int query(int left, int right) {
            return query(left, right, 1, 0, n - 1);
        }
    }

}
