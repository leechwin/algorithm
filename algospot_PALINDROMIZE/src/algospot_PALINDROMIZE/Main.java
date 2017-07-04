package algospot_PALINDROMIZE;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static int C;
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
            String S = br.readLine();
            String reverse = new StringBuilder(S).reverse().toString();
            pi = new int[S.length()];
            int matched = maxOverlap(S, reverse);
            int result = S.length() + S.length() - matched;
            bw.write(result + "\n");
            bw.flush();
        }

        bw.close();
        br.close();
    }

    private static int maxOverlap(String s, String reverse) {
        getPartialMatch(reverse);

        int n = s.length();
        int m = reverse.length();
        int begin = 0;
        int matched = 0;
        while (begin < n) {
            if (matched < m && s.charAt(begin + matched) == reverse.charAt(matched)) {
                matched++;
                if (begin + matched == n) {
                    return matched;
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
