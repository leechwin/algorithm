package algospot_HANOI4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static int C;

    // 12개의 DISC 상태는 2비트로 표현가능
    // 0, 01, 10, 11 : 4개의 기둥(0,1,2,3)
    public static int MAX_DISCS = 12; // 원반의 총 수

    // c는 2의 24제곱 자리에 1, 나머지 전부 0 인 상태.
    public static int[] c = new int[1 << (MAX_DISCS * 2)];

    public static int sgn(int x) {
        if (x == 0) {
            return 0;
        }
        return x > 0 ? 1 : -1;
    }

    public static int incr(int x) {
        if (x < 0) {
            return x - 1;
        }
        return x + 1;
    }

    public static int get(int state, int index) {
        // 3 -> {1,1}
        // 교집합은 둘다 1일 때 1이 나옴.
        // index 번호의 원반은 어느 기둥에 꽂혀있는지 확인 가능.
        return (state >> (index * 2)) & 3;
    }

    // state 상태에서 index원판 value번째 기둥에 set
    public static int set(int state, int index, int value) {
        return (state & ~(3 << (index * 2))) | (value << (index * 2));
    }

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        C = Integer.parseInt(br.readLine().trim());
        for (int c = 0; c < C; c++) {
            int begin = 0;
            int N = Integer.parseInt(br.readLine().trim());
            // 원기둥 마다 몇 개 꽂혀있는가
            int[] index = new int[4];

            // 각 원기둥에 꽂혀있는 원반은 무엇
            int[][] indexList = new int[4][];

            for (int i = 0; i < 4; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                // 각 원기둥에 몇개 꽂혀있는지 확인
                index[i] = Integer.parseInt(st.nextToken());
                // 갯수만큼 만든다
                indexList[i] = new int[index[i]];
                for (int j = 0; j < index[i]; j++) {
                    indexList[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for (int i = 0; i < indexList.length; i++) {
                for (int j = 0; j < indexList[i].length; j++) {
                    begin = set(begin, indexList[i][j] - 1, i);
                }
            }

            int end = 0;
            for (int i = 0; i < N; i++) {
                end = set(end, i, 3);
            }
            System.out.println(bidir(N, begin, end));
        }
    }

    public static int bidir(int discs, int begin, int end) {
        if (begin == end) {
            return 0;
        }
        Queue<Integer> q = new LinkedList<Integer>();
        Arrays.fill(c, 0);

        // 정방향탐색은 양수, 역방향 탐색은 음수
        q.add(begin);
        c[begin] = 1;
        q.add(end);
        c[end] = -1;

        while (!q.isEmpty()) {
            int here = q.poll();
            int[] top = { -1, -1, -1, -1 };
            for (int i = discs - 1; i >= 0; i--) {
                top[get(here, i)] = i;
            }
            for (int i = 0; i < 4; i++) {
                if (top[i] != -1) {
                    for (int j = 0; j < 4; j++) {
                        if (i != j && (top[j] == -1 || top[j] > top[i])) {
                            int there = set(here, top[i], j);
                            // 방문하지 않은경우
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
