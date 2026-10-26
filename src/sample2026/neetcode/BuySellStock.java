package sample2026.neetcode;

public class BuySellStock {

    public static void main(String[] args) {
        BuySellStock stock = new BuySellStock();
        int[] prices = new int[] {7,1,5,3,6,4};
        System.out.println("Max Profit :: " + stock.maxProfit(prices));
    }

    public int maxProfit(int[] prices) {
        int buy = 0;
        int sell = 0;
        int maxProfit = 0;
        while (sell < prices.length) {
            int profit = prices[sell] - prices[buy];
            if (profit > 0) {
                maxProfit = Math.max(profit, maxProfit);
            } else {
                buy = sell;
            }
            sell++;
        }
        return maxProfit;
    }

}
