package baekjoon_1793_tiling;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {

    public static BigInteger CACHE[] = new BigInteger[251];

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String line = br.readLine();
        while (line != null) {
            StringTokenizer st = new StringTokenizer(line);
            int n = Integer.parseInt(st.nextToken());
            System.out.println(solve(n));
            line = br.readLine();
        }
    }

    private static BigInteger solve(int n) {
        if (n <= 1) {
            return BigInteger.ONE;
        }
        if (CACHE[n] != null) {
            return CACHE[n];
        }

        BigInteger result1 = solve(n - 1);
        BigInteger result2 = solve(n - 2).multiply(new BigInteger("2"));
        CACHE[n] = result1.add(result2);
        return CACHE[n];
    }

}
