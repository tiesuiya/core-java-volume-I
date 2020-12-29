package chapter03;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: tsy
 * @Date: 2020/12/20
 * @Description p84, 3-7 随机组合结果
 */
public class LotteryDrawing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("一共有几个数？");
        int n = scanner.nextInt();

        System.out.println("你想选几个数？");
        int k = scanner.nextInt();

        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = i + 1;
        }

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            int r = (int) (Math.random() * n);
            result[i] = numbers[r];
            // 把待选数组中最后一位挪到这次被选中的位置，防止重复选择
            numbers[r] = numbers[n - 1];
            n--;
        }

        Arrays.sort(result);
        System.out.println("如果你选择了以下组合，那就赢了！");
        System.out.println(Arrays.toString(result));
    }
}
