package algospot_wordchain;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    public static int C;
    public static int N;
    public static String WORD[];
    public static int MAP[][] = new int[26][26];
    public static int IN_DEGREE[] = new int[26];
    public static int OUT_DEGREE[] = new int[26];

    public static LinkedList<String>[][] WORDMAP;
    public static ArrayList<Integer> RESULT = new ArrayList<Integer>();

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
            WORDMAP = new LinkedList[26][26];
            RESULT.clear();

            solve();
        }
    }

    private static void solve() {
        for (int i = 0; i < N; i++) {
            makeGraph(i);
        }

        getEulerCircuitOrTrail();
        if (RESULT.size() - 1 != N) {
            System.out.println("IMPOSSIBLE");
        } else {
            for (int i = RESULT.size() - 1; i >= 1; i--) {
                String word = WORDMAP[RESULT.get(i)][RESULT.get(i - 1)].removeLast();
                System.out.print(word + " ");
            }
            System.out.println();
        }
    }

    private static void makeGraph(int i) {
        int start = WORD[i].charAt(0) - 'a';
        int end = WORD[i].charAt(WORD[i].length() - 1) - 'a';

        MAP[start][end]++;
        OUT_DEGREE[start]++;
        IN_DEGREE[end]++;
        if (WORDMAP[start][end] == null) {
            WORDMAP[start][end] = new LinkedList<String>();
        }
        WORDMAP[start][end].add(WORD[i]);
    }

    private static void getEulerCircuit(int start) {
        for (int end = 0; end < 26; end++) {
            while (MAP[start][end] > 0) {
                MAP[start][end]--;
                getEulerCircuit(end);
            }
        }
        RESULT.add(start);
    }

    private static void getEulerCircuitOrTrail() {
        for (int i = 0; i < 26; i++) {
            // find start node
            if (IN_DEGREE[i] + 1 == OUT_DEGREE[i]) {
                getEulerCircuit(i);
                return;
            }
        }

        for (int i = 0; i < 26; i++) {
            if (IN_DEGREE[i] > 0) {
                getEulerCircuit(i);
                return;
            }
        }
    }

}
