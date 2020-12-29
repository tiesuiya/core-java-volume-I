package chapter03;

import java.util.Scanner;

/**
 * @Author: tsy
 * @Date: 2020/12/17
 * @Description p71, 3-5 组合概率
 */
public class LotteryOdds {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("一共有几个数？");
        int n = scanner.nextInt();

        System.out.println("你想选几个数？");
        int k = scanner.nextInt();

        /*
         * 双色球中奖概率公式
         * n*(n-1)*(n-2)*...*(n-k+1)
         * -------------------------
         *       1*2*3*4*...*k
         */
        int lotteryOdds = 1;
        for (int i = 1; i <= k; i++) {
            lotteryOdds = lotteryOdds * (n - i + 1) / i;
        }
        System.out.println("你的中奖概率是：1/" + lotteryOdds + "祝你好运！");
    }
}
