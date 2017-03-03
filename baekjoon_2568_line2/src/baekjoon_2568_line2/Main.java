package baekjoon_2568_line2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static List<Line> lists = new ArrayList<Line>();
    public static int MAX_LIS;

    static class Line implements Comparable<Line> {
        public int a;
        public int b;
        public int lis;

        public Line(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Line line) {
            return this.a > line.a ? 1 : -1;
        }
    }

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            lists.add(new Line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(lists);
        // LIS();
        BinarySearch();
    }

    /**
     * O Nlog(N)
     */
    private static void BinarySearch() {
        // TLE: http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=540&sca=30a0

    }

    /**
     * O(N^2)
     */
    @SuppressWarnings("unused")
    private static void LIS() {
        for (int i = 0; i < N; i++) {
            Line line = lists.get(i);
            line.lis = 1;
            for (int j = 0; j < i; j++) {
                Line pre = lists.get(j);
                if (line.b > pre.b && line.lis < pre.lis + 1) {
                    line.lis = pre.lis + 1;
                    if (MAX_LIS < line.lis) {
                        MAX_LIS = line.lis;
                    }
                }
            }
        }
        // System.out.println(MAX_LIS);

        ArrayList<Line> temp = new ArrayList<Line>();
        for (int i = N - 1; i >= 0; i--) {
            Line line = lists.get(i);
            if (line.lis == MAX_LIS) {
                for (int j = i - 1; j >= 0; j--) {
                    Line pre = lists.get(j);
                    if (pre.b < line.b && pre.lis == line.lis - 1) {
                        line = pre;
                    } else {
                        temp.add(pre);
                    }
                }
                break;
            } else {
                temp.add(line);
            }
        }

        System.out.println(temp.size());
        for (int i = temp.size() - 1; i >= 0; i--) {
            System.out.println(temp.get(i).a);
        }
    }

}
