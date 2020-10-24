import java.util.Arrays;

public class KMP {
    public static int[] getNext(String s) {
        int j = 0, k = -1;
        int[] next = new int[s.length()];
        next[0] = -1;
        while (j < s.length() - 1)
            if (k == -1 || s.charAt(j) == s.charAt(k)) {
                j++;
                k++;
                next[j] = k;
            } else k = next[k];//此语句是这段代码最反人类的地方，如果你一下子就能看懂，那么请允许我称呼你一声大神！
        return next;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(getNext("abcabc")));
    }
}