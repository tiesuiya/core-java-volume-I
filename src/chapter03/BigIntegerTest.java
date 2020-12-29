package chapter03;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @Author: tsy
 * @Date: 2020/12/17
 * @Description p77, 3-6
 */
public class BigIntegerTest {
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
        BigInteger lotteryOdds = BigInteger.valueOf(1);
        for (int i = 1; i <= k; i++) {
            lotteryOdds = lotteryOdds.multiply(BigInteger.valueOf(n - i + 1)).divide(BigInteger.valueOf(i));
        }
        System.out.println("你的中奖概率是：1/" + lotteryOdds + "祝你好运！");
    }
}
