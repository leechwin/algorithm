package jungol_1362_huffman_code;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static String bitString;
    public static int N;
    public static HashMap<String, Character> hashMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        bitString = br.readLine();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Character startValue = 'A';
        for (int i = 0; i < N; i++) {
            hashMap.put(st.nextToken(), startValue);
            startValue++;
        }

        huffman();
    }

    private static void huffman() {
        String str = "";
        for (int i = 0; i < bitString.length(); i++) {
            str += bitString.charAt(i);
            if (hashMap.containsKey(str)) {
                System.out.print(hashMap.get(str));
                str = "";
            }
        }
    }

}
