package algospot_NERD2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

    public static int C; // 1 <= C <= 50
    public static int N; // 1 <= N <= 50000

    public static TreeMap<Integer, Integer> treeMap = new TreeMap<>();
    public static int CNT;

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
            treeMap.clear();
            CNT = 0;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                int p = Integer.parseInt(st.nextToken());
                int q = Integer.parseInt(st.nextToken());

                // is nerd?
                Entry<Integer, Integer> entry = treeMap.higherEntry(p);
                if (entry == null || entry.getValue() < q) {
                    treeMap.put(p, q);

                    // recalc
                    while ((entry = treeMap.lowerEntry(p)) != null) {
                        if (entry.getValue() < q) {
                            treeMap.remove(entry.getKey());
                        } else {
                            break;
                        }
                    }
                }
                CNT += treeMap.size();
            }
            bw.write(CNT + "\n");
            bw.flush();
        }
        bw.close();
        br.close();
    }
}
