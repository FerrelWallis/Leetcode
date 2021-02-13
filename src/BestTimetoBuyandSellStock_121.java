public class BestTimetoBuyandSellStock_121 {
    //1
    //Say you have an array for which the ith element is the price of a given stock on day i.

    //If you were only permitted to complete at most one transaction (i.e., buy one and sell one
    //share of the stock), design an algorithm to find the maximum profit.
    //Note that you cannot sell a stock before you buy one.

    //Example 1:
    //Input: [7,1,5,3,6,4]
    //Output: 5
    //Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
    //             Not 7-1 = 6, as selling price needs to be larger than buying price.

    //Example 2:
    //Input: [7,6,4,3,1]
    //Output: 0
    //Explanation: In this case, no transaction is done, i.e. max profit = 0.


    //一个dp框架团灭6题best time to sell and buy stock
    //很多读者抱怨股票系列问题奇技淫巧太多，如果面试真的遇到这类问题，基本不会想到那些巧妙的办法，怎么办？
    //所以本文拒绝奇技淫巧，而是稳扎稳打，只用一种通用方法解决所用问题，以不变应万变。
    //这篇文章用状态机的技巧来解决，可以全部提交通过。不要觉得这个名词高大上，文学词汇而已，实际上就是 DP table
    //为什么别人能写出这么诡异却又高效的解法呢？因为这类问题是有框架的，但是人家不会告诉你的，因为一旦告诉你，
    //你五分钟就学会了，该算法题就不再神秘，变得不堪一击了。
    //本文就来告诉你这个框架，然后带着你一道一道秒杀。
    //这 6 道股票买卖问题是有共性的，我们通过对第四题（限制最大交易次数为 k）的分析一道一道解决。
    //因为第四题是一个最泛化的形式，其他的问题都是这个形式的简化。
    //第一题是只进行一次交易，相当于 k = 1；第二题是不限交易次数，相当于 k = +infinity（正无穷）；
    //第三题是只进行 2 次交易，相当于 k = 2；剩下两道也是不限次数，但是加了交易「冷冻期」和「手续费」的额外条件，
    //其实就是第二题的变种，都很容易处理。

    //一、穷举框架
    //首先，还是一样的思路：如何穷举？
    //这里，我们不用递归思想进行穷举，而是利用「状态」进行穷举。我们具体到每一天，看看总共有几种可能的「状态」，
    //再找出每个「状态」对应的「选择」。我们要穷举所有「状态」，穷举的目的是根据对应的「选择」更新状态。
    //听起来抽象，你只要记住「状态」和「选择」两个词就行，下面实操一下就很容易明白了。
    //for 状态1 in 状态1的所有取值：
    //    for 状态2 in 状态2的所有取值：
    //        for ...
    //            dp[状态1][状态2][...] = 择优(选择1，选择2...)

    //比如说这个问题，每天都有三种「选择」：买入、卖出、无操作，我们用 buy, sell, rest 表示这三种选择。
    //但问题是，并不是每天都可以任意选择这三种选择的，因为 sell 必须在 buy 之后，buy 必须在 sell 之后。
    //那么 rest 操作还应该分两种状态，一种是 buy 之后的 rest（持有了股票），一种是 sell 之后的 rest（没有持有股票）。
    //而且别忘了，我们还有交易次数 k 的限制，就是说你 buy 还只能在 k > 0 的前提下操作。
    //这个问题的「状态」有三个，第一个是天数，第二个是允许交易的最大次数，第三个是当前的持有状态
    //（即之前说的 rest 的状态，我们不妨用 1 表示持有，0 表示没有持有）。
    //然后我们用一个三维数组就可以装下这几种状态的全部组合：

    //dp[i][k][0 or 1]      0 <= i <= n-1, 1 <= k <= K      n 为天数，大 K 为最多交易数
    //此问题共 n × K × 2 种状态，全部穷举就能搞定。
    //for 0 <= i < n:
    //    for 1 <= k <= K:
    //        for s in {0, 1}:
    //            dp[i][k][s] = max(buy, sell, rest)

    //我们想求的最终答案是 dp[n - 1][K][0]，即最后一天，最多允许 K 次交易，最多获得多少利润。
    //读者可能问为什么不是 dp[n - 1][K][1]？因为 [1] 代表手上还持有股票，[0] 表示手上的股票已经卖出去了，
    //很显然后者得到的利润一定大于前者。记住如何解释「状态」，一旦你觉得哪里不好理解，把它翻译成自然语言就容易理解了。

    //二、状态转移框架
    //现在，我们完成了「状态」的穷举，我们开始思考每种「状态」有哪些「选择」，应该如何更新「状态」。
    //只看「持有状态」，可以画个状态转移图。据图，我们来写一下状态转移方程：

    //dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
    //              max(   选择 rest  ,           选择 sell      )
    //解释：今天我没有持有股票，有两种可能：
    //要么是我昨天就没有持有，然后今天选择 rest，所以我今天还是没有持有；
    //要么是我昨天持有股票，但是今天我 sell 了，所以我今天没有持有股票了。

    //dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
    //              max(   选择 rest  ,           选择 buy         )
    //解释：今天我持有着股票，有两种可能：
    //要么我昨天就持有着股票，然后今天选择 rest，所以我今天还持有着股票；
    //要么我昨天本没有持有，但今天我选择 buy，所以今天我就持有股票了。

    //这个解释应该很清楚了，如果 buy，就要从利润中减去 prices[i]，如果 sell，就要给利润增加 prices[i]。
    //今天的最大利润就是这两种可能选择中较大的那个。而且注意 k 的限制，我们在选择 buy 或者 sell的时候，把 k 减小了 1
    //现在，我们已经完成了动态规划中最困难的一步：状态转移方程。不过还差最后一点点，就是定义 base case，即最简单的情况。
    //dp[-1][k][0] = 0  解释：因为 i 是从 0 开始的，所以 i = -1 意味着还没有开始，这时候的利润当然是 0 。
    //dp[-1][k][1] = -infinity  解释：还没开始的时候，是不可能持有股票的，用负无穷表示这种不可能。
    //dp[i][0][0] = 0  解释：因为 k 是从 1 开始的，所以 k = 0 意味着根本不允许交易，这时候利润当然是 0 。
    //dp[i][0][1] = -infinity  解释：不允许交易的情况下，是不可能持有股票的，用负无穷表示这种不可能。
    //把上面的状态转移方程总结一下 base case：
    //dp[-1][k][0] = dp[i][0][0] = 0
    //dp[-1][k][1] = dp[i][0][1] = -infinity

    //状态转移方程：
    //dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
    //dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])

    //dp[i][k][0] i天数 k是最大交易数 0是非持有状态 1是持有状态。
    //Best time 1, 最大交易数为1，所以可以直接去掉。所以只需要 dp[i][0/1]
    //动态转移方程： dp[i][0] = max(dp[i-1][1] + price[i], dp[i-1][0])
    //             dp[i][1] = max(- price[i], dp[i-1][1])
    //             这里因为只有一次交易，所以每次买的时候都是 0 - price[1],这里表示找到最小值买入
    //时间复杂度O（n） 空间复杂度O（n）
    public int maxProfit11(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[len][2];
        dp[0][0] = 0; //dp[i][0] = max(dp[-1][0], dp[-1][1] + prices[i]) = max(0, -infinity + prices[i]) = 0
        dp[0][1] = -prices[0]; //同理代入公式 dp[-1][1] = -Inf  -1代表还没开始所以是不可能持有，负无穷
        for(int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][1] + prices[i], dp[i - 1][0]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        return dp[len - 1][0];
    }

    //空间优化 上面动态方程可以看出，当前dp只与上一个dp有关 所以不需要数组，只需要两个变量 空间复杂度O（1）
   public int maxProfit12(int[] prices) {
            int len = prices.length;
            int nothold = 0, hold = -prices[0];
            for(int i = 1; i < len; i++) {
            nothold = Math.max(hold + prices[i], nothold);
            hold = Math.max(hold, -prices[i]);
        }
        return nothold;
    }





    //一次遍历,因为始终是从左往右的时间顺序比较,所以min始终选取当前遍历到的最小值,
    //然后依次跟遍历到的比当前最低点大的值相减,保留最大的值,即为高低差最大的值
    public int maxProfit13(int[] prices) {
        if(prices==null || prices.length==0) return 0;
        int min=Integer.MAX_VALUE;
        int max_profit=0;
        for(int i=0;i<prices.length;i++) {
            if(prices[i] < min) min=prices[i];
            else if(prices[i] > min) max_profit = (prices[i]-min > max_profit)? prices[i]-min:max_profit;
        }
        return max_profit;
    }
}
