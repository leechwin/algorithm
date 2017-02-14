package full_search1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static int W;
    public static int FISH[];

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);
        long startTime = System.currentTimeMillis();

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        FISH = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            FISH[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        for (int i = 1; i < N - 1; i++) {
            int tmp = FISH[i - 1] + FISH[i] + FISH[i + 1];
            if (sum < tmp) {
                sum = tmp;
            }
        }
        System.out.println(sum);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + " ms");
    }

}
