/*
 * input: [-2,1,-3,4,-1,2,1,-5,4]
 * sum:    -2,1,-2,4, 3,5,6, 1,5
 * result: -2,1, 1,4, 4,5,6, 6,6
 */
class Solution {
    public int maxSubArray(int[] nums) {
        int sum = nums[0];
        int result = nums[0];

        for(int i = 1; i < nums.length; i++) {
            sum = Math.max(nums[i], sum + nums[i]);
            result = Math.max(result, sum);
        }
        
        return result;
    }
}
