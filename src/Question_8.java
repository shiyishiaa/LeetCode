import java.util.HashMap;
import java.util.Map;

public class Question_8 {
    public static int myAtoi(String s) {
        if (s == null || s.isBlank() || s.isEmpty()) return 0;
        final String string = s.trim();
        final char firstChar = string.charAt(0);
        long ans = 0;
        if (firstChar == '+' || firstChar == '-') {
            for (int i = 1; i < string.length(); i++) {
                char nowChar = string.charAt(i);
                if (!Character.isDigit(nowChar)) break;
                final boolean positive = firstChar == '+';
                ans = ans * 10 + (nowChar - (int) '0');
                if (positive && ans > Integer.MAX_VALUE) return Integer.MAX_VALUE;
                else if (!positive && -ans < Integer.MIN_VALUE) return Integer.MIN_VALUE;
            }
        } else if (Character.isDigit(firstChar)) {
            for (int i = 0; i < string.length(); i++) {
                char nowChar = string.charAt(i);
                if (!Character.isDigit(nowChar)) break;
                ans = ans * 10 + (nowChar - (int) '0');
                if (ans > Integer.MAX_VALUE) return Integer.MAX_VALUE;
            }
        } else return (int) ans;
        return string.charAt(0) == '-' ? (int) -ans : (int) ans;
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("21474836460"));
        Solution_8 i = null;
    }
}

/**
 * 官方解答
 *
 * @see <a href="https://leetcode-cn.com/problems/string-to-integer-atoi/solution/zi-fu-chuan-zhuan-huan-zheng-shu-atoi-by-leetcode-/">有限状态机</a>
 */
class Solution_8 {
    public int myAtoi(String str) {
        Automaton automaton = new Automaton();
        int length = str.length();
        for (int i = 0; i < length; ++i) {
            automaton.get(str.charAt(i));
        }
        return (int) (automaton.sign * automaton.ans);
    }
}

class Automaton {
    private final Map<String, String[]> table = new HashMap<String, String[]>() {{
        put("start", new String[]{"start", "signed", "in_number", "end"});
        put("signed", new String[]{"end", "end", "in_number", "end"});
        put("in_number", new String[]{"end", "end", "in_number", "end"});
        put("end", new String[]{"end", "end", "end", "end"});
    }};
    public int sign = 1;
    public long ans = 0;
    private String state = "start";

    public void get(char c) {
        state = table.get(state)[get_col(c)];
        if ("in_number".equals(state)) {
            ans = ans * 10 + c - '0';
            ans = sign == 1 ? Math.min(ans, Integer.MAX_VALUE) : Math.min(ans, -(long) Integer.MIN_VALUE);
        } else if ("signed".equals(state)) {
            sign = c == '+' ? 1 : -1;
        }
    }

    private int get_col(char c) {
        if (c == ' ') {
            return 0;
        }
        if (c == '+' || c == '-') {
            return 1;
        }
        if (Character.isDigit(c)) {
            return 2;
        }
        return 3;
    }
}