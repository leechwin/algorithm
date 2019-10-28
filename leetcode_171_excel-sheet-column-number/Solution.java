class Solution {
    public void moveZeroes(int[] nums) {
        int length = nums.length;
        int i = 0;
        int j = 0; // new array index
        for (i = 0; i < length; i++) {
            if (nums[i] != 0) {
                nums[j] = nums[i];
                j++;
            }
        }

        if (j < length) {
            while (j < length) {
                // assign 0
                nums[j++] = 0;
            }
        }

        for (int k = 0; k < length; k++) {
            System.out.print(nums[k] + " ");
        }
    }
}