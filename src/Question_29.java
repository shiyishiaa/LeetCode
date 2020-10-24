/**
 * 不想写边缘条件了
 */
public class Question_29 {
    public static int divide(int dividend, int divisor) {
        if (dividend == 0) return 0;
        int sign;
        if (Integer.signum(dividend) * Integer.signum(divisor) == 1) sign = 1;
        else sign = -1;

        return sign * div(Math.abs(dividend), Math.abs(divisor));
    }

    private static int div(int dividend, int divisor) {
        if (dividend < divisor) return 0;
        if (dividend == divisor) return 1;
        int times = 0;
        int shift = divisor;
        while (shift < dividend) {
            shift <<= 1;
            times++;
        }
        times--;
        shift >>= 1;

        return (1 << times) + div(dividend - shift, divisor);
    }

    public static void main(String[] args) {
        System.out.println(divide(-2147483648
                , -1));
    }
}
