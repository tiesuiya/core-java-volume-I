package chapter09;

import java.util.*;

/**
 * @Author: tsy
 * @Date: 2021/1/5
 * @Description p413, 9-7 随机打乱顺序
 */
public class ShuffleTest {
    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 10000000; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }

        long timeBegingA = System.currentTimeMillis();
        Collections.shuffle(arrayList);
        System.out.println("A:" + (System.currentTimeMillis() - timeBegingA));

        long timeBegingB = System.currentTimeMillis();
        Collections.shuffle(linkedList);
        System.out.println("B:" + (System.currentTimeMillis() - timeBegingB));

    }

}
