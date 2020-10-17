import java.util.HashMap;

public class Question_13 {
    public static int romanToInt(String s) {
        var number = new HashMap<String, Integer>();

        number.put("I", 1);
        number.put("X", 10);
        number.put("C", 100);
        number.put("M", 1000);

        number.put("V", 5);
        number.put("L", 50);
        number.put("D", 500);

        number.put("IV", 4);
        number.put("XL", 40);
        number.put("CD", 400);

        number.put("IX", 9);
        number.put("XC", 90);
        number.put("CM", 900);

        var i = 0;
        var value = 0;
        while (i != s.length()) {
            if (i + 1 < s.length() && number.containsKey(s.substring(i, i + 2))) {
                value += number.get(s.substring(i, i + 2));
                i += 2;
            } else {
                value += number.get(s.substring(i, i + 1));
                i += 1;
            }
        }
        return value;
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("XIV"));
    }
}