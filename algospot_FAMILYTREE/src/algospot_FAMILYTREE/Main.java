package algospot_FAMILYTREE;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static int C;
    public static int N; // 사람의 수 N (1 <= N <= 100,000)
    public static int Q; // 촌수를 계산할 두 사람의 수 Q (1 <= Q <= 10,000)
    public static ArrayList<Integer> ADJ[];
    public static int a;
    public static int b;

    public static void main(String[] args) throws Exception {
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

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
            ADJ = new ArrayList[N];
            ADJ[0] = new ArrayList<Integer>();
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N; j++) {
                int parent = Integer.parseInt(st.nextToken());
                if (ADJ[parent] == null) {
                    ADJ[parent] = new ArrayList<Integer>();
                }
                ADJ[parent].add(j);
            }

            no2serial = new int[N];
            serial2no = new int[N];
            locInTrip = new int[N];
            depth = new int[N];
            nextSerial = 0;

            ArrayList<Integer> trip = new ArrayList<Integer>();
            dfs(0, 0, trip);
            RMQ rmq = new RMQ(trip);
            for (int j = 0; j < Q; j++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                int u = locInTrip[a];
                int v = locInTrip[b];
                int lca;
                if (u > v) {
                    lca = serial2no[rmq.query(v, u, 1)];
                } else {
                    lca = serial2no[rmq.query(u, v, 1)];
                }

                Integer result = depth[a] + depth[b] - 2 * depth[lca];
                bw.write(result.toString());
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();
    }

    // 트리번호와 일련번호 관계
    public static int no2serial[];
    public static int serial2no[];

    // 각 노드가 trip에 처음 나타나는 위치
    public static int locInTrip[];
    // 각 노드의 깊이
    public static int depth[];
    // 다음 일련번호
    public static int nextSerial;

    // 깊이가 d인 노드 here 이하를 전위 탐색
    private static void dfs(int here, int d, ArrayList<Integer> trip) {
        no2serial[here] = nextSerial;
        serial2no[nextSerial] = here;
        nextSerial++;

        // 깊이 계산
        depth[here] = d;
        // trip에 현재 노드의 일련 번호를 추가
        locInTrip[here] = trip.size();
        trip.add(no2serial[here]);

        // 모든 자식 노드 방문
        if (ADJ[here] != null) {
            for (int i = 0; i < ADJ[here].size(); i++) {
                dfs(ADJ[here].get(i), d + 1, trip);
                // 자식노드 방문후 현재 노드로 돌아올때 다시 추가
                trip.add(no2serial[here]);
            }
        }
    }

    static class RMQ {
        int n;
        int range[];
        ArrayList<Integer> trip;

        public RMQ(ArrayList<Integer> arr) {
            trip = arr;
            n = arr.size();
            range = new int[getSize(n)];
            init(1, 0, n - 1);
        }

        private int getSize(int n) {
            Double H = Math.ceil(Math.log(n) / Math.log(2.0));
            return 1 << (H.intValue() + 1);
        }

        private int init(int node, int left, int right) {
            if (left == right) {
                return range[node] = trip.get(left);
            }

            int mid = (left + right) / 2;
            int leftInit = init(node * 2, left, mid);
            int rightInit = init(node * 2 + 1, mid + 1, right);
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

        public int query(int left, int right, int node) {
            return query(left, right, node, 0, n - 1);
        }
    }
}
