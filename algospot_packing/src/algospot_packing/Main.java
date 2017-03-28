package algospot_packing;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

// 344ms
public class Main {

    public static int C;
    public static int N;
    public static int W;
    public static String NAME[] = new String[101];
    public static int SIZE[] = new int[101];
    public static int REQ[] = new int[101];
    public static int CACHE[][] = new int[101][1001];
    public static HashSet<Integer> PICKED = new HashSet<Integer>();

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        // FileInputStream fis = new FileInputStream("input2.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());
        for (int i = 0; i < C; i++) {
            Arrays.fill(NAME, "");
            Arrays.fill(SIZE, -1);
            Arrays.fill(REQ, -1);
            for (int j = 0; j < 101; j++) {
                Arrays.fill(CACHE[j], -1);
            }
            PICKED.clear();

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                NAME[j] = st.nextToken();
                SIZE[j] = Integer.parseInt(st.nextToken());
                REQ[j] = Integer.parseInt(st.nextToken());
            }
            int maxReq = knapsack(N - 1, W);
            backtracking(N - 1, W);
            int maxThings = PICKED.size();
            System.out.printf("%d %d\n", maxReq, maxThings);
            for (Iterator<Integer> iterator = PICKED.iterator(); iterator.hasNext();) {
                int index = iterator.next();
                System.out.println(NAME[index]);
            }
        }
    }

    // V(i,w) == MAX( V(i-1, w), V(i-1, w-WI[i]) + CI[i) )
    private static int knapsack(int i, int w) {
        if (i < 0) {
            return 0;
        }

        if (CACHE[i][w] != -1) {
            return CACHE[i][w];
        }

        if (w - SIZE[i] >= 0) {
            CACHE[i][w] = Math.max(knapsack(i - 1, w), knapsack(i - 1, w - SIZE[i]) + REQ[i]);
        } else {
            CACHE[i][w] = knapsack(i - 1, w);
        }
        return CACHE[i][w];
    }

    // get item list
    private static void backtracking(int i, int w) {
        if (i == 0) {
            if (SIZE[i] <= w) {
                PICKED.add(i);
            }
            return;
        }
        if (CACHE[i][w] == CACHE[i - 1][w]) { // i item is not contain
            backtracking(i - 1, w);
        } else { // i item is contain
            PICKED.add(i);
            backtracking(i - 1, w - SIZE[i]);
        }
    }
}
