public class Question_5 {
    public static boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString().equals(s);
    }

    public static String longestPalindrome(String s) {
        if (s.length() == 1) return s;
        int minLength = 1, index = 0;
        boolean isEven = false;
        all:
        for (int i = 0; i < s.length(); i++) {
            boolean now;
            if (i != s.length() - 1) {
                now = s.charAt(i) == s.charAt(i + 1);
                if (now && minLength == 1) {
                    index = i;
                    isEven = true;
                }
            } else break;
            while (i + minLength < s.length() && i - minLength >= 0) {
                if (now) {
                    if (s.charAt(i - minLength) == s.charAt(i + 1 + minLength)) {
                        index = i;
                        minLength++;
                        isEven = true;
                    } else if (index - minLength + 1 < 0 || index + minLength >= s.length()) break all;
                    else break;
                } else {
                    if (s.charAt(i - minLength) == s.charAt(i + minLength)) {
                        index = i;
                        minLength++;
                        isEven = false;
                    } else if (index - minLength + 1 < 0 || index + minLength - 1 >= s.length()) break all;
                    else break;
                }
            }
        }
        minLength--;
        return isEven ?
                s.substring(index - minLength, index + 1 + minLength + 1) :
                s.substring(index - minLength, index + minLength + 1);
    }


    public static void main(String[] args) {
        System.out.println(longestPalindrome("ccc"));
    }
}

class Solution_5 {
//    public String longestPalindrome(String s) {
//
//    }
}