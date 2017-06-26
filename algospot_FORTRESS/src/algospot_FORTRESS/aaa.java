package algospot_FORTRESS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class aaa {
    private static class TreeNode {
        List<TreeNode> children = new ArrayList<TreeNode>();
    }

    private static int longest; // 지금까지 찾은 가장 긴 잎-잎 경로의 길이

    public static void main(String[] args) {
        try {
            // BufferedReader input = new BufferedReader(new
            // InputStreamReader(System.in));
            BufferedReader input = new BufferedReader(new FileReader("fortress.txt"));
            int T = Integer.parseInt(input.readLine());
            for (int t = 0; t < T; t++) {
                int N = Integer.parseInt(input.readLine()); // 성벽의 수 N
                List<Wall> wallList = new ArrayList<Wall>();
                for (int n = 0; n < N; n++) {
                    StringTokenizer strWall = new StringTokenizer(input.readLine());
                    Wall wall = new Wall();
                    wall.x = Integer.parseInt(strWall.nextToken());
                    wall.y = Integer.parseInt(strWall.nextToken());
                    wall.radius = Integer.parseInt(strWall.nextToken());
                    wallList.add(wall);
                }
                /*
                 * 1단계 : 트리 만들기
                 */
                Collections.sort(wallList);
                makeTree(wallList);
                /*
                 * 2단계 : 가장 긴 경로 구하기
                 */
                TreeNode rootNode = wallList.get(wallList.size() - 1).node;
                int result = solve(rootNode);
                System.out.println(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int solve(TreeNode root) {
        longest = 0;
        // 트리의 높이와 최대 잎-잎 경로 길이 중 큰 것을 선택한다.
        int h = height(root);
        return Math.max(longest, h);
    }

    // root를 루트로 하는 서브트리의 높이를 계산한다. longest 도 갱신해준다.
    private static int height(TreeNode root) {
        // 각 자식을 루트로 하는 서브트리의 높이를 계산한다.
        List<Integer> heights = new ArrayList<Integer>();
        for (TreeNode child : root.children) {
            heights.add(height(child));
        }
        // 자식이 없으면 트리의 높이는 0 이고 longest 를 계산할 필요도 없으므로 0 리턴.
        if (heights.isEmpty()) {
            return 0;
        }
        Collections.sort(heights);
        // root 를 최상위 노드로 하는 경로 계산.
        if (heights.size() >= 2) {
            longest = Math.max(longest, 2 + heights.get(heights.size() - 2) + heights.get(heights.size() - 1));
        }
        // 트리의 높이는 서브트리 높이의 최대치에 1을 더해서 계산.
        return heights.get(heights.size() - 1) + 1;
    }

    /*
     * Make Tree
     */
    private static class Wall implements Comparable<Wall> {
        int x;
        int y;
        int radius;
        TreeNode node = new TreeNode();

        // 성벽 wall 을 포함하는지 확인
        public boolean encloses(Wall wall) {
            return (radius > wall.radius) && (sqrdist(wall) < sqr(radius - wall.radius));
        }

        // 성벽 wall 의 중심전 간의 거리의 제곱
        private int sqrdist(Wall wall) {
            return sqr(y - wall.y) + sqr(x - wall.x);
        }

        private int sqr(int x) {
            return x * x;
        }

        @Override
        public int compareTo(Wall compare) {
            if (radius > compare.radius) {
                return 1;
            } else if (radius < compare.radius) {
                return -1;
            }
            return 0;
        }
    }

    private static void makeTree(List<Wall> sortedWallList) {
        for (int n = 0; n < sortedWallList.size(); n++) {
            Wall wall = sortedWallList.get(n);
            for (int nn = n + 1; nn < sortedWallList.size(); nn++) {
                if (sortedWallList.get(nn).encloses(wall)) {
                    sortedWallList.get(nn).node.children.add(wall.node);
                    break;
                }
            }
        }
    }
}