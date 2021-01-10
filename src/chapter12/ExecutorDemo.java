package chapter12;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: tsy
 * @Date: 2021/1/9
 * @Description p610, 12-8 词频统计
 * 用invokeAll统计某个单词在某个目录中出现的次数 用invokeAny查找一个包含某单词的文件
 */
public class ExecutorDemo {

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("请输入目录：");
            String start = in.nextLine();
            System.out.println("请输入要搜寻的关键字：");
            String word = in.nextLine();

            Set<Path> files = descendants(new File(start).toPath());
            List<Callable<Long>> tasks = new ArrayList<>();
            for (Path file : files) {
                tasks.add(() -> occurrences(word, file));
            }
            ExecutorService executor = Executors.newCachedThreadPool();

            Instant startTime = Instant.now();
            List<Future<Long>> results = executor.invokeAll(tasks);
            long total = 0;
            for (Future<Long> result : results) {
                total += result.get();
            }
            Instant endTime = Instant.now();
            System.out.println(word + "出现的次数:" + total);
            System.out.println("耗时：" + (Duration.between(startTime, endTime).toMillis()) + "ms");

            List<Callable<Path>> searchTasks = new ArrayList<>();
            for (Path file : files) {
                searchTasks.add(searchForTask(word, file));
            }
            // 找到一个就会返回
            Path found = executor.invokeAny(searchTasks);
            System.out.println(word + "出现在：" + found);

            if (executor instanceof ThreadPoolExecutor) {
                System.out.println("最大池大小：" + ((ThreadPoolExecutor) executor).getLargestPoolSize());
            }

            executor.shutdown();
        }
    }


    private static long occurrences(String word, Path path) {
        try (Scanner in = new Scanner(path)) {
            int count = 0;
            while (in.hasNext()) {
                if (in.next().equals(word)) {
                    count++;
                }
            }
            return count;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static Set<Path> descendants(Path rootDir) throws IOException {
        try (Stream<Path> entries = Files.walk(rootDir)) {
            return entries.filter(Files::isRegularFile).collect(Collectors.toSet());
        }
    }

    public static Callable<Path> searchForTask(String word, Path path) {
        return () -> {
            try (Scanner in = new Scanner(path)) {
                while (in.hasNext()) {
                    if (in.next().equals(word)) {
                        return path;
                    }
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Search in " + path + " canceled.");
                        return null;
                    }
                }
            }
            throw new NoSuchElementException();
        };
    }
}
