package remote_control;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static int A;
    public static int B;
    public static int result = 40;

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);
        long startTime = System.currentTimeMillis();

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        dfs(A, 0);
        System.out.println(result);

        // bfs();

        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + " ms");
    }

    public static void dfs(int temp, int cnt) {
        if (cnt > result) {
            return;
        }
        if (temp == B) {
            if (result > cnt) {
                result = cnt;
            }
            return;
        }
        if (temp < B) {
            dfs(temp + 10, cnt + 1);
            dfs(temp + 5, cnt + 1);
            dfs(temp + 1, cnt + 1);
        } else {
            dfs(temp - 10, cnt + 1);
            dfs(temp - 5, cnt + 1);
            dfs(temp - 1, cnt + 1);
        }
    }

    static class Degree {
        public int temp;
        public int cnt;

        public Degree(int temp, int cnt) {
            this.temp = temp;
            this.cnt = cnt;
        }
    }

    public static Queue<Degree> queue = new LinkedList<Degree>();

    public static void bfs() {
        queue.add(new Degree(A, 0));

        while (!queue.isEmpty()) {
            Degree degree = queue.poll();
            if (degree.temp == B) {
                System.out.println(degree.cnt);
                break;
            }
            if (degree.temp < B) {
                queue.add(new Degree(degree.temp + 10, degree.cnt + 1));
                queue.add(new Degree(degree.temp + 5, degree.cnt + 1));
                queue.add(new Degree(degree.temp + 1, degree.cnt + 1));
            } else {
                queue.add(new Degree(degree.temp - 10, degree.cnt + 1));
                queue.add(new Degree(degree.temp - 5, degree.cnt + 1));
                queue.add(new Degree(degree.temp - 1, degree.cnt + 1));
            }

        }

    }
}
