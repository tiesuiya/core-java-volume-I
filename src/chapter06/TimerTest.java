package chapter06;


import javax.swing.*;
import java.awt.*;
import java.time.Instant;

/**
 * @Author: tsy
 * @Date: 2020/12/29
 * @Description p161, 6-1 回调演示
 */
public class TimerTest {
    public static void main(String[] args) {
        Timer timer = new Timer(1000, e -> {
            System.out.println("听到这声了？表示过去一秒了！" + Instant.ofEpochMilli(e.getWhen()));
            Toolkit.getDefaultToolkit().beep();
        });
        timer.start();
    }
}
