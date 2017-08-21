package lca;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static int T;
    public static int N; // (1 <= N <= 100,000)
    public static int E; // (1<= E <= 100,000)
    public static int K; // (1<= K <= 200)
    public static Node nodes[];

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input2.txt");
        System.setIn(fis);

        long start = System.currentTimeMillis();

        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            nodes = new Node[N + 1];
            for (int j = 0; j < N + 1; j++) {
                Node node = new Node();
                node.index = j;
                nodes[j] = node;
            }
            nodes[0].parent = null;
            nodes[0].depth = 0;

            for (int j = 1; j <= N; j++) {
                st = new StringTokenizer(br.readLine());
                int parent = Integer.parseInt(st.nextToken());
                int height = Integer.parseInt(st.nextToken());
                nodes[j].height = height;
                nodes[j].parent = nodes[parent];
                nodes[j].parent.child.add(nodes[j]);
            }

            makeTree();

            // lca
            bw.write("#" + i);
            for (int j = 0; j < E; j++) {
                st = new StringTokenizer(br.readLine());
                K = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                for (int k = 1; k < K; k++) {
                    int b = Integer.parseInt(st.nextToken());
                    a = lca(a, b);
                }
                bw.write(" " + nodes[a].height);
            }
            bw.write("\n");
        }

        long end = System.currentTimeMillis();
        bw.write("time: " + ((end - start) / 1000.0) + " sec.");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int lca(int a, int b) {
        Node c1 = nodes[a];
        Node c2 = nodes[b];
        while (c1 != c2) {
            if (c1.depth == c2.depth) {
                c1 = c1.parent == null ? c1 : c1.parent;
                c2 = c2.parent == null ? c2 : c2.parent;
            } else if (c1.depth > c2.depth) {
                c1 = c1.parent == null ? c1 : c1.parent;
            } else {
                c2 = c2.parent == null ? c2 : c2.parent;
            }
        }
        return c1.index;
    }

    private static void makeTree() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(nodes[0]);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (Node ch : node.child) {
                ch.depth = node.depth + 1;
                ch.height = Math.max(node.height, ch.height);
                queue.add(ch);
            }
        }
    }

    static class Node {
        public int index;
        public int height;
        public int depth;
        public Node parent;
        public ArrayList<Node> child = new ArrayList<>();
    }

}
