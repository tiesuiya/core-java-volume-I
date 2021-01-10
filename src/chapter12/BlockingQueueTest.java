package chapter12;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Author: tsy
 * @Date: 2021/1/7
 * @Description p591, 12-6 使用阻塞队列来控制线程，实现所有一个目录下的所有递归文件
 */
public class BlockingQueueTest {
    private static final Path DUMMY = new File(".").toPath();
    private static final Integer SEARCH_THREAD = 1;
    private static BlockingQueue<Path> queue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入目录：");
        String searchPath = scanner.nextLine();
        System.out.println("请输入要搜寻的关键字：");
        String keyword = scanner.nextLine();

        new Thread(() -> {
            try {
                enumerate(new File(searchPath));
                queue.put(DUMMY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        for (int i = 0; i < SEARCH_THREAD; i++) {
            new Thread(() -> {
                boolean done = false;
                while (!done) {
                    try {
                        // 阻塞zuse
                        Path filePath = queue.take();
                        if (filePath == DUMMY) {
                            queue.put(filePath);
                            done = true;
                        } else {
                            search(filePath, keyword);
                        }
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    private static void search(Path filePath, String keyword) throws IOException {
        Scanner scanner = new Scanner(filePath, StandardCharsets.UTF_8.toString());
        int lineNumber = 0;
        while (scanner.hasNextLine()) {
            lineNumber++;
            String line = scanner.nextLine();
            if (line.contains(keyword)) {
                System.out.printf("%s:%d: %s%n", filePath, lineNumber, line.trim());
            }
        }
    }

    private static void enumerate(File file) throws InterruptedException {
        if (file.isDirectory() && file.listFiles() != null) {
            for (File f : file.listFiles()) {
                enumerate(f);
            }
        } else {
            queue.put(file.toPath());
        }
    }
}
