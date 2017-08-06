package baekjoon_2568_line2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static List<Line> lists = new ArrayList<Line>();
    public static int MAX_LIS;

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            lists.add(new Line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(lists);
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = lists.get(i).b;
        }
        // FIXME: http://www.jungol.co.kr/theme/jungol/reinfo.php?sid=1793898

        int a = LISLength(A, N);
        System.out.println();
    }

    public static int LISLength(int[] A, int size) {
        int[] indexTable = new int[size];
        int[] tailTable = new int[size];
        int lisLength = 0; // always points empty slot

        // 초기값
        tailTable[0] = A[0];
        lisLength = 1;

        for (int i = 1; i < size; i++) {
            // 후보값이 LIS의 처음 값보다 작은지?
            if (A[i] < tailTable[0]) {
                tailTable[0] = A[i];
                indexTable[0] = i;
            }
            // 후보값이 LIS의 마지막 값 보다 큰지?
            else if (A[i] > tailTable[lisLength - 1]) {
                tailTable[lisLength] = A[i];
                indexTable[lisLength] = i;
                lisLength++;
            } else {
                // CeilIndex를 찾고 replace한다.
                int idx1 = Arrays.binarySearch(tailTable, 0, lisLength, A[i]);
                idx1 = idx1 < 0 ? -idx1 - 1 : idx1;
                tailTable[idx1] = A[i];
                indexTable[idx1] = i;
            }
        }

        return lisLength;
    }

    static class Line implements Comparable<Line> {
        public int a;
        public int b;

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
