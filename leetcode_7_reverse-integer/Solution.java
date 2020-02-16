class Solution {
    public int reverse(int x) {
        int reverse = 0;
        int i = x;
        while(i != 0) {
            int lastDigit = i % 10; // Get last digit
            i = i / 10;

            // Check overflow
            if (reverse > Integer.MAX_VALUE / 10 || reverse < Integer.MIN_VALUE / 10) {
                return 0;
            }
            reverse = (reverse * 10) + lastDigit;
        }
        return reverse;
    }
}
