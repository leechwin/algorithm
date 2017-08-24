package baekjoon_2602;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {

    public static String D;
    public static String Devil;
    public static String Angel;

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        D = br.readLine();
        Devil = br.readLine();
        Angel = br.readLine();

        System.out.println();

        br.close();
    }

}
