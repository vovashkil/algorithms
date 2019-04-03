package algorithms.datastruct;

public class Simple {
    private final static long[] simples = new long[10000];
    private static int pos = 0;
    private static long counter = 0;
    private static long maxSimple = 1;

    static boolean contains(long val) {
        int left = 0;
        int right = pos - 1;
        while (left <= right) {
            int middle = (left + right)/2;
            if (val < simples[middle]) {
                right = middle - 1;
            } else if (val > simples[middle]) {
                left = middle + 1;
            } else {
                return true;
            }
        }
        return false;
    }

    static boolean isSimple(long x) {
        for (long i = 2; i < x; i++) {
            if (i>maxSimple || contains(i) ) {
                long rest = x % i;
                counter++;
                if (rest == 0) {
                    return false;
                }
            }
        }
        simples[pos++]=x;
        maxSimple = x;
        return true;
    }

    public static void main(String[] args) {
        long simpleCounter = 0;
        for (long z = 1; z < 1_00_000; z++ ) {
            if (isSimple(z)) {
                simpleCounter++;
                //System.out.println(z);
            }
        }
        System.out.printf("simples count:%d, operations:%d", simpleCounter, counter);
    }

}
