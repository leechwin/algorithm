package algospot_gallery;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    public static int C;
    public static int G;
    public static int H;
    public static LinkedList<Integer> ADJ[];
    public static boolean VISITED[];

    public static int UNWATCHED = 0;
    public static int WATCHED = 1;
    public static int INSTALLED = 2;

    public static int INSTALLED_COUNT;

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());

        for (int i = 0; i < C; i++) {
            st = new StringTokenizer(br.readLine());
            G = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            VISITED = new boolean[G];
            Arrays.fill(VISITED, false);
            ADJ = new LinkedList[G];
            INSTALLED_COUNT = 0;
            for (int j = 0; j < H; j++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                // 무방향 그래프 주의 (2번째 test case = 2)
                if (ADJ[start] == null) {
                    ADJ[start] = new LinkedList<Integer>();
                }
                ADJ[start].addLast(end);
                if (ADJ[end] == null) {
                    ADJ[end] = new LinkedList<Integer>();
                }
                ADJ[end].addLast(start);
            }
            solve();
        }
    }

    private static void solve() {
        for (int i = 0; i < G; i++) {
            if (VISITED[i] == false && dfs(i) == UNWATCHED) {
                INSTALLED_COUNT++;
            }
        }
        System.out.println(INSTALLED_COUNT);
    }

    private static int dfs(int here) {
        VISITED[here] = true;
        int childeren[] = { 0, 0, 0 };
        if (ADJ[here] != null) {
            for (int i = 0; i < ADJ[here].size(); i++) {
                int there = ADJ[here].get(i);
                if (VISITED[there] == false) {
                    childeren[dfs(there)]++;
                }
            }
        }

        if (childeren[UNWATCHED] > 0) {
            ++INSTALLED_COUNT;
            return INSTALLED;
        }

        if (childeren[INSTALLED] > 0) {
            return WATCHED;
        }
        return UNWATCHED;
    }
}
