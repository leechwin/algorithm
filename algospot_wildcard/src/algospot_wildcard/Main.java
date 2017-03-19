package algospot_wildcard;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static int C;
    public static String W;
    public static int N;
    public static String[] WORD;

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        for (int i = 0; i < C; i++) {
            st = new StringTokenizer(br.readLine());
            W = st.nextToken();
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            WORD = new String[N];
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                WORD[j] = st.nextToken();
            }

            solve();
        }
    }

    private static void solve() {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < N; i++) {
            if (dp(W, WORD[i])) {
                list.add(WORD[i]);
            }
        }

        // sort
        Collections.sort(list);

        // print
        for (String word : list) {
            System.out.println(word);
        }
    }

    private static boolean dp(String w, String word) {
        int pos = 0;
        while (pos < w.length() && pos < word.length()) {
            if (w.charAt(pos) == '?' || w.charAt(pos) == word.charAt(pos)) {
                pos++;
            } else {
                break;
            }
        }

        // solve to '?'
        if (pos == w.length()) {
            if (pos == word.length()) {
                return true;
            } else {
                return false;
            }
        } else if (w.charAt(pos) == '*') { // solve to '*'
            String subString = w.substring(pos + 1);
            for (int i = 0; pos + i <= word.length(); i++) {
                // search matched case
                if (dp(subString, word.substring(pos + i))) {
                    return true;
                }
            }
        }

        return false;
    }

}
