package baekjoon_1717_union_find;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int n; // 1≤n≤1,000,000
    public static int m; // 1≤m≤100,000
    public static int p[];

    public static void main(String[] args) throws Exception {
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        p = new int[n + 1];
        Arrays.fill(p, -1);
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (type == 0) {
                // union
                union(a, b);
            } else {
                int aa = find(a);
                int bb = find(b);
                if (aa == bb) {
                    bw.write("YES\n");
                } else {
                    bw.write("NO\n");
                }
            }
            bw.flush();
        }
        bw.close();
        br.close();
    }

    public static int find(int a) {
        if (p[a] < 0) {
            return a;
        }
        p[a] = find(p[a]);
        return p[a];
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) {
            return;
        }
        p[b] = a;
    }
}
