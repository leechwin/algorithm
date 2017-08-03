package algospot_ARCTIC;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static int C;
    public static int N;

    public static double X[];
    public static double Y[];
    public static ArrayList<Node> node = new ArrayList<Node>();

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        for (int i = 0; i < C; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            X = new double[N];
            Y = new double[N];
            node.clear();

            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                X[j] = Double.parseDouble(st.nextToken());
                Y[j] = Double.parseDouble(st.nextToken());
            }

            solve();
        }
    }

    private static void solve() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // TODO:
            }
        }
        System.out.println("end");
    }

    static class Node {
        public double x;
        public double y;
        public double dist;

        public Node(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
}
