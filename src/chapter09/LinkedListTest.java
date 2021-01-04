package chapter09;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * @Author: tsy
 * @Date: 2021/1/4
 * @Description p382, 9-1 演示LinkedList
 */
public class LinkedListTest {
    public static void main(String[] args) {
        List<String> a = new LinkedList<>();
        a.add("a1");
        a.add("a2");
        a.add("a3");
        a.add("c");
        List<String> b = new LinkedList<>();
        b.add("b1");
        b.add("b2");
        b.add("b3");
        b.add("c");

        // merge b to a
        ListIterator<String> aIter = a.listIterator();
        Iterator<String> bIter = b.iterator();

        while (bIter.hasNext()) {
            if (aIter.hasNext()) aIter.next();
            aIter.add(bIter.next());
        }
        System.out.println(a);

        // remove every second word from b
        bIter = b.iterator();
        while (bIter.hasNext()) {
            bIter.next();
            if (bIter.hasNext()) {
                bIter.next();
                bIter.remove();
            }
        }
        System.out.println(b);

        // remove all words in b from a
        a.removeAll(b);
        System.out.println(a);
    }
}
