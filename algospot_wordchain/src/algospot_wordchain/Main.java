package algospot_wordchain;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static int C;
    public static int N;
    public static String WORD[];
    public static int VISITED[];
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
            VISITED = new int[N];
            RESULT.clear();
            for (int j = 0; j < N; j++) {
                WORD[j] = br.readLine();
            }
            solve();
        }
    }

    private static void solve() {
        for (int i = 0; i < N; i++) {
            dfs(i);
        }

        if (RESULT.isEmpty()) {
            System.out.println("IMPOSSIBLE");
        } else {
            for (int i = RESULT.size() - 1; i >= 0; i--) {
                System.out.printf(RESULT.get(i) + " ");
            }
            System.out.println();
        }
    }

    private static void dfs(int i) {
        if (VISITED[i] == 1) {
            return;
        }
        VISITED[i] = 1;

        boolean hasNext = false;
        char startChar = WORD[i].charAt(WORD[i].length() - 1);
        for (int j = 0; j < N; j++) {
            if (i != j && startChar == WORD[j].charAt(0)) {
                dfs(j);
                hasNext = true;
            }
        }
        if (hasNext) {
            RESULT.add(WORD[i]);
        }
    }

}
