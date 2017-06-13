package baekjoon_11437_lca;

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
    public static int depth[];
    public static boolean visited[];
    public static int parent[][];
    public static int H;

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
        for (int i = 0; i < N + 1; i++) {
            ADJ[i] = new ArrayList<Integer>();
        }
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ADJ[a].add(b);
            ADJ[b].add(a);
        }

        depth = new int[N + 1];
        depth[0] = 0;
        visited = new boolean[N + 1];
        Double height = Math.floor(Math.log(N) / Math.log(2.0));
        H = height.intValue() + 1;
        parent = new int[N + 1][H + 1];
        makeTree(1, 0);

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int lca = lca(a, b);
            bw.write(lca + "\n");
            bw.flush();
        }
        bw.close();
        br.close();
    }

    private static int lca(int a, int b) {
        if (depth[a] > depth[b]) {
            return lca(b, a);
        }

        if (depth[a] != depth[b]) {
            for (int i = H; i >= 0; i--) {
                if (depth[a] <= depth[parent[b][i]]) {
                    b = parent[b][i];
                }
            }
        }

        int lca = a;
        if (a != b) {
            for (int i = H; i >= 0; i--) {
                if (parent[a][i] != parent[b][i]) {
                    a = parent[a][i];
                    b = parent[b][i];
                }
                lca = parent[a][i];
            }
        }
        return lca;
    }

    private static void makeTree(int here, int pparent) {
        depth[here] = depth[pparent] + 1;
        parent[here][0] = pparent;
        visited[here] = true;

        for (int i = 1; i < H; i++) {
            parent[here][i] = parent[parent[here][i - 1]][i - 1];
        }

        for (int i = 0; i < ADJ[here].size(); i++) {
            int there = ADJ[here].get(i);
            if (!visited[there]) {
                makeTree(there, here);
            }
        }
    }

}
