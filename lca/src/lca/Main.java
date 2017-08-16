package lca;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//#1 180 160
//#2 180 180
public class Main {

    public static int T;
    public static int N; // (1 <= N <= 100,000)
    public static int E; // (1<= E <= 100,000)
    public static int K; // (1<= K <= 200)

    public static ArrayList<Node> ADJ[];
    public static Node nodes[];

    public static int H;
    public static int parent[];
    public static int depth[];
    public static boolean visited[];
    public static int result;

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        long start = System.currentTimeMillis();

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            ADJ = new ArrayList[N + 1];
            nodes = new Node[N + 1];
            nodes[0] = new Node(0, 0, 0);
            for (int j = 0; j <= N; j++) {
                ADJ[j] = new ArrayList<>();
            }
            for (int j = 1; j <= N; j++) {
                st = new StringTokenizer(br.readLine());
                int parent = Integer.parseInt(st.nextToken());
                int height = Integer.parseInt(st.nextToken());
                ADJ[parent].add(new Node(j, parent, height));
                nodes[j] = new Node(j, parent, height);
            }

            parent = new int[N + 1];
            depth = new int[N + 1];
            visited = new boolean[N + 1];

            makeTree(0, 0);

            // lca
            System.out.print("#" + i + " ");
            for (int j = 0; j < E; j++) {
                st = new StringTokenizer(br.readLine());
                K = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                for (int k = 1; k < K; k++) {
                    int b = Integer.parseInt(st.nextToken());
                    a = lca(a, b);
                }
                result = 0;
                findHeight(a);
                System.out.print(result + " ");
            }
            System.out.println();
        }

        long end = System.currentTimeMillis();
        System.out.println("time: " + ((end - start) / 1000.0) + " sec.");
    }

    private static void findHeight(int here) {
        if (here == 0) {
            return;
        }
        Node node = nodes[here];
        result = Math.max(result, node.height);
        findHeight(node.parent);
    }

    private static int lca(int a, int b) {
        if (depth[a] > depth[b]) {
            return lca(b, a);
        }

        while (depth[a] != depth[b]) {
            b = parent[b];
        }

        while (a != b) {
            a = parent[a];
            b = parent[b];
        }
        return a;
    }

    private static void makeTree(int here, int pparent) {
        if (here == 0) {
            depth[here] = 0;
        } else {
            depth[here] = depth[pparent] + 1;
        }
        parent[here] = pparent;
        visited[here] = true;

        for (int i = 0; i < ADJ[here].size(); i++) {
            Node there = ADJ[here].get(i);
            if (!visited[there.index]) {
                makeTree(there.index, here);
            }
        }
    }

    static class Node {
        public int index;
        public int parent;
        public int height;

        public Node(int index, int parent, int height) {
            this.index = index;
            this.parent = parent;
            this.height = height;
        }
    }

}
