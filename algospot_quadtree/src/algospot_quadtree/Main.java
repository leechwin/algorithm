package algospot_quadtree;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// O(n)
// 144ms
public class Main {

    public static int C;
    public static int INDEX;

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());
        for (int i = 0; i < C; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            INDEX = -1;
            System.out.println(solve(str));
        }
    }

    // not quad(!x) = return char
    // if quad(x) = 1 -> 3, 2 -> 4
    // 1, 2, 3, 4 -> 3, 4, 1, 2
    private static String solve(String str) {
        INDEX++;
        char c = str.charAt(INDEX);
        if (c == 'w' || c == 'b') {
            return String.valueOf(c);
        } else if (c == 'x') {
            String str1 = solve(str);
            String str2 = solve(str);
            String str3 = solve(str);
            String str4 = solve(str);
            return "x" + str3 + str4 + str1 + str2;
        }
        return "";
    }

}
