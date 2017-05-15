package algospot_HANOI4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2 {
    public static final int MAX_DISCS = 12;

    // c는 2의 24제곱 자리에 1, 나머지 전부 0 인 상태.
    public static int[] c = new int[1 << (MAX_DISCS * 2)];
    public static int C;

    public static int sgn(int x) {
        if (x == 0)
            return 0;
        return x > 0 ? 1 : -1;
    }

    public static int incr(int x) {
        if (x < 0)
            return x - 1;
        return x + 1;
    }

    public static int get(int state, int index) {
        // 3 -> {1,1}
        // 교집합은 둘다 1일 때 1이 나옴.
        // index 번호의 원반은 어느 기둥에 꽂혀있는지 확인 가능.
        return (state >> (index * 2)) & 3;
    }

    // state 상태에서 index 원판은 value 기둥에 넣어준다.
    public static int set(int state, int index, int value) {
        return (state & ~(3 << (index * 2))) | (value << (index * 2));
    }

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        C = Integer.parseInt(br.readLine().trim());
        int[] answer = new int[C];
        for (int c = 0; c < C; c++) {
            int begin = 0;
            int N = Integer.parseInt(br.readLine().trim());
            // 원기둥 마다 몇 개 꽂혀있는가.
            int[] index = new int[4];

            // 각 원기둥에 꽂혀있는 원반은 무엇인가?
            int[][] indexList = new int[4][];

            // 네개 원기둥 돌자.
            for (int i = 0; i < 4; i++) {
                String str = br.readLine().trim();
                StringTokenizer strTok = new StringTokenizer(str);

                // 각 원기둥에 몇개 꽂혀있는지 확인.
                index[i] = Integer.parseInt(strTok.nextToken());
                // System.out.println("index[i] : "+index[i]);
                // 갯수만큼 만들어준다.
                indexList[i] = new int[index[i]];
                for (int j = 0; j < index[i]; j++) {
                    indexList[i][j] = Integer.parseInt(strTok.nextToken());
                }
            }
            for (int i = 0; i < indexList.length; i++) {
                for (int j = 0; j < indexList[i].length; j++) {
                    begin = set(begin, indexList[i][j] - 1, i);
                }
            }
            // System.out.println(begin);
            int end = 0;
            for (int i = 0; i < N; i++) {
                end = set(end, i, 3);
            }
            // System.out.println(end);
            answer[c] = bfs(N, begin, end);
        }
        for (int c = 0; c < answer.length; c++) {
            System.out.println(answer[c]);
        }

    }

    public static int bfs(int discs, int begin, int end) {
        int tmp = 0;
        if (begin == end)
            return 0;
        Queue<Integer> q = new LinkedList<Integer>();
        Arrays.fill(c, 0);
        q.add(begin);
        c[begin] = 1;
        q.add(end);
        c[end] = -1;
        while (!q.isEmpty()) {
            tmp++;
            int here = q.peek();
            q.poll();
            int[] top = new int[4];
            Arrays.fill(top, -1);
            for (int i = discs - 1; i >= 0; i--) {
                top[get(here, i)] = i;
            }
            for (int i = 0; i < 4; i++) {
                if (top[i] != -1) {
                    for (int j = 0; j < 4; j++) {
                        if (i != j && (top[j] == -1 || top[j] > top[i])) {
                            int there = set(here, top[i], j);
                            if (c[there] == 0) {
                                c[there] = incr(c[here]);
                                q.add(there);
                            } else if (sgn(c[there]) != sgn(c[here]))
                                return Math.abs(c[there]) + Math.abs(c[here]) - 1;
                        }
                    }
                }
            }

        }
        return -1;
    }

}
