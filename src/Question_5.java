public class Question_5 {
    private static final boolean fail = true;

    public static boolean isPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++)
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) return false;
        return true;
    }

    public static String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) return s;
        if (s.length() == 2) return (s.charAt(0) == s.charAt(1)) ? s : String.valueOf(s.charAt(0));
        int length = 0;
        String maxOdd = "", maxEven = "";

        boolean isEven;

        for (int i = 0; i < s.length(); i++) {
            int odd = findLength(s, i, length, false);
            String foundOdd = odd >= length ? s.substring(i - odd, i + odd + 1) : null;

            if (foundOdd != null)
                if (foundOdd.length() > maxOdd.length()) {
                    length = (foundOdd.length() - 1) / 2;
                    maxOdd = foundOdd;
                }

            String foundEven;
            if (i + 1 >= s.length()) break;
            if (s.charAt(i) == s.charAt(i + 1)) {
                int even = findLength(s, i, length, true);
                if (even >= length)
                    foundEven = s.substring(i - even, i + 1 + even + 1);
                else
                    foundEven = s.substring(i, i + 2);
            } else foundEven = null;
            if (foundEven != null)
                if (foundEven.length() > maxEven.length()) {
                    length = (foundEven.length() - 2) / 2;
                    maxEven = foundEven;
                }
            isEven = maxEven.length() > maxOdd.length();

            if (isEven) {
                if (i + length + 2 >= s.length()) break;
            } else {
                if (i + length + 1 >= s.length()) break;
            }
        }

        return maxOdd.length() > maxEven.length() ? maxOdd : maxEven;
    }

    private static int findLength(String s, int startIndex, int knownLength, boolean thisIsEven) {
        if (startIndex == 0) return 0;

        StringBuilder sb = new StringBuilder(s);
        sb.replace(startIndex, startIndex + (thisIsEven ? 2 : 1), "");

        int index = startIndex - 1, length = knownLength;

        boolean noLessThan = false;
        int left, right;
        if (length == 0) {
            left = index - length;
            right = index + 1 + length;
        } else {
            left = index - length + 1;
            right = index + length;
            noLessThan = true;
        }
        while (left >= 0 && right < sb.length())
            if (sb.charAt(left) == sb.charAt(right)) {
                length++;
                left = index - length;
                right = index + 1 + length;
            } else break;
        if (noLessThan) {
            if (isPalindrome(sb.substring(index - knownLength + 1, index + 1 + knownLength)))
                return length;
            else return -1;
        } else return -1;
    }

    public static void main(String[] args) {
        System.out.println(Solution_5.longestPalindrome("adaadc"));
    }
}

class Solution_5 {
    public static String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        String ans = "";
        for (int l = 0; l < n; ++l) {
            for (int i = 0; i + l < n; ++i) {
                int j = i + l;
                if (l == 0) {
                    dp[i][j] = true;
                } else if (l == 1) {
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                } else {
                    dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]);
                }
                if (dp[i][j] && l + 1 > ans.length()) {
                    ans = s.substring(i, i + l + 1);
                }
            }
        }
        return ans;
    }
}