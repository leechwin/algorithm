package algospot_dictionary;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {

    public static int C;
    public static int N;
    public static String WORD[];
    public static int MAP[][] = new int[26][26];
    public static int VISITED[] = new int[26];

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

            Arrays.fill(VISITED, 0);

            for (int j = 0; j < 26; j++) {
                Arrays.fill(MAP[j], 0);
            }

            RESULT.clear();

            solve();

            if (topologicalSort()) {
                // print to reverse
                for (int j = RESULT.size() - 1; j >= 0; j--) {
                    System.out.print((char) (RESULT.get(j) + 'a'));
                }
                System.out.println();
            } else {
                System.out.println("INVALID HYPOTHESIS");
            }

        }
    }

    private static boolean topologicalSort() {
        for (int i = 0; i < RESULT.size(); i++) {
            for (int j = i + 1; j < RESULT.size(); j++) {
                // find reverse path
                if (MAP[RESULT.get(i)][RESULT.get(j)] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void dfs(int i) {
        if (VISITED[i] == 0) {
            VISITED[i] = 1;
            for (int j = 0; j < 26; j++) {
                if (MAP[i][j] == 1) {
                    dfs(j);
                }
            }
            RESULT.add(i);
        }
    }

    private static void makeGraph(String word1, String word2) {
        int minLen = Math.min(word1.length(), word2.length());
        for (int i = 0; i < minLen; i++) {
            int pre = word1.charAt(i) - 'a';
            int next = word2.charAt(i) - 'a';
            if (pre != next) {
                MAP[pre][next] = 1;
                break;
            }
        }
    }

    private static void solve() {
        for (int i = 0; i < N - 1; i++) {
            makeGraph(WORD[i], WORD[i + 1]);
        }

        for (int i = 0; i < 26; i++) {
            if (VISITED[i] == 0) {
                dfs(i);
            }
        }
    }

}
