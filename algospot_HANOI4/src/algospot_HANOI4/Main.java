package algospot_HANOI4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    public static int C;
    public static int N;
    public static int END;
    public static int MAX_DISCS = 12;
    public static int[] c = new int[1 << (MAX_DISCS * 2)];

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        for (int i = 0; i < C; i++) {
            int begin = 0;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                int rings = Integer.parseInt(st.nextToken());
                for (int k = 0; k < rings; k++) {
                    begin = set(begin, Integer.parseInt(st.nextToken()), j);
                }

            }
            Arrays.fill(c, -1);
            int end = (int) Math.pow(4, N);
            bfs(N, begin, end);
            System.out.println(c[end]);
        }
    }

    public static int get(int state, int index) {
        return (state >> (index * 2)) & 3;
    }

    public static int set(int state, int index, int value) {
        return (state & ~(3 << (index * 2))) | (value << (index * 2));
    }

    public static int bfs(int discs, int begin, int end) {
        if (begin == end) {
            return 0;
        }

        LinkedList<Integer> q = new LinkedList<Integer>();
        q.addLast(begin);
        c[begin] = 0;

        while (!q.isEmpty()) {
            int here = q.removeFirst();

            int top[] = { -1, -1, -1, -1 };
            for (int i = discs; i > 0; --i) {
                top[get(here, i)] = i;
            }

            for (int i = 0; i < 4; i++) {
                if (top[i] == -1) {
                    continue;
                }

                for (int j = 0; j < 4; j++) {
                    if (i != j && (top[j] == -1 || top[j] > top[i])) {
                        int there = set(here, top[i], j);
                        if (c[there] != -1) {
                            continue;
                        }
                        c[there] = c[here] + 1;
                        if (there == end) {
                            return c[there];
                        }
                        q.addLast(there);
                    }
                }
            }
        }

        return -1;
    }

}
