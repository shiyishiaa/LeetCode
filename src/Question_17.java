import java.util.ArrayList;
import java.util.List;

public class Question_17 {
    // 2~6: The start letter is 3*(n-1)+'a'.
    // 7: pqrs 8:tuv 9:wxyz
    public static List<String> letterCombinations(String digits) {
        if (digits.length() == 0) return List.of();
        if (digits.length() == 1)
            return translate(digits.charAt(0));
        else
            return multiply(letterCombinations(digits.substring(0, digits.length() / 2)),
                    letterCombinations(digits.substring(digits.length() / 2)));
    }

    public static List<String> translate(char digit) {
        switch (digit) {
            case '7':
                return List.of("p", "q", "r", "s");
            case '8':
                return List.of("t", "u", "v");
            case '9':
                return List.of("w", "x", "y", "z");
            default:
                var startLetter = (char) (3 * (digit - '0' - 2) + 'a');
                return List.of(
                        String.valueOf(startLetter),
                        String.valueOf((char) (startLetter + 1)),
                        String.valueOf((char) (startLetter + 2))
                );
        }
    }

    public static List<String> multiply(List<String> a, List<String> b) {
        var mul = new ArrayList<String>(a.size() * b.size());

        for (var s1 : a)
            for (var s2 : b)
                mul.add(s1 + s2);

        return mul;
    }

    public static void main(String[] args) {
        System.out.println(letterCombinations("56"));
    }
}
