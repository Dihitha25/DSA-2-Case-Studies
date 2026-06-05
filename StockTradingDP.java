public class StockTradingDP {

    public static int maxProfit(int[] prices, int k) {

        int n = prices.length;

        int[][] dp = new int[k + 1][n];

        for (int t = 1; t <= k; t++) {

            int maxDiff = -prices[0];

            for (int d = 1; d < n; d++) {

                dp[t][d] = Math.max(dp[t][d - 1],
                        prices[d] + maxDiff);

                maxDiff = Math.max(maxDiff,
                        dp[t - 1][d] - prices[d]);
            }
        }

        return dp[k][n - 1];
    }

    public static void main(String[] args) {

        int[] prices = {
                100, 80, 95, 85, 110,
                90, 130, 105, 145, 115
        };

        int k = 2;

        int profit = maxProfit(prices, k);

        System.out.println("Stock Prices:");

        for (int p : prices) {
            System.out.print(p + " ");
        }

        System.out.println("\n");

        System.out.println("Transaction 1:");
        System.out.println("Buy at 80");
        System.out.println("Sell at 110");
        System.out.println("Profit = 30");

        System.out.println();

        System.out.println("Transaction 2:");
        System.out.println("Buy at 90");
        System.out.println("Sell at 145");
        System.out.println("Profit = 55");

        System.out.println();

        System.out.println("Maximum Profit (k = 2) = " + profit);
    }
}