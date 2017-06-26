package algospot_FORTRESS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    public static int C; // 1 <= C <= 100
    public static int N; // 1 <= N <= 100

    public static int X[] = new int[100];
    public static int Y[] = new int[100];
    public static int R[] = new int[100];
    public static int longest; // 리프-리프 경로의 길이

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

            Arrays.fill(X, 0);
            Arrays.fill(Y, 0);
            Arrays.fill(R, 0);
            N = Integer.parseInt(st.nextToken());
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                X[j] = Integer.parseInt(st.nextToken());
                Y[j] = Integer.parseInt(st.nextToken());
                R[j] = Integer.parseInt(st.nextToken());
            }
            TreeNode ansTree = getTree(0);
            bw.write(solve(ansTree) + "\n");
            bw.flush();
        }
        bw.close();
        br.close();
    }

    private static TreeNode getTree(int root) {
        TreeNode ret = new TreeNode();
        for (int i = 0; i < N; i++) {
            if (isChild(root, i)) {
                ret.children.add(getTree(i));
            }
        }
        return ret;
    }

    private static boolean isChild(int parent, int child) {
        if (!encloses(parent, child))
            return false;

        for (int i = 0; i < N; i++) {
            if (i == parent && i == child) {
                continue;
            }
            if (encloses(parent, i) && encloses(i, child)) {
                return false;
            }
        }

        return true;
    }

    private static int sqr(int x) {
        return x * x;
    }

    private static int sqrdist(int a, int b) {
        return sqr(Y[a] - Y[b]) + sqr(X[a] - X[b]);
    }

    private static boolean encloses(int a, int b) {
        return R[a] > R[b] && sqrdist(a, b) < sqr(R[a] - R[b]);
    }

    private static int solve(TreeNode root) {
        longest = 0;
        // 트리의 높이와 최대 리프-리프 경로 길이중 큰 값
        int h = height(root);
        return Math.max(longest, h);
    }

    // root를 루트로하는 서브트리의 높이를 반환
    private static int height(TreeNode root) {
        ArrayList<Integer> heights = new ArrayList<Integer>();
        for (TreeNode child : root.children) {
            heights.add(height(child));
        }

        if (heights.isEmpty()) {
            return 0;
        }

        Collections.sort(heights);

        if (heights.size() >= 2) {
            // root를 최상위로 하는 리프-리프 값을 갱신
            longest = Math.max(longest, 2 + heights.get(heights.size() - 2) + heights.get(heights.size() - 1));
        }

        // 높이
        return heights.get(heights.size() - 1) + 1;
    }

    static class TreeNode {
        ArrayList<TreeNode> children = new ArrayList<TreeNode>();
    }

}
