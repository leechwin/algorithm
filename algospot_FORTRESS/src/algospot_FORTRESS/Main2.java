package algospot_FORTRESS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {

    public static int C; // 1 <= C <= 100
    public static int N; // 1 <= N <= 100

    public static int X[] = new int[100];
    public static int Y[] = new int[100];
    public static int R[] = new int[100];
    public static int ADJ[][] = new int[100][100];
    public static int INF = 987654321;

    public static void main(String[] args) throws Exception {
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        for (int i = 0; i < C; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            Arrays.fill(X, 0);
            Arrays.fill(Y, 0);
            Arrays.fill(R, 0);
            for (int j = 0; j < N; j++) {
                Arrays.fill(ADJ[j], INF);
                ADJ[j][j] = 0;
            }
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                X[j] = Integer.parseInt(st.nextToken());
                Y[j] = Integer.parseInt(st.nextToken());
                R[j] = Integer.parseInt(st.nextToken());
            }
            int result = solve();
            bw.write(result + "\n");
            bw.flush();
        }
        bw.close();
        br.close();
    }

    private static int solve() {
        makeGraph();

        // floyd
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // 최단거리
                    if (ADJ[i][j] > ADJ[i][k] + ADJ[k][j]) {
                        ADJ[i][j] = ADJ[i][k] + ADJ[k][j];
                    }
                }
            }
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result = Math.max(result, ADJ[i][j]);
            }
        }
        return result;
    }

    private static void makeGraph() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (isChild(i, j)) {
                    ADJ[i][j] = ADJ[j][i] = 1;
                }
            }
        }
    }

    private static boolean isChild(int parent, int child) {
        if (!isContain(parent, child)) {
            return false;
        }
        for (int i = 0; i < N; i++) {
            if (i == parent || i == child) {
                continue;
            }
            // parent > i > child 관계
            // i 는 child 의 parent
            if (isContain(parent, i) && isContain(i, child)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isContain(int parent, int child) {
        // 부모의 반지름이 중심점간의 거리보다 크면 포함
        if (R[parent] > R[child]) {
            if ((R[parent] * R[parent]) >= (X[parent] - X[child]) * (X[parent] - X[child])
                    + (Y[parent] - Y[child]) * (Y[parent] - Y[child])) {
                return true;
            }
        }
        return false;
    }

}
