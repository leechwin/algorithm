package baekjoon_2517_segment_tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static Node nodes[];

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int value = Integer.parseInt(st.nextToken());
            nodes[i] = new Node(i, value);
        }
        Arrays.sort(nodes);
        for (int i = 0; i < N; i++) {
            nodes[i].value = i;
        }
        Arrays.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node a, Node b) {
                if (a.index > b.index) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });

        RMQ rmq = new RMQ();
        for (int i = 0; i < N; i++) {
            rmq.update(nodes[i].value, 1);
            int result = rmq.query(nodes[i].value);
            bw.write(result + "\n");
        }

        br.close();
        bw.close();
    }

    static class RMQ {
        int n;
        int range[];

        public RMQ() {
            this.n = N;
            this.range = new int[getSize(this.n)];
        }

        private int getSize(int n) {
            Double H = Math.ceil(Math.log(n) / Math.log(2.0));
            return 1 << (H.intValue() + 1);
        }

        private void update(int index, int diff, int node, int nodeLeft, int nodeRight) {
            if (index < nodeLeft || nodeRight < index) {
                return;
            }

            if (nodeLeft == nodeRight) {
                range[node] = 1;
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

        public int query(int index) {
            return query(index, n, 1, 0, n - 1);
        }
    }

    static class Node implements Comparable<Node> {
        int index;
        int value;

        public Node(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Node other) {
            if (this.value > other.value) {
                return 1;
            } else {
                return -1;
            }
        }
    }

}
