package baekjoon_2568_line2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {

    public static int N;
    public static List<Line> lists = new ArrayList<Line>();
    public static int MAX_LIS;

    static int[] lis;
    static Wire[] list;
    static Pair[] trace;
    static boolean[] visit;

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        // for (int i = 0; i < N; i++) {
        // st = new StringTokenizer(br.readLine());
        // lists.add(new Line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        // }
        list = new Wire[N];
        lis = new int[N];
        visit = new boolean[500001];
        Vector<Integer> v = new Vector<>();
        trace = new Pair[100001];
        for (int i = 0; i < 100001; i++) {
            trace[i] = new Pair();
        }
        for (int i = 0; i < N; i++) {
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            list[i] = new Wire(s, e);
            visit[s] = true;
        }
        Arrays.sort(list);
        int pLis = 0, pArr = 1;

        lis[pLis] = list[0].b;
        trace[0].first = 0;
        trace[0].second = list[0].a;

        while (pArr < N) {

            if (lis[pLis] < list[pArr].b) {
                lis[++pLis] = list[pArr].b;
                trace[pArr].first = pLis;
                trace[pArr].second = list[pArr].a;
            } else {
                int ans = lower_bound(0, pLis, list[pArr].b);
                lis[ans - 1] = list[pArr].b;

                trace[pArr].first = ans - 1;
                trace[pArr].second = list[pArr].a;
            }
            pArr++;
        }

        // LIS();
        // solve();
        // FIXME: http://www.jungol.co.kr/theme/jungol/reinfo.php?sid=1793898
    }

    static int lower_bound(int start, int end, int target) {
        int mid;

        while (start < end) {
            mid = (start + end) / 2;
            if (lis[mid] < target)
                start = mid + 1;
            else
                end = mid;
        }
        return end + 1;
    }

    /**
     * O(N^2)
     */
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

    static class Wire implements Comparable<Wire> {
        int a;
        int b;

        Wire(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Wire o) {
            return this.a < o.a ? -1 : 1;
        }
    }

    static class Pair {
        int first, second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        public Pair() {
        }
    }
}
