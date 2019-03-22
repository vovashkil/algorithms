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

        for (int i = 0; i < 11; i++)
            System.out.printf("fib(%d) = %d\n", i, fib(i, 0, 1));

        System.out.printf("fib(%d) = %d\n", 20, fib(20, 0, 1));
        System.out.printf("fib(%d) = %d\n", 30, fib(30, 0, 1));

        for (int i = 40; i < 47; i++)
            System.out.printf("fib(%d) = %d\n", i, fib(i, 0, 1));

        System.out.printf("MAX Integer = %d\n", Integer.MAX_VALUE);
        System.out.printf("fib(%d) = %d\n", 47, fib(47, 0, 1));


    }


}
