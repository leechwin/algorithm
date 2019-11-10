class Solution {
    public static boolean isHappy(int n) {
        Set<Integer> set = new HashSet<Integer>();

        while (!set.contains(n)) {
            set.add(n);

            n = sum(n);
            if (n == 1) {
                return true;
            }
        }
        return false;
    }

    public static int sum(int n) {
        int sum = 0;
        while (n > 0) {
            sum += (n % 10) * (n % 10);
            n = n / 10;
        }
        return sum;

        /*
        char[] nums = Integer.toString(n).toCharArray();
        for (int i = nums.length - 1; i >= 0; i--) {
            int j = (int) (nums[i] - '1' + 1);
            sum += (j * j);
        }
        return sum;
        */
    }
}

// Ref: https://en.wikipedia.org/wiki/Happy_number
