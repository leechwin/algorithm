package algospot_INSERTION;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Random;
import java.util.StringTokenizer;

public class Main {

    public static int C;
    public static int N;
    public static int NUM[];
    public static int RESULT[];
    public static Node ROOT;

    public static Random random = new Random();

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
            st = new StringTokenizer(br.readLine());
            NUM = new int[N];
            RESULT = new int[N];
            ROOT = null;
            for (int j = 0; j < N; j++) {
                NUM[j] = Integer.parseInt(st.nextToken());
                Node node = new Node(j + 1);
                ROOT = insert(ROOT, node);
            }

            for (int j = N - 1; j >= 0; j--) {
                int num = NUM[j];
                int nodeNum = kth(ROOT, j + 1 - num);
                RESULT[j] = nodeNum;
                ROOT = erase(ROOT, nodeNum);
            }
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < N; j++) {
                sb.append(RESULT[j]).append(" ");
            }
            bw.write(sb.toString() + "\n");
            bw.flush();
        }
        bw.close();
        br.close();
    }

    // 트랩노드
    static class Node {
        public int key;
        public int priority;
        public int size = 1;
        public Node left = null;
        public Node right = null;

        public Node(int key) {
            this.key = key;
            this.priority = random.nextInt();
        }

        public void setLeft(Node left) {
            this.left = left;
            calcSize();
        }

        public void setRight(Node right) {
            this.right = right;
            calcSize();
        }

        public void calcSize() {
            size = 1;
            if (left != null) {
                size += left.size;
            }
            if (right != null) {
                size += right.size;
            }
        }
    }

    static class NodePair {
        public Node left;
        public Node right;

        public NodePair(Node leftNode, Node rightNode) {
            this.left = leftNode;
            this.right = rightNode;
        }
    }

    // 노드를 키값기준으로 분리
    public static NodePair split(Node root, int key) {
        if (root == null) {
            return new NodePair(null, null);
        }

        if (root.key < key) {
            // split right
            NodePair rs = split(root.right, key);
            root.setRight(rs.left);
            return new NodePair(root, rs.right);
        } else {
            // split left
            NodePair ls = split(root.left, key);
            root.setLeft(ls.right);
            return new NodePair(ls.left, root);
        }
    }

    public static Node insert(Node root, Node node) {
        if (root == null) {
            return node;
        }

        if (root.priority < node.priority) {
            // change the root
            NodePair sp = split(root, node.key);
            node.setLeft(sp.left);
            node.setRight(sp.right);
            return node;
        } else if (root.key > node.key) {
            // append left
            Node newRoot = insert(root.left, node);
            root.setLeft(newRoot);
            return root;
        } else {
            // append right
            Node newRoot = insert(root.right, node);
            root.setRight(newRoot);
            return root;
        }
    }

    public static Node merge(Node a, Node b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }

        if (a.priority < b.priority) {
            // b is root
            b.setLeft(merge(a, b.left));
            return b;
        } else {
            // a is root
            a.setRight(merge(a.right, b));
            return a;
        }
    }

    public static Node erase(Node root, int key) {
        if (root == null) {
            return root;
        }
        if (root.key == key) {
            Node node = merge(root.left, root.right);
            root = null;
            return node;
        }

        if (root.key > key) {
            // remove left of root
            root.setLeft(erase(root.left, key));
        } else {
            // remove right of root
            root.setRight(erase(root.right, key));
        }
        return root;
    }

    public static int kth(Node root, int k) {
        int leftSize = 0;
        if (root.left != null) {
            leftSize = root.left.size;
        }

        // k번째노드는 왼쪽 서브트리에 속해있다.
        if (k <= leftSize) {
            return kth(root.left, k);
        }

        // 루트가 k번째노드
        if (k == leftSize + 1) {
            return root.key;
        }

        // k번째 노드는 오른쪽 서브트리에서 k - leftSize - 1 번째 노드(k - 왼쪽 서브트리 - 자기자신)
        return kth(root.right, k - leftSize - 1);
    }

}
