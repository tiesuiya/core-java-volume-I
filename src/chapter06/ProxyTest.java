package chapter06;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

/**
 * @Author: tsy
 * @Date: 2020/12/30
 * @Description
 */
public class ProxyTest {
    public static void main(String[] args) {
        Object[] elements = new Object[1000];
        for (int i = 0; i < elements.length; i++) {
            Integer it = i + 1;
            InvocationHandler proxyObject = new TraceHandler(it);
            elements[i] = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Comparable.class}, proxyObject);
        }
        int key = new Random().nextInt(elements.length);
        int index = Arrays.binarySearch(elements, key);
        System.out.println("找到了" + elements[index] + "了！下标：" + index);
    }
}

class TraceHandler implements InvocationHandler {

    private Object target;

    public TraceHandler(Integer it) {
        this.target = it;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.print(target + "." + method.getName() + "(");
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.print(args[i].toString());
                if (i != args.length - 1) {
                    System.out.println(",");
                }
            }
        }
        System.out.println(")");
        return method.invoke(target, args);
    }
}
