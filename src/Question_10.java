import java.util.ArrayDeque;

public class Question_10 {
    private static boolean priorTo(String before, String after) {
        if (before.length() != after.length()) return before.length() < after.length();
        if (before.length() == 1) return !before.equals(".") && after.equals(".");
        else return !before.startsWith(".") && after.startsWith(".");
    }

    public static boolean isMatch(String s, String p) {
        if (p == null) return s == null;
        if (s == null) return false;
        if (p.equals(".*")) return true;
        if (!(p.contains(".") || p.contains("*"))) return s.equals(p);

        var sQueue = new ArrayDeque<Character>();
        for (var k : s.toCharArray()) sQueue.add(k);

        var pQueue = new ArrayDeque<Character>();
        for (var k : p.toCharArray()) pQueue.add(k);

        // Match trailing single characters.
        var peekLast = pQueue.peekLast();
        if (peekLast == null) return false;
        while (!peekLast.equals('*')) {
            var pollLast = pQueue.pollLast();
            var sPollLast = sQueue.pollLast();
            if (sPollLast == null || pollLast == null) return false;
            if (!pollLast.equals(sPollLast) && !pollLast.equals('.')) return false;
            peekLast = pQueue.peekLast();
            if (peekLast == null)
                return sQueue.isEmpty();
        }

        var pStack = new ArrayDeque<String>();

        // Match patterns with priority.
        while (!pQueue.isEmpty() || !sQueue.isEmpty()) {
            if (pQueue.isEmpty()) {
                var stackBottom = pStack.peekLast();
                if (stackBottom != null && stackBottom.equals(".*")) return true;
                var toMatch = pStack.pop().charAt(0);
                var peek = sQueue.peek();
                while (peek != null && peek.equals(toMatch)) peek = sQueue.poll();
                return sQueue.isEmpty() && peek == null;
            }

            if (sQueue.isEmpty()) {
                int starPattern = 0;
                var peek = pQueue.peek();
                while (!pQueue.isEmpty()) {
                    if (peek != null)
                        if (peek.equals('*')) starPattern--;
                        else starPattern++;
                    pQueue.poll();
                    peek = pQueue.peek();
                }
                return starPattern == 0;
            }

            var nowPoll = pQueue.poll();
            assert nowPoll != null;
            // Single star return false.
            if (nowPoll.equals('*')) return false;
            var nowPeek = pQueue.peek();
            // Trailing single characters have been matched at the start of this function.
            assert nowPeek != null;

            // Pattern is "?*";
            if (nowPeek.equals('*')) {
                // Patterns like "?*" will be matched according to priority. (".*"<"m*")
                var nowPattern = new StringBuilder();
                nowPattern.append(nowPoll).append(pQueue.poll());
                // Store nowPattern if pattern stack is empty.
                if (pStack.isEmpty()) {
                    pStack.push(nowPattern.toString());
                    continue;
                }

                var peekStack = pStack.peek();
                // If before is "m*", remove all the same characters ("m") at the start of sQueue.
                if (!peekStack.startsWith(".")) {
                    pStack.pop();
                    var peekQueue = sQueue.peek();
                    while (peekQueue != null && peekQueue.equals(peekStack.charAt(0))) {
                        sQueue.poll();
                        peekQueue = sQueue.peek();
                    }
                }
                // Push now pattern.
                pStack.push(nowPattern.toString());
                // If before is ".*", nowPattern is covered, for ".*.*m*" equals ".*".
            } else {
                if (nowPoll.equals('.')) // Whatever before is, "." will also be instantly matched.
                    sQueue.poll();
                else { // Single character like "m" will be matched instantly due to their highest priority.
                    if (pStack.isEmpty())
                        if (!nowPoll.equals(sQueue.poll()))
                            return false;
                        else
                            continue;

                    var peekStack = pStack.peek();
                    if (peekStack.startsWith(".")) {
                        // If before is ".*", remove all different characters first.
                        var peek = sQueue.peek();
                        while (peek != null && !peek.equals(nowPoll)) {
                            sQueue.poll();
                            peek = sQueue.peek();
                        }

                        // Calculate the min length of nowPoll character.
                        int minLength = 1;
                        var pSameChar = pQueue.peek();
                        while (pSameChar != null && (pSameChar.equals('*') || pSameChar.equals(nowPoll))) {
                            if (pSameChar.equals('*')) minLength--;
                            else minLength++;
                            pQueue.poll();
                            pSameChar = pQueue.peek();
                        }

                        // Calculate the real length of nowPoll character.
                        int realLength = 0;
                        var sSameChar = sQueue.peek();
                        while (sSameChar != null && sSameChar.equals(nowPoll)) {
                            realLength++;
                            sQueue.poll();
                            sSameChar = sQueue.peek();
                        }
                        if (realLength < minLength) return false;

                        pStack.pop();
                        if (sQueue.isEmpty()) return false;
                    } else {
                        var toMatch = pStack.pop().charAt(0);
                        if (nowPoll.equals(toMatch)) {
                            int minLength = 1;
                            var pSameChar = pQueue.peek();
                            while (pSameChar != null && (pSameChar.equals('*') || pSameChar.equals(nowPoll))) {
                                if (pSameChar.equals('*')) minLength--;
                                else minLength++;
                                pQueue.poll();
                                pSameChar = pQueue.peek();
                            }

                            int realLength = 0;
                            var sSameChar = sQueue.peek();
                            while (sSameChar != null && sSameChar.equals(toMatch)) {
                                realLength++;
                                sQueue.poll();
                                sSameChar = sQueue.peek();
                            }

                            if (realLength < minLength) return false;
                        } else { // If nowPoll is not equivalent to "m".
                            var sPeek = sQueue.peek();
                            while (sPeek != null && sPeek.equals(toMatch)) {
                                sQueue.poll();
                                sPeek = sQueue.peek();
                            }
                            var nextDifferentChar = sQueue.poll();
                            if (!nowPoll.equals(nextDifferentChar)) return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isMatch("cbaacacaaccbaabcb",
                "c*b*b*.*ac*.*bc*a*"));
    }
}
