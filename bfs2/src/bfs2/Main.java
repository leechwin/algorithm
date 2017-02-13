package bfs2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Vertex {
        public int x;
        public int y;
        public int cnt;

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

    public static int H;
    public static int W;
    public static char MAP[][];

    public static Vertex start;
    public static Vertex goal;
    public static Queue queue = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);
        long startTime = System.currentTimeMillis();

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        MAP = new char[H][W];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            String input = st.nextToken();
            for (int j = 0; j < W; j++) {
                MAP[i][j] = input.charAt(j);
                if (input.charAt(j) == 'S') {
                    start = new Vertex(i, j, 0);
                } else if (input.charAt(j) == 'G') {
                    goal = new Vertex(i, j, 0);
                }
            }
        }

        queue.add(start);
        Vertex result = bfs();
        if (result == null) {
            System.out.println("-1");
        } else {
            System.out.println(result.cnt);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + " ms");
    }

    public static Vertex bfs() {
        while (!queue.isEmpty()) {
            Vertex v = (Vertex) queue.poll();
            if (v.equals(goal)) {
                return v;
            }
            if (isSafe(v.x - 1, v.y)) {
                Vertex vv = new Vertex(v.x - 1, v.y, v.cnt + 1);
                queue.add(vv);
            }
            if (isSafe(v.x + 1, v.y)) {
                Vertex vv = new Vertex(v.x + 1, v.y, v.cnt + 1);
                queue.add(vv);
            }
            if (isSafe(v.x, v.y - 1)) {
                Vertex vv = new Vertex(v.x, v.y - 1, v.cnt + 1);
                queue.add(vv);
            }
            if (isSafe(v.x, v.y + 1)) {
                Vertex vv = new Vertex(v.x, v.y + 1, v.cnt + 1);
                queue.add(vv);
            }
        }
        return null;
    }

    public static boolean isSafe(int x, int y) {
        if (x < 0 || x >= H) {
            return false;
        }
        if (y < 0 || y >= W) {
            return false;
        }
        if (MAP[x][y] == '#') {
            return false;
        }
        return true;
    }

}
