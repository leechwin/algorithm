package baekjoon_7469_k_number;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static int N; // 1 <= n <= 100,000
    public static int M; // 1 <= m <= 5,000
    public static int I, J, K;
    public static int arr[];

    public static void main(String[] args) throws Exception {
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        RMQ rmq = new RMQ();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            I = Integer.parseInt(st.nextToken());
            J = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            ArrayList<ArrayList<Integer>> result = new ArrayList<>();
            rmq.query(result, I - 1, J - 1, 1);

            int leftVal = rmq.range[1].get(0);
            int rightVal = rmq.range[1].get(N - 1);
            int mid = 0;
            Integer answer = 0;
            while (leftVal <= rightVal) {
                mid = (leftVal + rightVal) / 2;

                int low = parameticSearch(result, mid, K);

                if (low == K) {
                    answer = mid;
                }

                if (low < K) {
                    leftVal = mid + 1;
                } else {
                    rightVal = mid - 1;
                }
            }
            bw.write(answer.toString());
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    private static int parameticSearch(ArrayList<ArrayList<Integer>> result, int mid, int k) {
        int low = 0;
        for (ArrayList<Integer> list : result) {
            low += binarySearch(list, 0, list.size() - 1, mid);

            if (low > k) {
                break;
            }
        }
        return low;
    }

    private static int binarySearch(ArrayList<Integer> arr, int left, int right, int value) {
        if (value < arr.get(left)) {
            return 0;
        }

        if (arr.get(right) < value) {
            return right + 1;
        }

        int mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;

            if (arr.get(mid) == value) {
                return mid + 1;
            }

            if (arr.get(mid) < value) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (arr.get(mid) < value) {
            return mid + 1;
        }
        return mid;
    }

    static class RMQ {
        int n;
        ArrayList<Integer> range[];

        public RMQ() {
            n = N;
            range = new ArrayList[getSize(n)];
            init(1, 0, n - 1);
        }

        private int getSize(int n) {
            Double H = Math.ceil(Math.log(n) / Math.log(2.0));
            return 1 << (H.intValue() + 1);
        }

        private ArrayList<Integer> init(int node, int nodeLeft, int nodeRight) {
            if (nodeLeft == nodeRight) {
                if (range[node] == null) {
                    range[node] = new ArrayList<Integer>();
                }
                range[node].add(arr[nodeLeft]);
                return range[node];
            }
            int mid = (nodeLeft + nodeRight) / 2;
            ArrayList<Integer> leftInit = init(node * 2, nodeLeft, mid);
            ArrayList<Integer> rightInit = init(node * 2 + 1, mid + 1, nodeRight);

            // merge sort
            ArrayList<Integer> merged = new ArrayList<Integer>();
            int leftI = 0;
            int rightI = 0;
            while (leftI < leftInit.size()) {
                int leftVal = leftInit.get(leftI);
                if (rightI < rightInit.size()) {
                    // compare with right
                    int rightVal = rightInit.get(rightI);
                    if (leftVal < rightVal) {
                        merged.add(leftVal);
                        leftI++;
                    } else {
                        merged.add(rightVal);
                        rightI++;
                    }
                } else {
                    // add left
                    merged.add(leftVal);
                    leftI++;
                }
            }
            // add remained val
            while (rightI < rightInit.size()) {
                merged.add(rightInit.get(rightI));
                rightI++;
            }

            return range[node] = merged;
        }

        private void query(ArrayList<ArrayList<Integer>> result, int left, int right, int node, int nodeLeft,
                int nodeRight) {
            if (right < nodeLeft || nodeRight < left) {
                return;
            }
            if (left <= nodeLeft && nodeRight <= right) {
                result.add(range[node]);
                return;
            }
            int mid = (nodeLeft + nodeRight) / 2;
            query(result, left, right, node * 2, nodeLeft, mid);
            query(result, left, right, node * 2 + 1, mid + 1, nodeRight);
        }

        public void query(ArrayList<ArrayList<Integer>> result, int left, int right, int node) {
            query(result, left, right, node, 0, n - 1);
        }
    }

}
