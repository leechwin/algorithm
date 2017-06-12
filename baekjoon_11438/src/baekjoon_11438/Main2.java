package baekjoon_11438;

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
    public static int depth[];
    public static int ancester[][];
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

        depth = new int[N + 1];
        depth[0] = -1;
        H = getSize(N);
        ancester = new int[N + 1][H + 1];
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
        if (depth[a] > depth[b]) {
            return lca(b, a);
        }
        if (depth[a] != depth[b]) {
            for (int i = H; i >= 0; i--) {
                if (depth[a] <= depth[ancester[b][i]]) {
                    b = ancester[b][i];
                }
            }
        }
        int lca = a;
        if (a != b) {
            for (int i = H; i >= 0; i--) {
                if (ancester[a][i] != ancester[b][i]) {
                    a = ancester[a][i];
                    b = ancester[b][i];
                }
                lca = ancester[a][i];
            }
        }
        return lca;
    }

    private static int getSize(int n) {
        Double height = Math.floor(Math.log(n) / Math.log(2.0));
        return height.intValue() + 1;
    }

    private static void makeTree(int here, int parent) {
        depth[here] = depth[parent] + 1;
        ancester[here][0] = parent;
        for (int i = 1; i <= H; i++) {
            ancester[here][i] = ancester[ancester[here][i - 1]][i - 1];
        }

        for (int i = 0; i < ADJ[here].size(); i++) {
            int there = ADJ[here].get(i);
            if (there != parent) {
                makeTree(there, here);
            }
        }
    }
}
