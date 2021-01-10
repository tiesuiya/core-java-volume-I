package chapter12;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: tsy
 * @Date: 2021/1/8
 * @Description p598, 12-7 词频统计
 */
public class CHMDemo {
    private static Map<String, Long> map = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        // 获取CPU线程数?
        int processors = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(processors);
        Path pathRoot = new File(".").toPath();
        for (Path p : descendants(pathRoot)) {
            if (p.getFileName().toString().endsWith(".txt")) {
                executorService.execute(() -> process(p));
            }
        }
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.MINUTES);
        List<String> values = new ArrayList<>(map.keySet());
        values.sort((a, b) -> (int) (map.get(b) - map.get(a)));
        for (String val : values) {
            System.out.println(String.format("%20s:%05d", val, map.get(val)));
        }
    }

    private static void process(Path p) {
        try (Scanner scanner = new Scanner(p)) {
            while (scanner.hasNext()) {
                String word = scanner.next();
                map.merge(word, 1L, Long::sum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Set<Path> descendants(Path pathRoot) throws IOException {
        // 通过递归遍历以给定起始文件为根的文件树来返回由Path延迟填充的流
        try (Stream<Path> entries = Files.walk(pathRoot)) {
            return entries.collect(Collectors.toSet());
        }
    }
}
