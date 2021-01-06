package chapter12;


/**
 * @Author: tsy
 * @Date: 2021/1/6
 * @Description p566, 12-3 随机选取账户将随机金额转入另一个账户中
 */
public class UnsynchBankTest {
    private static final int NACCOUNTS = 100;
    private static final double INITIAL_BALANCE = 1000;
    private static final int DELAY = 10;
    private static final double MAX_AMOUNT = 1000;

    public static void main(String[] args) {
        Bank bank = new Bank(NACCOUNTS, INITIAL_BALANCE);
        for (int i = 0; i < NACCOUNTS; i++) {
            int fromAccount = i;
            new Thread(() -> {
                try {
                    while (true) {
                        int toAccount = (int) (bank.size() * Math.random());
                        double amount = MAX_AMOUNT * Math.random();
                        bank.transfer(fromAccount, toAccount, amount);
                        Thread.sleep((int) (DELAY * Math.random()));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.format("TASK%03d", (i + 1))).start();
        }
    }
}
