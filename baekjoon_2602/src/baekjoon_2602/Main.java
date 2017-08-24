package baekjoon_2602;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

    public static String roll;
    public static String devil;
    public static String angel;

    public static int DP[][][]; // 돌타입, 돌길이, 두루마리길이

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        roll = br.readLine();
        devil = br.readLine(); // type 0
        angel = br.readLine(); // type 1

        DP = new int[2][devil.length()][roll.length()];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < devil.length(); j++) {
                Arrays.fill(DP[i][j], -1);
            }
        }

        int resultDevil = calc(0, 0, 0);
        int resultAngel = calc(1, 0, 0);
        bw.write(resultDevil + resultAngel + "\n");

        br.close();
        bw.close();
    }

    private static int calc(int dolType, int dolPos, int rollPos) {
        if (rollPos == roll.length()) {
            return 1;
        }
        if (dolPos == devil.length()) {
            return 0;
        }

        if (DP[dolType][dolPos][rollPos] != -1) {
            return DP[dolType][dolPos][rollPos];
        }

        DP[dolType][dolPos][rollPos] = 0;

        if (dolType == 0) {
            for (int i = dolPos; i < devil.length(); i++) {
                if (devil.charAt(i) == roll.charAt(rollPos)) {
                    DP[0][dolPos][rollPos] += calc(1, i + 1, rollPos + 1);
                }
            }
        } else {
            for (int i = dolPos; i < angel.length(); i++) {
                if (angel.charAt(i) == roll.charAt(rollPos)) {
                    DP[1][dolPos][rollPos] += calc(0, i + 1, rollPos + 1);
                }
            }
        }

        return DP[dolType][dolPos][rollPos];
    }

}
