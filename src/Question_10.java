import java.util.ArrayDeque;

public class Question_10 {
    private static final boolean SUCCESS = false;

    public static boolean fail_isMatch(String s, String p) {
        if (p == null) return s == null;
        if (s == null) return false;
        final String allMatch = ".*";
        if (p.equals(allMatch)) return true;
        if (!(p.contains(".") || p.contains("*"))) return s.equals(p);

        var sQueue = new ArrayDeque<Character>();
        for (var k : s.toCharArray()) sQueue.add(k);

        var pQueue = new ArrayDeque<String>();
        char[] pChars = p.toCharArray();
        for (int i = 0; i < pChars.length; i++) {
            if (i + 1 < pChars.length) { // End condition.
                if (pChars[i + 1] == '*') { // Pattern "?*".
                    if (pChars[i] == '*') // Pattern "**"
                        return false;
                    else { //Pattern "m*" or ".*"
                        // If before pattern is ".*", this one is covered.
                        var pPeekLast = pQueue.peekLast();
                        if (allMatch.equals(pPeekLast)) {
                            i++;
                            continue;
                        }

                        // If this pattern is ".*", all before continuous ".*" will be covered.
                        var thisPattern = String.valueOf(pChars[i]) + pChars[i + 1];
                        if (allMatch.equals(thisPattern)) {
                            var lastPattern = pQueue.peekLast();
                            while (lastPattern != null && lastPattern.endsWith("*")) {
                                pQueue.pollLast();
                                lastPattern = pQueue.peekLast();
                            }
                            pQueue.add(thisPattern);
                            i++;
                            continue;
                        }

                        // If before pattern is equivalent to this pattern, this pattern is covered.
                        if (thisPattern.equals(pPeekLast)) {
                            i++;
                            continue;
                        }
                        pQueue.add(thisPattern);
                        i++;
                    }
                    continue;
                }
            }
            // Single character like "m" or "."
            pQueue.add(String.valueOf(pChars[i]));
        }

        // Match the start until encountering ".*"
        var peek = pQueue.peek();
        while (peek != null && !peek.equals(allMatch)) {
            char toMatch = peek.charAt(0);

            if (toMatch == '.') sQueue.poll();

            int minLength = 0;
            while (peek != null && peek.charAt(0) == toMatch) {
                minLength++;
                if (peek.endsWith("*")) minLength--;
                pQueue.poll();
                peek = pQueue.peek();
            }

            int realLength = 0;
            Character sPeek = sQueue.peek();
            while (sPeek != null && sPeek.equals(toMatch)) {
                realLength++;
                sQueue.poll();
                sPeek = sQueue.peek();
            }

            if (realLength < minLength) return false;
        }

        // Match the end until encountering ".*"
        var peekLast = pQueue.peekLast();
        while (peekLast != null && !peekLast.equals(allMatch)) {
            char toMatch = peekLast.charAt(0);

            if (toMatch == '.') sQueue.pollLast();

            int minLength = 0;
            while (peekLast != null && peekLast.charAt(0) == toMatch) {
                minLength++;
                if (peekLast.endsWith("*")) minLength--;
                pQueue.pollLast();
                peekLast = pQueue.peekLast();
            }

            int realLength = 0;
            var sPeekLast = sQueue.peekLast();
            while (sPeekLast != null && sPeekLast.equals(toMatch)) {
                realLength++;
                sQueue.pollLast();
                sPeekLast = sQueue.peekLast();
            }

            if (realLength < minLength) return false;
        }

        if (allMatch.equals(pQueue.peekLast()) && allMatch.equals(pQueue.peekLast())) {
            // Remove the start and trail. (".*")
            pQueue.poll();
            pQueue.pollLast();

            var patterns = new ArrayDeque<String>();
            var sb = new StringBuilder();

            while (!pQueue.isEmpty()) {
                var pPeek = pQueue.peek();
                do {
                    sb.append(pPeek);
                    pQueue.poll();
                    pPeek = pQueue.peek();
                } while (!(allMatch.equals(peek) || pPeek == null));
                patterns.add(sb.toString());
                sb = new StringBuilder();

                if (allMatch.equals(pPeek)) pQueue.poll();
                else break;
            }

            if (pQueue.isEmpty()) return true;

        }

        if (pQueue.size() <= 1) {
            if (pQueue.isEmpty() && sQueue.isEmpty()) return true;
            for (var restPattern : pQueue) if (!restPattern.equals(allMatch)) return false;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(Solution_10.isMatch("aa",
                ".*"));
    }
}

/**
 * 动态规划
 * @see <a href="https://leetcode-cn.com/problems/regular-expression-matching/solution/zheng-ze-biao-da-shi-pi-pei-by-leetcode-solution/">LeetCode 第十题</a>
 */
class Solution_10 {
    public static boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    if (j >= 2)
                        f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    public static boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }
}
