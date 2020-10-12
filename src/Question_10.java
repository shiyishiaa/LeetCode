import java.util.Arrays;

public class Question_10 {
    public static boolean isMatch(String s, String p) {
        if (p == null) return s == null;
        if (s == null) return false;
        if (p.startsWith("*") || p.contains("**")) return false;
        if (p.equals(".*")) return true;

        if (!(p.contains(".") || p.contains("*"))) return s.equals(p);

        StringBuilder sb = new StringBuilder(s);
        while (p.contains("*")) {
            int indexP = p.indexOf("*");
            char lastChar = p.charAt(indexP - 1);
            while (lastChar == p.charAt(indexP + 1) || p.charAt(indexP + 1) == '.') indexP++;

            String block = p.substring(0, indexP + 1);
            if (!block.contains(".")) {
                String[] split = splitString(block, '*');
                lastChar = split[0].charAt(split[0].length() - 1);
                if (split[1].isEmpty()) {

                } else {

                }
            } else {
            }
            p = p.substring(indexP + 1);
        }

        return false;
    }

    private static String[] splitString(String s, char Char) {
        return new String[]{
                s.substring(0, s.indexOf(Char)),
                s.substring(s.indexOf(Char) + 1)
        };
    }

    public static void main(String[] args) {
        System.out.println(
                Arrays.toString(splitString("haiv*", '*'))
        );
    }
}
