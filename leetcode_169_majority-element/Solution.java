class Solution {
    public int majorityElement(int[] nums) {
        // A Linear Time Majority Vote Algorithm
       int cur = 0;
       int count = 0;
       for (int i = 0; i < nums.length; i++) {
           if (count == 0) {
               cur = nums[i];
               count = 1;
           } else if (cur != nums[i]) {
               count--;
           } else {
               count++;
           }
       }
       return cur;
   }
}
