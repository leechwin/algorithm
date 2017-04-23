package algospot_gallery;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    public static int C;
    public static int G;
    public static int H;
    public static LinkedList<Integer> MAP[];
    public static int VISITED[];
    public static int DEGREE[];

    public static ArrayList<Integer> RESULT;
    public static int COUNT;
    public static int INSTALLED[];

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
            VISITED = new int[G];
            DEGREE = new int[G];
            MAP = new LinkedList[G];
            RESULT = new ArrayList<Integer>();
            INSTALLED = new int[G];
            COUNT = 0;
            for (int j = 0; j < H; j++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                if (MAP[start] == null) {
                    MAP[start] = new LinkedList<Integer>();
                }
                MAP[start].addLast(end);
            }
            solve();
        }
    }

    private static void solve() {
        for (int i = 0; i < G; i++) {
            if (VISITED[i] == 0) {
                dfs(i);
            }
        }
        // 자식이 있으면 설치
        // 부모가 없으면 설치
        for (int i = RESULT.size() - 1; i >= 0; i--) {
            if (MAP[RESULT.get(i)] != null && INSTALLED[RESULT.get(i)] == 0) {
                INSTALLED[RESULT.get(i)] = 1;
                COUNT++;
            } else if (DEGREE[RESULT.get(i)] == 0 && INSTALLED[RESULT.get(i)] == 0) {
                INSTALLED[RESULT.get(i)] = 1;
                COUNT++;
            }
        }
        System.out.println(COUNT);
    }

    private static void dfs(int g) {
        if (VISITED[g] == 0) {
            VISITED[g] = 1;
            if (MAP[g] != null) {
                for (int i = 0; i < MAP[g].size(); i++) {
                    dfs(MAP[g].get(i));
                    DEGREE[g]++;
                    DEGREE[MAP[g].get(i)]++;
                }
            }
            RESULT.add(g);
        }
    }

}
