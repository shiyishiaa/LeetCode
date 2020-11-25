import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Question_423 {

    /*
     * z-0
     * u-4
     * x-6
     * g-8
     *
     * h-3
     * f-5
     * s-7
     *
     * t-2
     *
     * i-9
     * otherwise-1
     *  */
    private static final List<Character> alphabet = List.of(
            'o', 'n', 'e', 't', 'w',
            'o', 't', 'h', 'r', 'e',
            'e', 'f', 'o', 'u', 'r',
            'f', 'i', 'v', 'e', 's',
            'i', 'x', 's', 'e', 'v',
            'e', 'n', 'e', 'i', 'g',
            'h', 't', 'n', 'i', 'n',
            'e', 'z', 'e', 'r', 'o');

    public static void main(String[] args) {
//        Map<Character, Integer> freq = new HashMap<>();
//        for (char c : alphabet)
//            if (freq.containsKey(c)) freq.replace(c, freq.get(c) + 1);
//            else freq.put(c, 1);
//        System.out.println(freq);

        System.out.println(originalDigits("egith"));
    }

    public static String originalDigits(String s) {
        if (s == null || s.isBlank()) return null;
        int[] count = new int[10];
        Arrays.fill(count, 0);

        char[] cs = s.toCharArray();
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : cs) {
            if (freq.containsKey(c)) freq.replace(c, freq.get(c) + 1);
            else freq.put(c, 1);
        }
        /* Level 1 */
        count[0] = freq.getOrDefault('z', 0);
        count[4] = freq.getOrDefault('u', 0);
        count[6] = freq.getOrDefault('x', 0);
        count[8] = freq.getOrDefault('g', 0);

        freq.replace('e', freq.getOrDefault('e', 0) - count[0]);
        freq.replace('r', freq.getOrDefault('r', 0) - count[0]);
        freq.replace('o', freq.getOrDefault('o', 0) - count[0]);

        freq.replace('f', freq.getOrDefault('f', 0) - count[4]);
        freq.replace('o', freq.getOrDefault('o', 0) - count[4]);
        freq.replace('r', freq.getOrDefault('r', 0) - count[4]);

        freq.replace('s', freq.getOrDefault('s', 0) - count[6]);
        freq.replace('i', freq.getOrDefault('i', 0) - count[6]);

        freq.replace('e', freq.getOrDefault('e', 0) - count[8]);
        freq.replace('i', freq.getOrDefault('i', 0) - count[8]);
        freq.replace('h', freq.getOrDefault('h', 0) - count[8]);
        freq.replace('t', freq.getOrDefault('t', 0) - count[8]);

        freq.remove('z');
        freq.remove('u');
        freq.remove('x');
        freq.remove('g');

        /* Level 2 */

        count[3] = freq.getOrDefault('h', 0);
        count[5] = freq.getOrDefault('f', 0);
        count[7] = freq.getOrDefault('s', 0);

        freq.replace('t', freq.getOrDefault('t', 0) - count[3]);
        freq.replace('r', freq.getOrDefault('r', 0) - count[3]);
        freq.replace('e', freq.getOrDefault('e', 0) - 2 * count[3]);

        freq.replace('i', freq.getOrDefault('i', 0) - count[5]);
        freq.replace('v', freq.getOrDefault('v', 0) - count[5]);
        freq.replace('e', freq.getOrDefault('e', 0) - count[5]);

        freq.replace('e', freq.getOrDefault('e', 0) - 2 * count[7]);
        freq.replace('v', freq.getOrDefault('v', 0) - count[7]);
        freq.replace('n', freq.getOrDefault('n', 0) - count[7]);

        freq.remove('h');
        freq.remove('f');
        freq.remove('s');

        /* Level 3 */
        count[2] = freq.getOrDefault('t', 0);

        freq.replace('w', freq.getOrDefault('w', 0) - count[2]);
        freq.replace('o', freq.getOrDefault('o', 0) - count[2]);

        freq.remove('t');

        /* Rest */

        count[9] = freq.getOrDefault('i', 0);
        count[1] = freq.getOrDefault('o', 0);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count.length; i++) sb.append(String.valueOf((char) ('0' + i)).repeat(count[i]));
        return sb.toString();
    }
}

/**
 * 官方答案相似，但2可以通过'w'唯一识别
 */
class Solution_423{}