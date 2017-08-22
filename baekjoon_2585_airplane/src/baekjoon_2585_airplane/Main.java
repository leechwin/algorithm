package baekjoon_2585_airplane;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static int K;
    public static int point[][];
    public static int dist[][];
    public static int[] count;

    public static Queue<Integer> queue = new LinkedList<Integer>();

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()) + 2;
        K = Integer.parseInt(st.nextToken());

        dist = new int[N][N];
        point = new int[N][2];
        point[0][0] = point[0][1] = 0;
        point[N - 1][0] = point[N - 1][1] = 10000;

        for (int i = 1; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            point[i][0] = Integer.parseInt(st.nextToken());
            point[i][1] = Integer.parseInt(st.nextToken());
        }

        int min = calc();
        bw.write(min + "");
        bw.close();
    }

    private static int calc() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    continue;
                }
                if (j < i) {
                    dist[i][j] = dist[j][i];
                } else {
                    dist[i][j] = (int) Math.sqrt((point[j][0] - point[i][0]) * (point[j][0] - point[i][0]) + (point[j][1] - point[i][1]) * (point[j][1] - point[i][1])) / 10;
                }
            }
        }

        int start = 0;
        int end = 14142; // max distance
        while (start < end) {
            int mid = (start + end) / 2;
            if (reach(mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    public static boolean reach(int m) {
        count = new int[N];
        queue.clear();
        queue.add(0);

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if (count[curr] > K + 1) {
                return false;
            }
            if (curr == N - 1) {
                return true;
            }
            for (int i = 1; i < N; i++) {
                if (count[i] == 0 && dist[curr][i] < m) {
                    count[i] = count[curr] + 1;
                    queue.add(i);
                }
            }
        }
        return false;
    }

}
