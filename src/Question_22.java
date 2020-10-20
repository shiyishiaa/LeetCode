import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Question_22 {
    public static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        while (n > 0) {
            ans = insertParenthesis(ans);
            n--;
        }
        return ans;
    }

    public static List<String> insertParenthesis(List<String> list) {
        if (list.isEmpty()) return List.of("()");

        Set<String> set = new HashSet<>();
        for (var s : list) for (var i = 0; i < s.length(); i++) set.add(s.substring(0, i) + "()" + s.substring(i));
        return new ArrayList<>(set);
    }

    public static void main(String[] args) {
        System.out.println(generateParenthesis(9));
    }
}
