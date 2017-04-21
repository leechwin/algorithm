package algospot_wordchain;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int C;
    public static int N;
    public static String WORD[];
    public static int MAP[][] = new int[26][26];
    public static int IN_DEGREE[] = new int[26];
    public static int OUT_DEGREE[] = new int[26];
    public static ArrayList<String> RESULT = new ArrayList<>();

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
            WORD = new String[N];
            for (int j = 0; j < N; j++) {
                WORD[j] = br.readLine();
            }

            for (int j = 0; j < 26; j++) {
                Arrays.fill(MAP[j], 0);
            }
            Arrays.fill(IN_DEGREE, 0);
            Arrays.fill(OUT_DEGREE, 0);

            RESULT.clear();

            solve();
        }
    }

    private static void solve() {
        for (int i = 0; i < N; i++) {
            makeGraph(i);
        }

        if (RESULT.isEmpty() || RESULT.size() != N) {
            System.out.println("IMPOSSIBLE");
        } else {
            for (int i = RESULT.size() - 1; i >= 0; i--) {
                System.out.printf(RESULT.get(i) + " ");
            }
            System.out.println();
        }
    }

    private static void makeGraph(int i) {
        int start = WORD[i].charAt(0) - 'a';
        int end = WORD[i].charAt(WORD[i].length() - 1) - 'a';

        MAP[start][end]++;
        INDE

    }

}
