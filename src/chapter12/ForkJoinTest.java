package chapter12;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.function.DoublePredicate;

/**
 * @Author: tsy
 * @Date: 2021/1/9
 * @Description p613, 12-9 ForkJoin
 * 结果普通操作耗时还少一些
 */
public class ForkJoinTest {
    public static void main(String[] args) {
        int size = 10000000;
        double[] dbs = new double[size];
        for (int i = 0; i < dbs.length; i++) {
            dbs[i] = Math.random();
        }

        long startTime = System.currentTimeMillis();
        int count = 0;
        for (int i = 0; i < dbs.length; i++) {
            if (dbs[i] > 0.5) {
                count++;
            }
        }
        System.out.println("普通操作结果：" + count);
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
        System.out.println();

        Counter counter = new Counter(dbs, 0, dbs.length, x -> x > 0.5);
        ForkJoinPool pool = new ForkJoinPool();
        startTime = System.currentTimeMillis();
        pool.invoke(counter);
        System.out.println("forkjoin结果：" + counter.join());
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
        System.out.println();
    }
}

class Counter extends RecursiveTask<Integer> {
    public static final int THRESHOLD = 1000; // 阈值
    private double[] values;
    private int from;
    private int to;
    private DoublePredicate filter;

    public Counter(double[] values, int from, int to, DoublePredicate filter) {
        this.values = values;
        this.from = from;
        this.to = to;
        this.filter = filter;
    }

    @Override
    protected Integer compute() {
        if (to - from < THRESHOLD) {
            int count = 0;
            for (int i = from; i < to; i++) {
                if (filter.test(values[i])) {
                    count++;
                }
            }
            return count;
        } else {
            int mid = (from + to) / 2;
            Counter first = new Counter(values, from, mid, filter);
            Counter second = new Counter(values, mid, to, filter);
            invokeAll(first, second);
            return first.join() + second.join();
        }
    }
}

