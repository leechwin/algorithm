package algospot_klis;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    public static int C;
    public static int N, K;
    public static int NUMBER[];
    public static int LIS[];
    public static int COUNTS[];

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        for (int i = 0; i < C; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            NUMBER = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                NUMBER[j] = Integer.parseInt(st.nextToken());
            }
            LIS = new int[N + 1];
            Arrays.fill(LIS, -1);
            COUNTS = new int[N + 1];
            Arrays.fill(COUNTS, -1);
            solve();
        }
    }

    private static void solve() {
        int s = lis(-1);
        int c = count(-1);
        result.clear();
        reconstruct(-1, K - 1);
        System.out.println();
    }

    private static int lis(int start) {
        if (LIS[start + 1] != -1) {
            return LIS[start + 1];
        }

        int ret = 1;
        for (int next = start + 1; next < N; next++) {
            if (start == -1 || NUMBER[start] < NUMBER[next]) {
                ret = Math.max(ret, lis(next) + 1);
            }
        }
        LIS[start + 1] = ret;
        return ret;
    }

    private static int count(int start) {
        if (lis(start) == 1) {
            return 1;
        }
        if (COUNTS[start + 1] != -1) {
            return COUNTS[start + 1];
        }
        int ret = 0;
        for (int next = start + 1; next < N; next++) {
            if (start == -1 || NUMBER[start] < NUMBER[next] && lis(start) == lis(next) + 1) {
                ret = (int) Math.min(Long.MAX_VALUE, (long) ret + count(next));
            }
        }
        COUNTS[start + 1] = ret;
        return ret;
    }

    public static LinkedList result = new LinkedList<>();

    static class Pair {
        public Pair(int num, int index) {
            this.num = num;
            this.index = index;
        }

        public int num;
        public int index;
    }

    public static void reconstruct(int start, int skip) {
        if (start != -1) {
            result.addLast(NUMBER[start]);
        }
        LinkedList<Pair> followers = new LinkedList<>();
        for (int i = start + 1; i < N; i++) {
            if (start == -1 || NUMBER[start] < NUMBER[i] && lis(start) == lis(i) + 1) {
                followers.addLast(new Pair(NUMBER[i], i));
            }
        }
        Collections.sort(followers, new Comparator<Pair>() {
            @Override
            public int compare(Pair arg0, Pair arg1) {
                if (arg0.num > arg1.num) {
                    return 1;
                }
                return -1;
            }
        });

        for (int i = 0; i < followers.size(); i++) {
            int idx = followers.get(i).index;
            int cnt = count(idx);
            if (cnt <= skip) {
                skip -= cnt;
            } else {
                reconstruct(idx, skip);
                break;
            }
        }
    }

}
