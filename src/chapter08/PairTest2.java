package chapter08;

/**
 * @Author: tsy
 * @Date: 2020/12/29
 * @Description p332, 8-2 泛型类型变量的|限定
 */
public class PairTest2 {
    public static void main(String[] args) {
        ArrayAlg2.Pair<String> pair = ArrayAlg2.minmax(new String[]{"Bob","Alice","Jule"});
        System.out.println("min:" + pair.getFirst());
        System.out.println("max:" + pair.getSecond());
    }
}

class ArrayAlg2 {
    static class Pair<T> {
        private T first;
        private T second;

        public Pair(T first, T second) {
            this.first = first;
            this.second = second;
        }

        public T getSecond() {
            return second;
        }

        public T getFirst() {
            return first;
        }
    }

    public static <T extends Comparable<? super T>> Pair<T> minmax(T[] array) {
        if (array == null || array.length == 0) return null;
        T min = array[0];
        T max = array[0];
        for (T s :array) {
            if (min.compareTo(s) > 0) min = s;
            if (max.compareTo(s) < 0) max = s;
        }
        return new Pair<>(min, max);
    }
}
