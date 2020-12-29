package chapter03;

/**
 * @Author: tsy
 * @Date: 2020/12/20
 * @Description p87, 3-8 打印不同利率和年份的收益表
 */
public class CompoundInterest {
    private static final double STARTRATE = 10;
    private static final double NMONEY = 10000;
    private static final int NRATES = 9;
    private static final int NYEARS = 10;

    public static void main(String[] args) {
        double[][] balances = new double[NYEARS][NRATES];
        for (int i = 0; i < NYEARS; i++) {
            for (int j = 0; j < NRATES; j++) {
                balances[i][j] = NMONEY;
            }
        }

        double[] interestRate = new double[NRATES];
        for (int i = 0; i < NRATES; i++) {
            interestRate[i] = (STARTRATE + i) / 100;
        }

        for (int i = 1; i < NYEARS; i++) {
            for (int j = 0; j < NRATES; j++) {
                double oldBalance = balances[i - 1][j];
                double interest = oldBalance * interestRate[j];
                balances[i][j] = oldBalance + interest;
            }
        }

        for (int i = 0; i < NRATES; i++) {
            System.out.printf("%9.0f%%", 100 * interestRate[i]);
        }
        System.out.println();
        for (double[] row : balances) {
            for (double b : row) {
                System.out.printf("%10.2f", b);
            }
            System.out.println();
        }
    }


}
