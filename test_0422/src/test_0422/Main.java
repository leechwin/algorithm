package test_0422;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int C;
    public static int N; // 1,000,000
    public static int K; // 1,000,000, N은 K까지의 값을 가질수있다.
    public static int L; // K - L 개이상의 숫자종류
    public static int MAX_COUNT;
    public static int NUMBER[];
    public static int COUNT[];

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        long startTime = System.currentTimeMillis();
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        for (int i = 0; i < C; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            NUMBER = new int[N];
            COUNT = new int[K + 1];
            for (int j = 0; j < N; j++) {
                NUMBER[j] = Integer.parseInt(st.nextToken());
            }

            MAX_COUNT = K - L;
            System.out.println("#" + (i + 1) + " " + solve());
        }
        long endTime = System.currentTimeMillis();

        System.out.println((endTime - startTime) / 1000.0 + " sec");
    }

    private static long solve() {
        int usedCount = 0;
        long result = 0;

        int leftIndex = 0;
        for (int i = 0; i < N; i++) {
            if (usedCount < MAX_COUNT) {
                if (COUNT[NUMBER[i]] == 0) {
                    usedCount++;
                }
                COUNT[NUMBER[i]]++;
            }

            while (usedCount >= MAX_COUNT) {
                result += N - i;
                COUNT[NUMBER[leftIndex]]--;
                if (COUNT[NUMBER[leftIndex]] == 0) {
                    usedCount--;
                }
                leftIndex++;
            }
        }

        return result;
    }

}
