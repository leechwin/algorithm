package algospot_CHILDRENDAY;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    public static int T;
    public static String D;
    public static int N;
    public static int M;

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            D = st.nextToken();
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            String result = solve();
            System.out.println(result);
        }
    }

    private static int append(int here, int x, int n) {
        int there = here * 10 + x;
        if (there >= n) {
            return n + there % n;
        }
        return there % n;
    }

    // digits에 속한 숫자들만으로 구성되고 C mod n == m인 최소의 C를 찾음
    private static String solve() {
        // 간선의 번호를 오름차순으로 정렬해 두면 사전순으로 가장 앞에 있는 경로를 찾을 수 있다
        char[] chars = D.toCharArray();
        Arrays.sort(chars);
        D = String.valueOf(chars);

        int parent[] = new int[2 * N];
        int choice[] = new int[2 * N];
        Arrays.fill(parent, -1);
        Arrays.fill(choice, -1);
        LinkedList<Integer> queue = new LinkedList<Integer>();

        parent[0] = 0;
        queue.push(0);
        while (!queue.isEmpty()) {
            int here = queue.removeFirst();
            for (int i = 0; i < D.length(); i++) {
                int there = append(here, D.charAt(i) - '0', N);
                if (parent[there] == -1) {
                    parent[there] = here;
                    choice[there] = D.charAt(i) - '0';
                    queue.addLast(there);
                }
            }
        }

        if (parent[N + M] == -1) {
            return "IMPOSSIBLE";
        }

        // 부모로 가는 연결을 따라가면서 C를 계산
        String ret = "";
        int here = N + M;
        while (parent[here] != here) {
            ret += choice[here];
            here = parent[here];
        }

        String reverse = new StringBuffer(ret).reverse().toString();
        return reverse;
    }

}
