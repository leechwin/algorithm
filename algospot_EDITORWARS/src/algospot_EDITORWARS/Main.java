package algospot_EDITORWARS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int C;
    public static int N; // 1≤N≤10000
    public static int M; // 1≤M≤100000

    public static int parent[];
    public static int[] rank;
    public static int[] enemy;
    public static int[] size;

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
            M = Integer.parseInt(st.nextToken());
            parent = new int[N + 1];
            for (int j = 0; j < N + 1; j++) {
                parent[j] = j;
            }
            rank = new int[N + 1];
            enemy = new int[N + 1];
            size = new int[N + 1];
            Arrays.fill(rank, 1);
            Arrays.fill(enemy, -1);
            Arrays.fill(size, 1);

            boolean isContradiction = false;
            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                String str = st.nextToken();
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                boolean valid = true;
                if (valid && !isContradiction) {
                    if (str.equals("ACK")) {
                        valid = ack(u, v);
                    } else {
                        valid = dis(u, v);
                    }
                    if (!valid) {
                        isContradiction = true;
                        bw.write("CONTRADICTION AT " + (j + 1) + "\n");
                        bw.flush();
                    }
                }
            }
            if (isContradiction) {
                continue;
            }

            int result = 0;
            for (int j = 0; j < N; j++) {
                if (find(j) == j) {
                    int en = enemy[j];
                    // 같은 모임 쌍을 두 번 세지 않기 위해, enemy < node 인 경우만 카운트
                    if (en > j) {
                        continue;
                    }
                    if (en == -1) {
                        en = 0;
                    }
                    int mySize = size[j];
                    int enSize = size[en];
                    result += Math.max(mySize, enSize);
                }
            }
            bw.write("MAX PARTY SIZE IS " + result + "\n");
            bw.flush();
        }
        bw.close();
    }

    public static boolean ack(int u, int v) {
        u = find(u);
        v = find(v);
        // 서로 적인 경우 모순
        if (enemy[u] == v) {
            return false;
        }

        int a = merge(u, v);
        // 아군의 적은 나의 적
        int b = merge(enemy[u], enemy[v]);
        enemy[a] = b;
        if (b != -1) {
            enemy[b] = a;
        }
        return true;
    }

    public static boolean dis(int u, int v) {
        u = find(u);
        v = find(v);
        // 같은편이면 모순
        if (u == v) {
            return false;
        }
        // 적의 적은 아군
        int a = merge(u, enemy[v]);
        int b = merge(v, enemy[u]);
        enemy[a] = b;
        enemy[b] = a;
        return true;
    }

    public static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        parent[a] = find(parent[a]);
        return parent[a];
    }

    public static int merge(int u, int v) {
        if (u == -1 || v == -1) {
            return Math.max(u, v);
        }
        u = find(u);
        v = find(v);
        if (u == v) {
            return v;
        }
        // 부모가될 v 가 더 크게 조정
        if (rank[u] > rank[v]) {
            int tmp = u;
            u = v;
            v = tmp;
        }
        if (rank[u] == rank[v]) {
            rank[v]++;
        }
        parent[u] = v;
        size[v] += size[u];
        return v;
    }

}
