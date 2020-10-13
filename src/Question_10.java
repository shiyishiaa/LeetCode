import java.util.ArrayDeque;
import java.util.Stack;

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

        ArrayDeque<Character> sQueue = new ArrayDeque<>();
        for (Character k : s.toCharArray()) sQueue.add(k);

        ArrayDeque<Character> pQueue = new ArrayDeque<>();
        for (Character k : p.toCharArray()) pQueue.add(k);

        // Match trailing single characters.
        Character peekLast = pQueue.peekLast();
        if (peekLast == null) return false;
        while (!peekLast.equals('*')) {
            Character pollLast = pQueue.pollLast();
            Character sPollLast = sQueue.pollLast();
            if (sPollLast == null || pollLast == null) return false;
            if (!pollLast.equals(sPollLast) && !pollLast.equals('.')) return false;
            peekLast = pQueue.peekLast();
            if (peekLast == null)
                return sQueue.isEmpty();
        }

        Stack<String> pStack = new Stack<>();

        // Match patterns with priority.
        while (!pQueue.isEmpty() || !sQueue.isEmpty()) {
            if (pQueue.isEmpty()) {
                if (pStack.peek().equals(".*")) return true;
                Character toMatch = pStack.pop().charAt(0);
                Character poll = sQueue.poll();
                while (poll != null && poll.equals(toMatch)) {
                    poll = sQueue.poll();
                }
                return sQueue.isEmpty();
            }

            if (sQueue.isEmpty()) return true;

            Character nowPoll = pQueue.poll();
            assert nowPoll != null;
            // Single star return false.
            if (nowPoll.equals('*')) return false;
            Character nowPeek = pQueue.peek();
            // Trailing single characters have been matched at the start of this function.
            assert nowPeek != null;

            // Pattern is "?*";
            if (nowPeek.equals('*')) {
                // Patterns like "?*" will be matched according to priority. (".*"<"m*")
                StringBuilder nowPattern = new StringBuilder();
                nowPattern.append(nowPoll).append(pQueue.poll());
                // Store nowPattern if pattern stack is empty.
                if (pStack.isEmpty()) {
                    pStack.push(nowPattern.toString());
                    continue;
                }
//
//                Character afterPeek = pQueue.peek();
//                if (afterPeek == null) {
//                    // pQueue is empty.
//                    // TODO Match StringBuilder "before" and the rest of sQueue.
//                }
//                // Single star return false.
//                else if (afterPeek.equals('*')) return false;
//                else if (afterPeek.equals('.')) {
//                    Character afterPoll = pQueue.poll(); // .
//                    assert afterPoll != null;
//
//                    afterPeek = pQueue.peek();
//                    // Trailing single characters have been matched at the start of this function.
//                    assert afterPeek != null;
//
//                    if (afterPeek.equals('*')) {
//                        // After pattern is ".*"
//                        // Push after to stack first due to its lowest priority.
//                        String peekStack = pStack.peek();
//                        // If before is "m*", start matching before pattern.
//                        if (!peekStack.startsWith(".")) {
//                            pStack.pop();
//                            Character peekQueue = sQueue.peek();
//                            if (peekQueue != null && peekQueue.equals(peekStack.charAt(0))) {
//                                do {
//                                    sQueue.poll();
//                                    peekQueue = sQueue.peek();
//                                } while (peekQueue != null && peekQueue.equals(peekStack.charAt(0)));
//                            }
//                        }
//                        pStack.push(String.valueOf(afterPoll) + pQueue.poll());
//                    } else {
//                        // After pattern is "."
//                        // Push before to stack first. ("?*" < ".")
//                        pStack.push(nowPattern.toString());
//                        pQueue.poll();
//                        sQueue.poll();
//                    }
//                } else {
//                    // TODO After pattern starts with "m".
//                    Character afterPoll = pQueue.poll(); // m
//                    assert afterPoll != null;
//
//                    afterPeek = pQueue.peek();
//                    // Trailing single characters have been matched at the start of this function.
//                    assert afterPeek != null;
//
//                    if (afterPeek.equals('*')) {
//                        // After pattern is "m*".
//
//                    } else {
//                        // After pattern is "m".
//                        // Single character like "m" will be matched instantly due to their highest priority.
//                        afterPoll = pQueue.poll();
//                        assert afterPoll != null;
//                        if (!afterPoll.equals(sQueue.poll())) return false;
//
//                        pStack.push(nowPattern.toString());
//                    }
//                }
                String peekStack = pStack.peek();
                // If before is "m*", remove all the same characters ("m") at the start of sQueue.
                if (!peekStack.startsWith(".")) {
                    pStack.pop();
                    Character peekQueue = sQueue.peek();
                    if (peekQueue != null && peekQueue.equals(peekStack.charAt(0))) {
                        do {
                            sQueue.poll();
                            peekQueue = sQueue.peek();
                        } while (peekQueue != null && peekQueue.equals(peekStack.charAt(0)));
                    }
                }
                // Push now pattern.
                pStack.push(nowPattern.toString());
                // If before is ".*", nowPattern is covered, for ".*.*m*" equals ".*".
            }
            // Whatever before is, "." will also be instantly matched.
            else if (nowPoll.equals('.')) {
                sQueue.poll();
            }
            // Single character like "m" will be matched instantly due to their highest priority.
            else {
                if (pStack.isEmpty())
                    if (!nowPoll.equals(sQueue.poll()))
                        return false;
                    else
                        continue;

                String peekStack = pStack.peek();
                // If before is ".*", remove all different characters.
                if (peekStack.startsWith(".")) {
                    Character sPeek = sQueue.peek();
                    if (sPeek != null && !sPeek.equals(nowPoll)) {
                        do {
                            sQueue.poll();
                            sPeek = sQueue.peek();
                        } while (sPeek != null && !sPeek.equals(nowPoll));
                    }
                    pStack.pop();
                    if (sQueue.isEmpty()) return false;
                    sQueue.poll();
                }
                // If before is "m*", remove all the same characters ("m") at the start of sQueue.
                else {
                    pStack.pop();
                    Character peekQueue = sQueue.peek();
                    if (peekQueue != null && peekQueue.equals(peekStack.charAt(0))) {
                        do {
                            sQueue.poll();
                            peekQueue = sQueue.peek();
                        } while (peekQueue != null && peekQueue.equals(peekStack.charAt(0)));
                    }
                    // If nowPoll is not equivalent to "m".
                    if (!nowPoll.equals(peekStack.charAt(0))) {
                        if (!nowPoll.equals(sQueue.poll())) return false;
                    } else {
                        int minLength = 1;
                        Character pSameChar = pQueue.peek();
                        while (pSameChar != null && (pSameChar.equals('*') || pSameChar.equals(nowPoll))) {
                            if (pSameChar.equals('*')) minLength--;
                            else minLength++;
                            pQueue.poll();
                            pSameChar = pQueue.peek();
                        }

                        int realLength = 0;
                        Character sSameChar = sQueue.peek();
                        while (sSameChar != null && sSameChar.equals(peekStack.charAt(0))) {
                            realLength++;
                            sQueue.poll();
                            sSameChar = sQueue.peek();
                        }

                        if (realLength < minLength) return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isMatch("att"
                , "att*"));
    }
}
