package algospot_TRAVERSAL;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static int C;
    public static int N;
    public static ArrayList<Integer> preOrder = new ArrayList<Integer>();
    public static ArrayList<Integer> inOrder = new ArrayList<Integer>();
    public static BufferedWriter bw = null;

    public static void main(String[] args) throws Exception {
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        bw = new BufferedWriter(osw);

        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        for (int i = 0; i < C; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            preOrder.clear();
            inOrder.clear();

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                preOrder.add(Integer.parseInt(st.nextToken()));
            }
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                inOrder.add(Integer.parseInt(st.nextToken()));
            }

            printPostOrder(preOrder, inOrder);
            bw.newLine();
            bw.flush();
        }
        bw.close();
        br.close();
    }

    // 전위 탐색순서 (root -> left -> right)
    // 중위 탐색순서 (left -> root -> right)
    // 후위 탐색순서 (left -> right -> root)
    public static void printPostOrder(List<Integer> pre, List<Integer> in) throws Exception {
        if (pre.isEmpty()) {
            return;
        }

        int root = pre.get(0);
        int n = pre.size();
        int leftSize = in.indexOf(root);

        // 후위 탐색 순서로 출력 (left -> right -> root)

        // left 출력 (pre에서 root index(0)을 제외)
        printPostOrder(pre.subList(1, leftSize + 1), in.subList(0, leftSize));

        // right 출력
        printPostOrder(pre.subList(leftSize + 1, n), in.subList(leftSize + 1, n));

        bw.write(root + " ");
        bw.flush();
    }

}
