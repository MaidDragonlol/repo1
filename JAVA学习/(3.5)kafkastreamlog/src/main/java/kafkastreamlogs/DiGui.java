package kafkastreamlogs;

public class DiGui {

    public static void main(String[] args) {
        int a = Factorial(3);
        System.out.println(a);
    }
    private static int Factorial(int n) {
        if (n == 0)  return 1;

        return n * Factorial(n - 1);
    }
}
