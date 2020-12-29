package chapter06;

/**
 * @Author: tsy
 * @Date: 2020/12/29
 * @Description p2569, 6-9 静态内部类演示
 */
public class StaticInnerClassTest {
    public static void main(String[] args) {
        ArrayAlg.Pair pair = ArrayAlg.minmax(new double[]{334,5345,333d,32,-323,456});
        System.out.println("min:" + pair.getFirst());
        System.out.println("max:" + pair.getSecend());
    }
}
class ArrayAlg {
    static class Pair {
        private double first;
        private double secend;

        public Pair(double first, double secend) {
            this.first = first;
            this.secend = secend;
        }

        public double getSecend() {
            return secend;
        }

        public double getFirst() {
            return first;
        }
    }

    public static Pair minmax(double[] array) {
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;
        for (double d : array) {
            if (d < min) min = d;
            if (d > max) max = d;
        }
        return new Pair(min, max);
    }
}
