package baekjoon_2565_line;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static List<Line> lines = new ArrayList<Line>();
    public static int MAX_CNT;

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            lines.add(new Line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(lines);
        lis();

        bw.write(N - MAX_CNT + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void lis() {
        for (int i = 0; i < N; i++) {
            Line line = lines.get(i);
            line.lis = 1;
            for (int j = 0; j < i; j++) {
                Line linePre = lines.get(j);
                if (line.b > linePre.b && line.lis < linePre.lis + 1) {
                    line.lis = linePre.lis + 1;
                    if (MAX_CNT < line.lis) {
                        MAX_CNT = line.lis;
                    }
                }
            }
        }
    }

    static class Line implements Comparable<Line> {
        public int a;
        public int b;
        public int lis = 0;

        public Line(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Line other) {
            return this.a > other.a ? 1 : -1;
        }
    }
}
