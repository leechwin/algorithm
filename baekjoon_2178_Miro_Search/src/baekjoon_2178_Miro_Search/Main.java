package baekjoon_2178_Miro_Search;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static int MAP[][] = new int[101][101];
    public static int N;
    public static int M;
    public static int VISITED[][] = new int[101][101];
    public static Queue<Vertex> queue = new LinkedList<>();
    public static Vertex start;
    public static Vertex end;

    static class Vertex {
        int x;
        int y;
        int cnt;

        public Vertex(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        @Override
        public boolean equals(Object obj) {
            Vertex v = (Vertex) obj;
            if (this.x == v.x && this.y == v.y) {
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String input = st.nextToken();
            for (int j = 0; j < M; j++) {
                MAP[i][j] = Character.getNumericValue(input.charAt(j));
            }
        }

        start = new Vertex(0, 0, 1);
        queue.add(start);
        end = new Vertex(N - 1, M - 1, 0);
        Vertex result = bfs();
        if (result != null) {
            System.out.println(result.cnt);
        }
    }

    public static Vertex bfs() {
        while (!queue.isEmpty()) {
            Vertex v = queue.poll();
            if (v.equals(end)) {
                return v;
            }
            if (isSafe(v.x - 1, v.y) && VISITED[v.x - 1][v.y] == 0) {
                VISITED[v.x - 1][v.y] = 1;
                queue.add(new Vertex(v.x - 1, v.y, v.cnt + 1));
            }
            if (isSafe(v.x + 1, v.y) && VISITED[v.x + 1][v.y] == 0) {
                VISITED[v.x + 1][v.y] = 1;
                queue.add(new Vertex(v.x + 1, v.y, v.cnt + 1));
            }
            if (isSafe(v.x, v.y - 1) && VISITED[v.x][v.y - 1] == 0) {
                VISITED[v.x][v.y - 1] = 1;
                queue.add(new Vertex(v.x, v.y - 1, v.cnt + 1));
            }
            if (isSafe(v.x, v.y + 1) && VISITED[v.x][v.y + 1] == 0) {
                VISITED[v.x][v.y + 1] = 1;
                queue.add(new Vertex(v.x, v.y + 1, v.cnt + 1));
            }
        }
        return null;
    }

    public static boolean isSafe(int x, int y) {
        if (x < 0 || x >= N) {
            return false;
        }
        if (y < 0 || y >= M) {
            return false;
        }
        if (MAP[x][y] == 0) {
            return false;
        }
        return true;
    }

}
