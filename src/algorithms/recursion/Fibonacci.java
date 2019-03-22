package algorithms.recursion;

public class Fibonacci {

    static long fib(int n, int a, int b) {

        if (n == 0)
            return a;
        if (n == 1)
            return b;

        return fib(n - 1, b, a + b);

    }

    public static void main(String[] args) {

        System.out.printf("fib(%d) = %d\n", 9, fib(9, 0, 1));
        System.out.printf("fib(%d) = %d\n", 10, fib(10, 0, 1));
        System.out.printf("fib(%d) = %d\n", 20, fib(20, 0, 1));
        System.out.printf("fib(%d) = %d\n", 30, fib(30, 0, 1));

    }


}
