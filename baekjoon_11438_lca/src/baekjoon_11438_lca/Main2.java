package baekjoon_11438_lca;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main2 {

    public static int N;
    public static int M;
    public static ArrayList<Integer> ADJ[];
    public static int DEPTH[];
    public static int PARENT[][];
    public static int H;

    /**
     * LCA
     */
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

        DEPTH = new int[N + 1];
        DEPTH[0] = -1;
        H = getSize(N);
        PARENT = new int[N + 1][H + 1];
        makeTree(1, 0);

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int result = lca(a, b);
            bw.write(result + "\n");
            bw.flush();
        }
        bw.close();
        br.close();
    }

    private static int lca(int a, int b) {
        if (DEPTH[a] > DEPTH[b]) {
            return lca(b, a);
        }
        if (DEPTH[a] != DEPTH[b]) {
            for (int i = H; i >= 0; i--) {
                if (DEPTH[a] <= DEPTH[PARENT[b][i]]) {
                    b = PARENT[b][i];
                }
            }
        }
        int lca = a;
        if (a != b) {
            for (int i = H; i >= 0; i--) {
                if (PARENT[a][i] != PARENT[b][i]) {
                    a = PARENT[a][i];
                    b = PARENT[b][i];
                }
                lca = PARENT[a][i];
            }
        }
        return lca;
    }

    private static int getSize(int n) {
        Double height = Math.floor(Math.log(n) / Math.log(2.0));
        return height.intValue() + 1;
    }

    private static void makeTree(int here, int parent) {
        DEPTH[here] = DEPTH[parent] + 1;
        PARENT[here][0] = parent;
        for (int i = 1; i <= H; i++) {
            PARENT[here][i] = PARENT[PARENT[here][i - 1]][i - 1];
        }

        for (int i = 0; i < ADJ[here].size(); i++) {
            int there = ADJ[here].get(i);
            if (there != parent) {
                makeTree(there, here);
            }
        }
    }
}
