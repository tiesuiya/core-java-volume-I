package chapter09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @Author: tsy
 * @Date: 2021/1/4
 * @Description p386, 9-2 演示Set
 */
public class SetTest {
    // java chapter09/SetTest < chapter09/alice30.txt
    public static void main(String[] args) throws FileNotFoundException {
        Set<String> words = new HashSet<>();
        long totalTime = 0;

        try (Scanner in = new Scanner(new File("src/chapter09/alice30.txt"))) {
            while (in.hasNext()) {
                String word = in.next();
                long callTime = System.currentTimeMillis();
                words.add(word);
                callTime = System.currentTimeMillis() - callTime;
                totalTime += callTime;
            }
        }

        Iterator<String> iter = words.iterator();
        for (int i = 1; i <= 5 && iter.hasNext(); i++) {
            System.out.println(iter.next());
        }
        System.out.println("...");
        System.out.println(words.size()+" distinct words. " + totalTime + " milliseconds");
    }
}
