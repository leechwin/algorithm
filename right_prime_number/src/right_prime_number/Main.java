package right_prime_number;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int N;

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        solve(2, N);
        solve(3, N);
        solve(5, N);
        solve(7, N);
    }

    // 1 digit: 2, 3, 5, 7
    // 2 digit + @: 1, 3, 7, 9
    public static void solve(int num, int len) {
        if (len == 1) {
            if (isPrime(num)) {
                System.out.println(num);
            }
            return;
        }
        if (isPrime(num)) {
            solve(num * 10 + 1, len - 1);
            solve(num * 10 + 3, len - 1);
            solve(num * 10 + 7, len - 1);
            solve(num * 10 + 9, len - 1);
        }
    }

    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
