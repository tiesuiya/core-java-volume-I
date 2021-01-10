package chapter12;

import com.google.common.io.ByteStreams;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: tsy
 * @Date: 2021/1/10
 * @Description p619, 12-10 运用可完成Future抓取网页上图片链接
 * 与Future相比最大的特点在于使用异步回调的方式返回信息，而不用通过`阻塞循环`的方式访问结果数据
 */
public class CompletableFutureDemo {
    private static final Pattern IMG_PATTERN = Pattern.compile("[<]\\s*[iI][mM][gG]\\s*[^>]*[sS][rR][cC]\\s*[=]\\s*['\"]([^'\"]*)['\"][^>]*[>]");
    private ExecutorService executor = Executors.newCachedThreadPool();
    private URL urlToProcess;


    public static void main(String[] args) throws MalformedURLException {
        new CompletableFutureDemo().run(new URL("http://changba.com/"));
    }

    private void run(URL url) {
        urlToProcess = url;
        CompletableFuture.completedFuture(url)
                .thenComposeAsync(this::readPage, executor)
                .thenApply(this::getImageURLs)
                .thenCompose(this::getImages)
                .thenAccept(this::saveImages);
    }


    private CompletionStage<String> readPage(URL url) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                String contents = null;
                contents = new String(ByteStreams.toByteArray(url.openStream()), StandardCharsets.UTF_8);
                System.out.println("Read page from " + url);
                return contents;
            } catch (IOException e) {
                e.printStackTrace();
                throw new UncheckedIOException(e);
            }
        }, executor);
    }

    public List<URL> getImageURLs(String webpage) {
        try {
            ArrayList<URL> result = new ArrayList<>();
            Matcher matcher = IMG_PATTERN.matcher(webpage);
            while (matcher.find()) {
                URL url = new URL(urlToProcess, matcher.group(1));
                result.add(url);
            }
            System.out.println("Found URLs: " + result);
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new UncheckedIOException(e);
        }
    }

    private CompletionStage<List<BufferedImage>> getImages(List<URL> urls) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                ArrayList<BufferedImage> result = new ArrayList<>();
                for (URL url : urls) {
                    result.add(ImageIO.read(url));
                    System.out.println("Loaded " + url);
                }
                return result;
            } catch (IOException e) {
                e.printStackTrace();
                throw new UncheckedIOException(e);
            }
        }, executor);
    }

    private void saveImages(List<BufferedImage> images) {
        System.out.println("Saving " + images.size() + " images");
        try {
            for (int i = 0; i < images.size(); i++) {
                String filename = "/tmp/image" + (i + 1) + ".png";
                ImageIO.write(images.get(i), "PNG", new File(filename));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new UncheckedIOException(e);
        }
        executor.shutdown();
    }

}
