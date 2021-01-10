package chapter12;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: tsy
 * @Date: 2021/1/7
 * @Description p577, 12-5 银行(多线程安全使用synchronized实现)
 */
public class SyncBank2 {
    private final double[] accounts;

    public SyncBank2(int n, double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
    }

    public synchronized void transfer(int from, int to, double amount) throws InterruptedException {
        // 注意这里是while，开始写成if就有问题
        while (accounts[from] < amount) {
            System.out.printf("[balance:%10.2f used:%10.2f]%n", accounts[from], amount);
            wait();
        }
        System.out.print(Thread.currentThread());
        accounts[from] -= amount;
        System.out.printf(" %10.2f from %03d to %03d", amount, from, to);
        accounts[to] += amount;
        System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
        notifyAll();
    }

    public synchronized double getTotalBalance() {
        double totalBalance = 0;
        for (double a : accounts) {
            totalBalance += a;
        }
        return totalBalance;
    }

    public int size() {
        return accounts.length;
    }
}
