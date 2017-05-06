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

public class Main2 {

    public static int N;

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        for (int i = 0; i < C; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            LinkedList<Integer> list = new LinkedList<Integer>();
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                list.add(Integer.parseInt(st.nextToken()));
            }

            int result = bfs(list);
            System.out.println(result);
        }
    }

    private static int bfs(LinkedList<Integer> perm) {
        int size = perm.size();
        LinkedList<Integer> sorted = (LinkedList<Integer>) perm.clone();
        Collections.sort(sorted);

        LinkedList<LinkedList<Integer>> queue = new LinkedList<>();
        Map<LinkedList<Integer>, Integer> distance = new HashMap<>();
        queue.push(perm);
        distance.put(perm, 0);

        while (!queue.isEmpty()) {
            LinkedList<Integer> here = queue.removeFirst();
            int cost = distance.get(here);
            // 정렬된 답과 비교
            if (here.equals(sorted)) {
                return cost;
            }

            for (int i = 0; i < size; i++) {
                for (int j = i + 2; j <= size; j++) {
                    List<Integer> subList = here.subList(i, j);
                    // 구간 변형
                    Collections.reverse(subList);
                    if (distance.get(here) == null || distance.get(here) == 0) {
                        LinkedList<Integer> there = (LinkedList<Integer>) here.clone();
                        distance.put(there, cost + 1);
                        queue.addLast(there);
                    }
                    // 원상복구
                    Collections.reverse(subList);
                }
            }

        }
        return -1;
    }

}
