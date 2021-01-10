package chapter12;

import java.util.Arrays;

/**
 * @Author: tsy
 * @Date: 2021/1/6
 * @Description p565, 12-2 银行(多线程不安全)
 */
public class Bank {
    private final double[] accounts;

    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
    }

    public void transfer(int from, int to, double amount) {
        if (accounts[from] < amount) {
            return;
        }
        System.out.print(Thread.currentThread());
        accounts[from] -= amount;
        System.out.printf(" %10.2f from %03d to %03d", amount, from, to);
        accounts[to] += amount;
        System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
    }

    public double getTotalBalance() {
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
