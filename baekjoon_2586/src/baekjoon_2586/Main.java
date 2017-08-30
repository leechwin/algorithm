package baekjoon_2586;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    public static int n; // 1≤P≤100,000
    public static int m; // 1≤F≤100,000
    public static Node a[]; // 0: pump, 1: fire
    public static int sp;
    public static int r;

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = new Node[n + m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = new Node(Integer.parseInt(st.nextToken()), 0);
        }
        st = new StringTokenizer(br.readLine());
        for (int i = n; i < n + m; i++) {
            a[i] = new Node(Integer.parseInt(st.nextToken()), 1);
        }

        Arrays.sort(a);

        for (int i = 1; i < n + m; i++) {
            a[i].lv = a[i - 1].lv;
            if (a[i].type == a[i - 1].type) {
                if (a[i].type == 1) {
                    a[i].lv++;
                } else {
                    a[i].lv--;
                }
            }
        }

        Arrays.sort(a, new Comparator<Node>() {
            @Override
            public int compare(Node a, Node b) {
                if (a.lv >= b.lv) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });

        for (int i = 1; i < n + m; i++) {
            if (a[i].lv != a[i - 1].lv) {
                r += count(sp, i - 1);
                sp = i;
            }
        }
        r += count(sp, n + m - 1);
        System.out.print(r);

        br.close();
    }

    private static int count(int s, int e) {
        Arrays.sort(a, s, e + 1);

        int r = 0;
        for (int i = s; i < e; i += 2) {
            r += a[i + 1].pos - a[i].pos;
        }
        if (s % 2 == e % 2) {
            int rm = r;
            for (int i = e; i - 2 >= s; i -= 2) {
                r += a[i].pos - a[i - 1].pos - (a[i - 1].pos - a[i - 2].pos);
                if (rm > r) {
                    rm = r;
                }
            }
            r = rm;
        }
        return r;
    }

    static class Node implements Comparable<Node> {
        int pos;
        int type;
        int lv = 50000;

        public Node(int pos, int type) {
            this.pos = pos;
            this.type = type;
        }

        @Override
        public int compareTo(Node other) {
            if (this.pos > other.pos) {
                return 1;
            } else {
                return -1;
            }
        }
    }

}
