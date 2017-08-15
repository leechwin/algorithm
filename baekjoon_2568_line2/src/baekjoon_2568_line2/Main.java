package baekjoon_2568_line2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static Line[] LINES;
    public static boolean[] VISITED;
    public static Tracker[] TRACKERS;
    public static int[] LIS;

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        LINES = new Line[N];
        VISITED = new boolean[500001];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            LINES[i] = new Line(a, b);
            VISITED[a] = true;
        }
        Arrays.sort(LINES);

        TRACKERS = new Tracker[N];
        for (int i = 0; i < N; i++) {
            TRACKERS[i] = new Tracker();
        }

        LIS = new int[N];
        int pLis = 0;
        int pArr = 1;
        LIS[0] = LINES[0].b;
        TRACKERS[0].index = 0;
        TRACKERS[0].a = LINES[0].a;

        while (pArr < N) {
            if (LIS[pLis] < LINES[pArr].b) {
                pLis++;
                LIS[pLis] = LINES[pArr].b;

                TRACKERS[pArr].index = pLis;
                TRACKERS[pArr].a = LINES[pArr].a;
            } else {
                int ans = lowerBound(0, pLis, LINES[pArr].b);
                LIS[ans - 1] = LINES[pArr].b;

                TRACKERS[pArr].index = ans - 1;
                TRACKERS[pArr].a = LINES[pArr].a;
            }
            pArr++;
        }

        bw.write(N - (pLis + 1) + "\n");
        bw.flush();

        int p = pLis;
        ArrayList<Integer> tracker = new ArrayList<>();
        for (int i = N - 1; i >= 0; i--) {
            if (p < 0) {
                break;
            }
            if (TRACKERS[i].index == p) {
                tracker.add(TRACKERS[i].a);
                p--;
            }
        }
        for (Integer num : tracker) {
            VISITED[num] = false;
        }
        for (int i = 0; i <= 500000; i++) {
            if (VISITED[i]) {
                bw.write(i + "\n");
                bw.flush();
            }
        }

        br.close();
        bw.close();
    }

    static int lowerBound(int start, int end, int target) {
        while (start < end) {
            int mid = (start + end) / 2;
            if (LIS[mid] < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return end + 1;
    }

    static class Line implements Comparable<Line> {
        int a;
        int b;

        Line(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Line other) {
            if (this.a > other.a) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    static class Tracker {
        int index;
        int a;

        public Tracker() {
        }
    }

}
