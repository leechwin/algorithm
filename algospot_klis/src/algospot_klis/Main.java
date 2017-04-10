package algospot_klis;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    static int C;
    static int N, K;
    static int NUMBER[];
    static int LIS[];
    static long COUNT[];
    static long MAX = 2000000001L;

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

            COUNT = new long[N + 1];
            Arrays.fill(COUNT, -1);

            lis(-1);
            count(-1);

            LinkedList<Integer> result = new LinkedList<Integer>();
            reconstruct(-1, K - 1, result);

            System.out.println(result.size());
            for (Iterator<Integer> iterator = result.iterator(); iterator.hasNext();) {
                int num = iterator.next();
                System.out.printf(num + " ");
            }
            System.out.println();
        }
    }

    private static int lis(int pos) {
        if (LIS[pos + 1] != -1) {
            return LIS[pos + 1];
        }

        int ret = 1;
        for (int i = pos + 1; i < N; i++) {
            if (pos == -1 || NUMBER[pos] < NUMBER[i]) {
                ret = Math.max(ret, lis(i) + 1);
            }
        }
        LIS[pos + 1] = ret;
        return ret;
    }

    private static long count(int pos) {
        if (lis(pos) == 1) {
            return 1;
        }

        if (COUNT[pos + 1] != -1) {
            return COUNT[pos + 1];
        }

        long ret = 0;
        for (int i = pos + 1; i < N; i++) {
            if (pos == -1 || NUMBER[pos] < NUMBER[i] && lis(pos) == lis(i) + 1) {
                ret = Math.min(MAX, ret + count(i));
            }
        }
        COUNT[pos + 1] = ret;
        return ret;
    }

    static class Pair {
        public Pair(int num, int index) {
            this.num = num;
            this.index = index;
        }

        public int num;
        public int index;
    }

    public static void reconstruct(int start, int skip, LinkedList<Integer> result) {
        if (start != -1) {
            result.add(NUMBER[start]);
        }
        LinkedList<Pair> followers = new LinkedList<Pair>();
        for (int i = start + 1; i < N; i++) {
            if ((start == -1 || NUMBER[start] < NUMBER[i]) && lis(start) == lis(i) + 1) {
                followers.add(new Pair(NUMBER[i], i));
            }
        }
        Collections.sort(followers, new Comparator<Pair>() {
            @Override
            public int compare(Pair pair1, Pair pair2) {
                return pair1.num - pair2.num;
            }
        });

        for (int i = 0; i < followers.size(); i++) {
            int idx = followers.get(i).index;
            int cnt = (int) count(idx);
            if (cnt <= skip) {
                skip -= cnt;
            } else {
                reconstruct(idx, skip, result);
                break;
            }
        }
    }
}
