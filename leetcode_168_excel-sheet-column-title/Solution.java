class Solution {
    public String convertToTitle(int n) {
         if (n <= 0) {
            return "";
        }

        StringBuffer sb = new StringBuffer();
        while (n > 0) {
            n--;
            int mod = n % 26;
            char title = (char) (mod + 'A');
            n = n / 26;
            sb.append(title);
        }

        return sb.reverse().toString();
    }
}

// Ref: http://buttercola.blogspot.com/2015/08/leetcode-excel-sheet-column-title.html
