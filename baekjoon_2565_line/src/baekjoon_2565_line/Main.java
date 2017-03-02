package baekjoon_2565_line;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class LINE implements Comparable<LINE> {
        public int a;
        public int b;
        public int lis = 0;

        public LINE(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(LINE obj) {
            return this.a > obj.a ? 1 : -1;
        }
    }

    public static int N;
    public static List<LINE> LINES = new ArrayList<LINE>();
    public static int MAX_CNT;

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            LINES.add(new LINE(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(LINES);
        LIS();

        System.out.println(N - MAX_CNT);
    }

    public static void LIS() {
        for (int i = 0; i < N; i++) {
            LINE line = LINES.get(i);
            line.lis = 1;
            for (int j = 0; j < i; j++) {
                LINE linePre = LINES.get(j);
                if (line.b > linePre.b && line.lis < linePre.lis + 1) {
                    line.lis = linePre.lis + 1;
                    if (MAX_CNT < line.lis) {
                        MAX_CNT = line.lis;
                    }
                }
            }
        }
    }

}
