package chapter06;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;

/**
 * @Author: tsy
 * @Date: 2020/12/29
 * @Description p258, 6-7 内部类演示
 */
public class InnerClassTest {
    public static void main(String[] args) {
        new TalkingClock(1000, false).start();

        JOptionPane.showMessageDialog(null, "exit?");
        System.exit(0);
    }
}

class TalkingClock {
    private int interval;
    private boolean beep;

    public TalkingClock(int interval, boolean beep) {
        this.interval = interval;
        this.beep = beep;
    }

    public void start() {
        Timer timer = new Timer(interval, new TimePrinter());
        timer.start();
    }

    // 只有内部类可以是私有的
    private class TimePrinter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.print("听到这声了？表示过去一秒了！" + Instant.ofEpochMilli(e.getWhen()));
            if (beep) {
                System.out.print(" !!! beep !!! ");
                Toolkit.getDefaultToolkit().beep();
            }
            System.out.println();
        }
    }
}
