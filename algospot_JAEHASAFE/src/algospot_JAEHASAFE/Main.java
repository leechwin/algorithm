package algospot_JAEHASAFE;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static int C;
    public static int N;
    public static String STR[];
    public static int pi[];

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
            STR = new String[N + 1];
            for (int j = 0; j < N + 1; j++) {
                STR[j] = br.readLine();
            }
            int result = 0;
            for (int j = 0; j < N; j++) {
                if (j % 2 == 0) {
                    result += shifts(STR[j + 1], STR[j]);
                } else {
                    result += shifts(STR[j], STR[j + 1]);
                }
            }
            bw.write(result + "\n");
            bw.flush();
        }

        bw.close();
        br.close();
    }

    private static int shifts(String original, String target) {
        return kmpSearch(original + original, target);
    }

    private static int kmpSearch(String original, String target) {
        getPartialMatch(target);

        int n = original.length();
        int m = target.length();
        int begin = 0;
        int matched = 0;
        while (begin <= n - m) {
            if (begin < m && original.charAt(begin + matched) == target.charAt(matched)) {
                matched++;
                if (matched == m) {
                    return begin;
                }
            } else {
                if (matched == 0) {
                    begin++;
                } else {
                    begin += matched - pi[matched - 1];
                    matched = pi[matched - 1];
                }
            }
        }
        return 0;
    }

    private static void getPartialMatch(String N) {
        pi = new int[N.length()];
        int begin = 1;
        int matched = 0;
        int m = N.length();
        while (begin + matched < m) {
            if (N.charAt(begin + matched) == N.charAt(matched)) {
                matched++;
                pi[begin + matched - 1] = matched;
            } else {
                if (matched == 0) {
                    begin++;
                } else {
                    begin += matched - pi[matched - 1];
                    matched = pi[matched - 1];
                }
            }
        }
    }

}
