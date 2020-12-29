package chapter03;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * @Author: tsy
 * @Date: 2020/12/17
 * @Description p68, 3-3 退休金计算器(升级版)
 */
public class Retirement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("每年基本开销多少就可以了？");
        double retiExp = scanner.nextDouble();

        System.out.println("睡后收入阶段的年化利率%？");
        double retiRate = scanner.nextDouble();

        System.out.println("打算每年存多少？");
        double payment = scanner.nextDouble();

        System.out.println("存钱阶段的年化利率%？");
        double interestRate = scanner.nextDouble();

        double goal = retiExp / (retiRate / 100);
        double balance = 0;
        int years = 0;
        while (balance < goal) {
            balance += payment;
            double interest = balance * (interestRate / 100);
            balance += interest;
            years++;
            System.out.println("---------year:"+years+",money:"+balance);
        }
        System.out.println(
                String.format("恭喜你退休了！从此过上年躺平就有%s元收入的生活，需要存%s年，每年存%s元，直到存满%s元",
                retiExp, years, payment, new BigDecimal(goal).toPlainString()
                ));
    }
}
