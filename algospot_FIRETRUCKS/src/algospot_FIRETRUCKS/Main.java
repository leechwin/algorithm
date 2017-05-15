package algospot_FIRETRUCKS;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    public static int C;
    public static int V;
    public static int E;
    public static int n; // 화재장소 수
    public static int m; // 소방서 수

    public static LinkedList<Vertex> ADJ[];
    public static int DIST[];
    public static ArrayList<Integer> N;
    public static ArrayList<Integer> M;

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        for (int i = 0; i < C; i++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            DIST = new int[V];
            Arrays.fill(DIST, Integer.MAX_VALUE);

            ADJ = new LinkedList[V + 1];
            for (int j = 0; j < E; j++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                if (ADJ[start] == null) {
                    ADJ[start] = new LinkedList<>();
                }
                ADJ[start].add(new Vertex(end, cost));

                if (ADJ[end] == null) {
                    ADJ[end] = new LinkedList<>();
                }
                ADJ[end].add(new Vertex(start, cost));
            }

            N = new ArrayList<Integer>();
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                N.add(Integer.parseInt(st.nextToken()));
            }

            M = new ArrayList<Integer>();
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                M.add(Integer.parseInt(st.nextToken()));
            }

            solve();
            System.out.println("end");
        }

    }

    private static void solve() {
        // TODO Auto-generated method stub

    }

    static class Vertex implements Comparable<Vertex> {
        public int here;
        public int cost;

        public Vertex(int here, int cost) {
            this.here = here;
            this.cost = cost;
        }

        @Override
        public int compareTo(Vertex othere) {
            if (this.cost > othere.cost) {
                return 1;
            }
            return -1;
        }

    }
}
