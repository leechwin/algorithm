package bfs1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Vertex {
        public int x;
        public int y;

        public Vertex(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int N;
    public static int MAP[][];
    public static int CNT = 1;
    public static int SIZE[];

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);
        long startTime = System.currentTimeMillis();

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        MAP = new int[N][N];
        SIZE = new int[N * N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                MAP[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();
        print();
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + " ms");
    }

    public static void print() {
        System.out.println(CNT - 1);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (MAP[i][j] > 1) {
                    SIZE[MAP[i][j]]++;
                }
            }
        }
        Arrays.sort(SIZE);
        for (int i = SIZE.length - 1; i >= 0; i--) {
            if (SIZE[i] > 0) {
                System.out.println(SIZE[i]);
            } else {
                break;
            }
        }
    }

    public static void solve() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (MAP[i][j] == 1) {
                    bfs(new Vertex(i, j));
                }
            }
        }
    }

    public static void bfs(Vertex v) {
        Queue<Vertex> queue = new LinkedList<>();
        queue.add(v);
        CNT++;
        while (!queue.isEmpty()) {
            Vertex vert = queue.poll();
            MAP[vert.x][vert.y] = CNT;
            if (isSafe(vert.x - 1, vert.y) && MAP[vert.x - 1][vert.y] == 1) {
                queue.add(new Vertex(vert.x - 1, vert.y));
            }
            if (isSafe(vert.x + 1, vert.y) && MAP[vert.x + 1][vert.y] == 1) {
                queue.add(new Vertex(vert.x + 1, vert.y));
            }
            if (isSafe(vert.x, vert.y - 1) && MAP[vert.x][vert.y - 1] == 1) {
                queue.add(new Vertex(vert.x, vert.y - 1));
            }
            if (isSafe(vert.x, vert.y + 1) && MAP[vert.x][vert.y + 1] == 1) {
                queue.add(new Vertex(vert.x, vert.y + 1));
            }
        }
    }

    public static boolean isSafe(int x, int y) {
        if (x < 0 || x >= N) {
            return false;
        }
        if (y < 0 || y >= N) {
            return false;
        }
        return true;
    }
}
