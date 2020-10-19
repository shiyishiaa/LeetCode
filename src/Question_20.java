import java.util.ArrayDeque;
import java.util.Map;

public class Question_20 {
    public static final Map<Character, Character> brackets = Map.of(
            '(', ')',
            '[', ']',
            '{', '}');

    public static boolean isValid(String s) {
        if (s.length() % 2 != 0) return false;

        var queue = new ArrayDeque<Character>();
        var cs = s.toCharArray();
        for (var c : cs)
            if (brackets.containsKey(c)) queue.push(c);
            else if (queue.isEmpty()) return false;
            else if (!brackets.getOrDefault(queue.pop(), '\0').equals(c)) return false;
        return queue.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValid("()"));
    }
}
