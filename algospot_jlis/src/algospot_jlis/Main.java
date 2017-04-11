package algospot_jlis;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int C;
    public static int N, M;
    public static int NN[], MM[];
    public static int CACHE[][];

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
            M = Integer.parseInt(st.nextToken());
            NN = new int[N];
            MM = new int[M];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                NN[j] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                MM[j] = Integer.parseInt(st.nextToken());
            }
            CACHE = new int[N + 1][M + 1];
            for (int j = 0; j <= N; j++) {
                Arrays.fill(CACHE[j], -1);
            }

            // solve
            System.out.println(jlis(-1, -1));
        }
    }

    // NN[indexA] 와 MM[indexB]에서 시작한 합친증가부분수열의 최대길이
    private static int jlis(int indexA, int indexB) {
        if (CACHE[indexA + 1][indexB + 1] != -1) {
            return CACHE[indexA + 1][indexB + 1];
        }

        int a = Integer.MIN_VALUE;
        if (indexA != -1) {
            a = NN[indexA];
        }
        int b = Integer.MIN_VALUE;
        if (indexB != -1) {
            b = MM[indexB];
        }
        int maxElement = Math.max(a, b);

        int ret = 0;
        for (int nextA = indexA + 1; nextA < N; nextA++) {
            if (maxElement < NN[nextA]) {
                ret = Math.max(ret, jlis(nextA, indexB) + 1);
            }
        }
        for (int nextB = indexB + 1; nextB < M; nextB++) {
            if (maxElement < MM[nextB]) {
                ret = Math.max(ret, jlis(indexA, nextB) + 1);
            }
        }
        CACHE[indexA + 1][indexB + 1] = ret;
        return ret;
    }

}
