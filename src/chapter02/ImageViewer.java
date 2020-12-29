package chapter02;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * @Author: tsy
 * @Date: 2020/12/15
 * @Description p19, 2-2 一个简单的图像文件查看器，可以加载和显示一个图像
 */
public class ImageViewer {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new ImageViewerFrame();
            frame.setTitle("ImageViewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

class ImageViewerFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 400;

    public ImageViewerFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        JLabel label = new JLabel();
        add(label);

        JFileChooser chooser = new JFileChooser(new File("."));

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("File");
        menuBar.add(menu);

        JMenuItem openItem = new JMenuItem("Open");
        menu.add(openItem);

        openItem.addActionListener(e -> {
            int result = chooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                ImageIcon imageIcon = new ImageIcon(chooser.getSelectedFile().getPath());
                label.setIcon(imageIcon);
            }
        });

        JMenuItem exitItem = new JMenuItem("Exit");
        menu.add(exitItem);

        exitItem.addActionListener(e -> System.exit(0));
    }
}
