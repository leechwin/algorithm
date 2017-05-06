package algospot_SORTGAME;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static Map<LinkedList<Integer>, Integer> toSort = new HashMap<>();

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());

        // 수열의 길이는 최고 8이기때문에 각 크기별 정렬값을 미리 BFS로 계산
        for (int j = 1; j <= 8; j++) {
            preCalc(j);
        }

        for (int i = 0; i < C; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            LinkedList<Integer> list = new LinkedList<Integer>();
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                list.add(Integer.parseInt(st.nextToken()));
            }

            int result = solve(list);
            System.out.println(result);
        }
    }

    private static int solve(LinkedList<Integer> perm) {
        int n = perm.size();
        LinkedList<Integer> fixed = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int smaller = 0;
            for (int j = 0; j < n; j++) {
                if (perm.get(j) < perm.get(i)) {
                    smaller++;
                }
            }
            fixed.add(i, smaller);
        }
        return toSort.get(fixed);
    }

    private static void preCalc(int n) {
        LinkedList<Integer> perm = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            perm.addLast(i);
        }
        LinkedList<LinkedList<Integer>> queue = new LinkedList<>();
        queue.push(perm);
        toSort.put(perm, 0);

        while (!queue.isEmpty()) {
            LinkedList<Integer> here = queue.removeFirst();
            int cost = toSort.get(here);

            for (int i = 0; i < n; i++) {
                for (int j = i + 2; j <= n; j++) {
                    List<Integer> subList = here.subList(i, j);
                    // 구간 변형
                    Collections.reverse(subList);
                    if (toSort.get(here) == null) {
                        LinkedList<Integer> there = (LinkedList<Integer>) here.clone();
                        toSort.put(there, cost + 1);
                        queue.addLast(there);
                    }
                    // 원상복구
                    Collections.reverse(subList);
                }
            }
        }
    }

}
