package chapter08;

/**
 * @Author: tsy
 * @Date: 2020/12/29
 * @Description p329, 8-1 泛型
 */
public class PairTest {
    public static void main(String[] args) {
        ArrayAlg.Pair<String> pair = ArrayAlg.minmax(new String[]{"Bob","Alice","Jule"});
        System.out.println("min:" + pair.getFirst());
        System.out.println("max:" + pair.getSecond());
    }
}
class ArrayAlg {
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

    public static Pair<String> minmax(String[] array) {
        if (array == null || array.length == 0) return null;
        String min = array[0];
        String max = array[0];
        for (String s :array) {
            if (min.compareTo(s) > 0) min = s;
            if (max.compareTo(s) < 0) max = s;
        }
        return new Pair<>(min, max);
    }
}
