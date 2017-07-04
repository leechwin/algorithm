package algospot_NAMING;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {

    public static int pi[];

    public static void main(String[] args) throws Exception {
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        String father = br.readLine();
        String mother = br.readLine();
        String S = father + mother;

        pi = new int[S.length() + 1];
        ArrayList<Integer> result = KMP(S);
        for (int i = result.size() - 1; i >= 0; i--) {
            bw.write(result.get(i) + " ");
            bw.flush();
        }

        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static ArrayList<Integer> KMP(String s) {
        getPartialMatch(s);
        ArrayList<Integer> result = new ArrayList<Integer>();
        int k = s.length();
        while (k > 0) {
            result.add(k);
            k = pi[k - 1];
        }
        return result;
    }

    // N에서 자기 자신을 찾으면서 나타나는 부분일치를 이용해 pi[]를 계산
    // pi[i] = N[..i]의 접미사도 되고 접두사도 되는 문자열의 최대 길이
    private static void getPartialMatch(String N) {
        // KMP로 자기 자신을 찾는다
        // N을 N에서 찾는다. beging=0이면 자기자신을 찾으므로 안됨
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
