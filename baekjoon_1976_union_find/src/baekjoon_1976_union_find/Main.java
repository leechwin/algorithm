package baekjoon_1976_union_find;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static int M;
    public static int ADJ[][];
    public static int p[];

    public static void main(String[] args) throws Exception {
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        p = new int[N];
        Arrays.fill(p, -1);
        ADJ = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                ADJ[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        disjointSet();

        boolean result = true;
        st = new StringTokenizer(br.readLine());
        int start = find(Integer.parseInt(st.nextToken()) - 1);
        for (int i = 1; i < M; i++) {
            int next = find(Integer.parseInt(st.nextToken()) - 1);
            if (start != next) {
                result = false;
                break;
            }
        }
        if (result) {
            bw.write("YES\n");
        } else {
            bw.write("NO\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void disjointSet() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (ADJ[i][j] == 1) {
                    merge(i, j);
                }
            }
        }
    }

    private static int find(int a) {
        if (p[a] < 0) {
            return a;
        }
        return p[a] = find(p[a]);
    }

    private static void merge(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) {
            return;
        }
        if (a < b) {
            p[b] = a;
        } else {
            p[a] = b;
        }
    }

}
