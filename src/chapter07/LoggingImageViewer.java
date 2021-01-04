package chapter07;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.*;

/**
 * @Author: tsy
 * @Date: 2020/12/15
 * @Description p314, 7-2 日志
 */
public class LoggingImageViewer {
    public static void main(String[] args) {
        if (System.getProperty("java.util.logging.config.class") == null
                && System.getProperty("java.util.logging.config.file") == null) {
            try {
                Logger.getLogger("com.corejava").setLevel(Level.ALL);
                int COUNT = 10;
                FileHandler fileHandler = new FileHandler("%h/LoggingImageViewer.log", 0, COUNT);
                Logger.getLogger("com.corejava").addHandler(fileHandler);
            } catch (IOException e) {
                Logger.getLogger("com.corejava").log(Level.SEVERE, "不能创建日志文件处理器");
            }

        }

        EventQueue.invokeLater(() -> {
            WindowHandler windowHandler = new WindowHandler();
            windowHandler.setLevel(Level.ALL);
            // 添加处理器为窗口
            Logger.getLogger("com.corejava").addHandler(windowHandler);


            JFrame frame = new ImageViewerFrame();
            frame.setTitle("ImageViewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            Logger.getLogger("com.corejava").fine("开始展示窗口");
            frame.setVisible(true);
        });
    }
}

class ImageViewerFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 400;

    private static Logger logger = Logger.getLogger("com.corejava");

    public ImageViewerFrame() {
        logger.entering("图片窗口", "<init>");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        JLabel label = new JLabel();
        add(label);

        JFileChooser chooser = new JFileChooser(new File("."));
        chooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".gif") || f.isDirectory();
            }

            @Override
            public String getDescription() {
                return "GIF图像";
            }
        });

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("File");
        menuBar.add(menu);

        JMenuItem openItem = new JMenuItem("Open");
        menu.add(openItem);

        openItem.addActionListener(e -> {
            logger.entering("图片窗口.文件打开监听", "事件", e);

            int result = chooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                String name = chooser.getSelectedFile().getPath();
                logger.log(Level.FINE, "读取文件 {0}", name);
                label.setIcon(new ImageIcon(name));
            } else {
                logger.fine("文件打开对话取消");
            }
        });

        JMenuItem exitItem = new JMenuItem("Exit");
        menu.add(exitItem);

        exitItem.addActionListener(e -> {
            logger.fine("退出");
            System.exit(0);
        });
        logger.exiting("图片窗口", "<init>");
    }
}

class WindowHandler extends StreamHandler {
    private JFrame jFrame;

    public WindowHandler() {
        jFrame = new JFrame();
        JTextArea jTextArea = new JTextArea();
        jTextArea.setEditable(false);
        jFrame.setSize(500, 400);
        jFrame.add(new JScrollPane(jTextArea));
        jFrame.setFocusableWindowState(false);
        jFrame.setVisible(true);
        setOutputStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {

            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                jTextArea.append(new String(b, off, len));
            }
        });
    }

    @Override
    public synchronized void publish(LogRecord record) {
        if (!jFrame.isVisible()) return;
        super.publish(record);
        flush();
    }
}