class Solution {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int profit = 0;
        for(int i = 0; i < prices.length; i++) {
            if (minPrice > prices[i]) {
                minPrice = prices[i];
            } else if (profit < (prices[i] - minPrice)) {
                profit = prices[i] - minPrice;
            }
        }
        return profit;
    }
}
