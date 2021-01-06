package chapter12;


/**
 * @Author: tsy
 * @Date: 2021/1/6
 * @Description p554, 12-1 转账测试(虽然有延时，但是无奈CPU太快了，有时还是会出现总金额异常)
 */
public class ThreadTest {
    private static final int DELAY = 10;
    private static final int STEPS = 10;
    private static final double MAX_AMOUNT = 1000;

    public static void main(String[] args) {
        Bank bank = new Bank(4, 10000);

        Runnable task1 = () -> {
            try {
                for (int i = 0; i < STEPS; i++) {
                    double amount = MAX_AMOUNT * Math.random();
                    bank.transfer(0, 1, amount);
                    Thread.sleep((int) (DELAY * Math.random()));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable task2 = () -> {
            try {
                for (int i = 0; i < STEPS; i++) {
                    double amount = MAX_AMOUNT * Math.random();
                    bank.transfer(2, 3, amount);
                    Thread.sleep((int) (DELAY * Math.random()));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        System.out.println(1111);
        new Thread(task1, "TASK1").start();
        new Thread(task2, "TASK2").start();

    }
}
