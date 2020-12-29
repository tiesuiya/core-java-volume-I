package chapter03;

import java.util.Scanner;

/**
 * @Author: tsy
 * @Date: 2020/12/17
 * @Description p56, 3-2
 */
public class InputTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("What's your name?");
        String name = scanner.nextLine();

        System.out.println("How lod are you?");
        int age = scanner.nextInt();

        System.out.println("Hello," + name + ". Next year, you'll be " + (age + 1));
    }
}
