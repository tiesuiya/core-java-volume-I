package chapter02;

/**
 * @Author: tsy
 * @Date: 2020/12/15
 * @Description p17,2-1 向终端输出一条消息
 */
public class Welcome {
    public static void main(String[] args) {
        String greeting = "chapter02.Welcome to Java!";
        System.out.println(greeting);
        for (int i = 0; i < greeting.length(); i++) {
            System.out.print("=");
        }
        System.out.println();
    }
}
