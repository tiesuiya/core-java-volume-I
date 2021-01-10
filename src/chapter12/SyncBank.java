package chapter12;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: tsy
 * @Date: 2021/1/7
 * @Description p574, 12-4 银行(多线程安全)
 */
public class SyncBank {
    private final double[] accounts;
    private Lock bankLock;
    private Condition sufficientFunds; // 条件：足够的资金

    public SyncBank(int n, double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
        bankLock = new ReentrantLock();
        sufficientFunds = bankLock.newCondition();
    }

    public void transfer(int from, int to, double amount) throws InterruptedException {
        bankLock.lock();
        try {
            // 注意这里是while，开始写成if就有问题
            while (accounts[from] < amount) {
                sufficientFunds.await();
            }
            System.out.print(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %03d to %03d", amount, from, to);
            accounts[to] += amount;
            System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
            sufficientFunds.signalAll();
        } finally {
            bankLock.unlock();
        }
    }

    public double getTotalBalance() {
        bankLock.lock(); // 这里加锁的意义是什么？
        try {
            double totalBalance = 0;
            for (double a : accounts) {
                totalBalance += a;
            }
            return totalBalance;
        } finally {
            bankLock.unlock();
        }
    }

    public int size() {
        return accounts.length;
    }
}
