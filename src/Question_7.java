public class Question_7 {
    public static int reverse(int x) {
        if (x == Integer.MIN_VALUE) return 0;
        boolean positive = x >= 0;
        String value = new StringBuilder(String.valueOf(Math.abs(x))).reverse().toString();
        Long l = Long.parseLong(value);
        if (l.compareTo((long) Integer.MAX_VALUE) > 0) return 0;
        else return positive ? l.intValue() : -l.intValue();
    }

    public static void main(String[] args) {
        System.out.println(reverse(-2147483648));
    }
}

class Solution_7 {
    public static int reverse(int x) {
        int ans = 0;
        while (x != 0) {
            if ((ans * 10) / 10 != ans) {
                ans = 0;
                break;
            }
            ans = ans * 10 + x % 10;
            x = x / 10;
        }
        return ans;
    }
}