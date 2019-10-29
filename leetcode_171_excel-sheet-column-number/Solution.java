class Solution {
    public int titleToNumber(String s) {
        char[] ss = s.toCharArray();

        int sum = 0;
        int pt = 0;
        for (int i = ss.length - 1; i >= 0; i--) {
            int charter = ((int) ss[i] - 64);
            sum += Math.pow(26, pt) * charter;
            pt++;
        }
        return sum;
    }
}
