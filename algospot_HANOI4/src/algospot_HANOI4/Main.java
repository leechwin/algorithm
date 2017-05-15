package algospot_HANOI4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int C;
    static int N;
    static byte[] cache;
    static int SB = 30;

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());

        for (int c = 0; c < C; c++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            cache = new byte[1 << (N * 2)];
            int ds = 0;
            BitSet gbs = new BitSet(N * 2);
            gbs.set(0, N * 2);
            int goal = (int) gbs.toLongArray()[0];
            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken());
                for (int j = 0; j < n; j++) {
                    int w = Integer.parseInt(st.nextToken());
                    int shift = (w - 1) * 2;
                    int si = i << shift;
                    ds |= si;
                }
            }

            int result = sub(ds, goal);
            System.out.println(result);
        }
    }

    static int sub(int start, int end) {
        if (start == end) {
            return 0;
        }

        Queue<Integer> qu = new LinkedList<Integer>();
        qu.add(start);
        qu.add(end);

        cache[start] = 1;
        cache[end] = -1;

        while (!qu.isEmpty()) {
            int data = qu.poll();
            int[] status = new int[] { SB, SB, SB, SB };
            for (int i = 0; i < N; i++) {
                int shift = i * 2;
                int si = 3 << shift;
                int n = data & si;
                n = n >> shift;
                if (status[n] == SB) {
                    status[n] = i;
                }
            }

            for (int i = 0; i < 4; i++) {
                if (status[i] == SB) {
                    continue;
                }

                for (int j = 0; j < 4; j++) {
                    if (status[j] > status[i]) {
                        int newds = data;
                        int iv = status[i];
                        int sj = j << (iv * 2);
                        int base = 3 << (iv * 2);
                        base = ~base;
                        newds &= base;
                        newds |= sj;

                        if (cache[newds] == 0) {
                            cache[newds] = incr(cache[data]);
                            qu.add(newds);
                        } else if (sgn(cache[newds]) != sgn(cache[data])) {
                            return Math.abs(cache[newds]) + Math.abs(cache[data]) - 1;
                        }
                    }
                }
            }
        }
        return -1;
    }

    public static byte sgn(byte x) {
        return (byte) ((x > 0) ? 1 : -1);
    }

    public static byte incr(byte x) {
        return (byte) ((x > 0) ? x + 1 : x - 1);
    }

}
