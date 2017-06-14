package baekjoon_8012_lca;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static int n;
    public static ArrayList<Integer> ADJ[];

    public static int depth[];
    public static int parent[][];
    public static boolean visited[];
    public static int H;

    public static void main(String[] args) throws Exception {
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        ADJ = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            ADJ[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ADJ[a].add(b);
            ADJ[b].add(a);
        }

        depth = new int[n + 1];
        visited = new boolean[n + 1];
        Double h = Math.floor(Math.log(n) / Math.log(2.0));
        H = h.intValue() + 1;
        parent = new int[n + 1][H + 1];

        depth[0] = 0;
        makeTreeDfs(1, 0);

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int result = 0;
        int start = 1;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int end = Integer.parseInt(st.nextToken());
            int lca = lca(start, end);
            result += depth[start] + depth[end] - (2 * depth[lca]);
            start = end;
        }
        bw.write(result + "\n");
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

    private static void makeTreeDfs(int here, int pparent) {
        depth[here] = depth[pparent] + 1;
        parent[here][0] = pparent;
        visited[here] = true;

        for (int i = 1; i <= H; i++) {
            parent[here][i] = parent[parent[here][i - 1]][i - 1];
        }

        for (int i = 0; i < ADJ[here].size(); i++) {
            int there = ADJ[here].get(i);
            if (!visited[there]) {
                makeTreeDfs(there, here);
            }
        }
    }

}
