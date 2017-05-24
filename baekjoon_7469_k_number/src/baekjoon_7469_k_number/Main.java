package baekjoon_7469_k_number;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    public static int N; // 1 <= n <= 100,000
    public static int M; // 1 <= m <= 5,000
    public static int I, J, K;
    public static int arr[];

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        RMQ rmq = new RMQ();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            I = Integer.parseInt(st.nextToken());
            J = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            ArrayList<Integer> result = rmq.query(I - 1, J - 1, 1);
            System.out.println(result.get(K - 1));
        }
    }

    static class RMQ {
        int n;
        ArrayList<Integer> range[];

        public RMQ() {
            n = N;
            range = new ArrayList[getSize(n)];
            init(1, 0, n - 1);
        }

        private int getSize(int n) {
            Double H = Math.ceil(Math.log(n) / Math.log(2.0));
            return 1 << (H.intValue() + 1);
        }

        private ArrayList<Integer> init(int node, int nodeLeft, int nodeRight) {
            if (nodeLeft == nodeRight) {
                if (range[node] == null) {
                    range[node] = new ArrayList<Integer>();
                }
                range[node].add(arr[nodeLeft]);
                return range[node];
            }
            int mid = (nodeLeft + nodeRight) / 2;
            ArrayList<Integer> leftInit = init(node * 2, nodeLeft, mid);
            ArrayList<Integer> rightInit = init(node * 2 + 1, mid + 1, nodeRight);
            ArrayList<Integer> merged = new ArrayList<Integer>();
            merged.addAll(leftInit);
            merged.addAll(rightInit);
            Collections.sort(merged);
            return range[node] = merged;
        }

        private ArrayList<Integer> query(int left, int right, int node, int nodeLeft, int nodeRight) {
            if (right < nodeLeft || nodeRight < left) {
                return null;
            }
            if (left <= nodeLeft && nodeRight <= right) {
                return range[node];
            }
            int mid = (nodeLeft + nodeRight) / 2;
            ArrayList<Integer> leftQuery = query(left, right, node * 2, nodeLeft, mid);
            ArrayList<Integer> rightQuery = query(left, right, node * 2 + 1, mid + 1, nodeRight);
            ArrayList<Integer> merged = new ArrayList<Integer>();
            if (leftQuery != null) {
                merged.addAll(leftQuery);
            }
            if (rightQuery != null) {
                merged.addAll(rightQuery);
            }
            Collections.sort(merged);
            return merged;
        }

        public ArrayList<Integer> query(int left, int right, int node) {
            return query(left, right, node, 0, n - 1);
        }
    }

}
