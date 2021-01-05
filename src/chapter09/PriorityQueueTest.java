package chapter09;

import java.time.LocalDate;
import java.util.PriorityQueue;

/**
 * @Author: tsy
 * @Date: 2021/1/5
 * @Description p393, 9-5 演示优先队列
 */
public class PriorityQueueTest {
    public static void main(String[] args) {
        PriorityQueue<LocalDate> localDates = new PriorityQueue<>();
        localDates.add(LocalDate.of(1994, 3, 30));
        localDates.add(LocalDate.of(1898, 12, 11));
        localDates.add(LocalDate.of(1960, 4, 13));
        localDates.add(LocalDate.of(130, 2, 7));

        for (LocalDate localDate : localDates) {
            System.out.println(localDate);
        }

        while (!localDates.isEmpty()) {
            System.out.println(localDates.remove());
        }

    }
}
