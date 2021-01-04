package chapter07;

import java.util.Random;

/**
 * @Author: tsy
 * @Date: 2021/1/1
 * @Description 7-1, 295
 */
public class StackTraceTest {
    public static void main(String[] args) {
        int n = new Random().nextInt(100);
        factorial(n);
    }

    public static int factorial(int n) {
        System.out.println("factorial(" + n + ")");
//        、 s = StackWalker. 需要jdk9+
        int r;
        if (n <= 1) r = 1;
        else r = n * factorial(n - 1);
        return r;
    }

}
