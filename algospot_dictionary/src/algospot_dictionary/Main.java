package algospot_dictionary;

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
    public static ArrayList<ArrayList<Integer>> ALPHABET;
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
            Arrays.fill(VISITED, -1);
            ALPHABET = new ArrayList<ArrayList<Integer>>();
            for (int j = 0; j < 26; j++) {
                ALPHABET.add(new ArrayList<>());
            }

            RESULT.clear();
            boolean bSolve = solve();

            if (!bSolve) {
                System.out.println("INVALID HYPOTHESIS");
            } else {
                for (int j = 0; j < 26; j++) {
                    ArrayList<Integer> list = ALPHABET.get(j);
                    if (!list.isEmpty()) {
                        dfs(j);
                    }
                }
                printword();
            }
        }
    }

    private static void printword() {
        for (int i = RESULT.size() - 1; i >= 0; i--) {
            System.out.print((char) (RESULT.get(i) + 'a'));
        }

        for (int i = 0; i < 26; i++) {
            if (VISITED[i] == -1) {
                System.out.print((char) (i + 'a'));
            }
        }
        System.out.println();
    }

    private static void dfs(int i) {
        if (VISITED[i] == 1) {
            return;
        }
        VISITED[i] = 1;
        ArrayList<Integer> list = ALPHABET.get(i);
        for (int j = 0; j < list.size(); j++) {
            int point = list.get(j);
            if (VISITED[point] == -1) {
                dfs(point);
            }
        }
        RESULT.add(i);
    }

    private static boolean compare(String word1, String word2) {
        for (int i = 0; i < word1.length(); i++) {
            int pre = word1.charAt(i) - 'a';
            int next = word2.charAt(i) - 'a';
            if (pre != next) {
                ArrayList<Integer> list = ALPHABET.get(pre);
                if (!list.contains(next)) {
                    list.add(next);
                    if (ALPHABET.get(next).contains(pre)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return true;
    }

    private static boolean solve() {
        for (int i = 0; i < N - 1; i++) {
            if (!compare(WORD[i], WORD[i + 1])) {
                return false;
            }
        }
        return true;
    }

}
