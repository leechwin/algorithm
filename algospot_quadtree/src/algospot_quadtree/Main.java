package algospot_quadtree;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int C;

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());
        for (int i = 0; i < C; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            solve(str);
        }
    }

    private static void solve(String str) {
        // TODO
    }

}
