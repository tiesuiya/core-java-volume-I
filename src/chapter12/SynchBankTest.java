package chapter12;


/**
 * @Author: tsy
 * @Date: 2021/1/6
 * @Description p574, ? SynchBank测试类
 */
public class SynchBankTest {
    private static final int NACCOUNTS = 100;
    private static final double INITIAL_BALANCE = 1000;
    private static final int DELAY = 10;
    private static final double MAX_AMOUNT = 1000;

    public static void main(String[] args) {
        SyncBank bank = new SyncBank(NACCOUNTS, INITIAL_BALANCE);
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
