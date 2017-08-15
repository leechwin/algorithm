package baekjoon_2661_dp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static String str = "";

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        dfs(0);

        System.out.println(str);
    }

    private static boolean dfs(int len) {
        if (len == N) {
            return true;
        }

        for (int i = 1; i <= 3; ++i) {
            str += i;
            if (check(str)) {
                if (dfs(len + 1)) {
                    return true;
                }
            }
            str = str.substring(0, str.length() - 1);
        }
        return false;
    }

    private static boolean check(String str) {
        for (int i = 1; i <= str.length() / 2; ++i) {
            if (str.length() - 2 * i < 0) {
                break;
            }
            if (str.substring(str.length() - i, str.length())
                    .equals(str.substring(str.length() - 2 * i, str.length() - 2 * i + i))) {
                return false;
            }
        }
        return true;
    }

}
