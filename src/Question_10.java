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

        ArrayDeque<Character> sQueue = new ArrayDeque<>();
        for (Character k : s.toCharArray()) sQueue.add(k);

        ArrayDeque<Character> pQueue = new ArrayDeque<>();
        for (Character k : p.toCharArray()) pQueue.add(k);

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

        ArrayDeque<String> pSplit = new ArrayDeque<>();

        all:
        while (!pQueue.isEmpty()) {
            Character poll = pQueue.poll();
            if (poll == null) return false;
            if (!poll.equals('*')) {
                Character peek = pQueue.peek();
                if (peek == null)
                    if (!pQueue.isEmpty())
                        return false;
                    else {
                        if (sQueue.size() != 1) return false;
                        return sQueue.poll().equals(poll) || poll.equals('.');
                    }
                if (!peek.equals('*')) {
                    // Pattern like (m)k/(m). To match "m".
                    // The most plain condition.
                    Character sPoll = sQueue.poll(); // Whatever the character s provides, we need to poll it out.
                    if (!(poll.equals(sPoll) || poll.equals('.'))) return false;
                } else {
                    StringBuilder sbp = new StringBuilder(String.valueOf(poll));
                    emptyQueue:
                    do {
                        poll = pQueue.poll();
                        sbp.append(poll);

                        peek = pQueue.peek();
                        if (peek == null)
                            if (pQueue.isEmpty())
                                if (sQueue.isEmpty())
                                    break all;
                                else
                                    break;
                            else return false;

                        char beforeStar = sbp.charAt(sbp.length() - 2);
                        if (peek.equals(beforeStar)) {
                            do {
                                poll = pQueue.poll();
                                sbp.append(poll);

                                peek = pQueue.peek();
                                if (peek == null) break emptyQueue;
                            } while (peek.equals(beforeStar));
                        } else {
                            // Pattern like (m*)k/(m*). To match "m*"
                            Character sPeek = sQueue.peek();
                            if (sPeek == null) return false;
                            // m*==m{n}(n>0) Need to poll till next sPeek is not "m".
                            char firstChar = sbp.charAt(0); // Refers to "m".
                            if (sPeek.equals(firstChar)) {
                                // String s is firstChar.
                                do {
                                    sQueue.poll();
                                    sPeek = sQueue.peek();
                                    if (sPeek == null) break;
                                } while (sPeek.equals(firstChar));
                                continue all;
                            }
                            // m*==m{0} Do not need to poll.
                        }
                    } while (peek.equals('*'));
                    // 比较~4
                    // Pattern like (m*mm*)k/(m*mm*). To match "m*mm*"
                    StringBuilder sbs = new StringBuilder();
                    Character sPeek = sQueue.peek();
                    if (sPeek == null) return false;
                    // m*==m{n}(n>0) Need to poll till next sPeek is not "m".
                    char firstChar = sbp.charAt(0); // Refers to "m".
                    String matchAll = "";
                    if (firstChar == '.') {
                        matchAll = sbp.toString();
                        do {
                            pSplit.push(sbp.toString());
                            sbp.replace(0, sbp.length(), "");
                            sbp.append(pQueue.poll());
                            sbp.append(pQueue.poll());
                        } while (sbp.toString().equals(".*"));
                    } else if (sPeek.equals(firstChar)) {
                        // String s is firstChar.
                        do {
                            sbs.append(sQueue.poll());
                            sPeek = sQueue.peek();
                            if (sPeek == null) break;
                        } while (sPeek.equals(firstChar));
                        // m*mmm*m String s must have at least 3(sLeast) "m".
                        String s1 = sbp.toString();
                        int num = 0;
                        for (int i = 0; i < s1.length(); i++)
                            if (s1.charAt(i) == '*') num++;
                        int sLeast = sbp.length() - 2 * num;
                        if (sbs.length() < sLeast) return false;
                    } else {
                        // String s is not firstChar.
                        do {
                            sbp.replace(0, 2, "");
                        } while (sbp.toString().startsWith(firstChar + "*"));
                        if (sbp.length() != 0) return false;
                    }
                }
            } else return false;
        }
        return sQueue.isEmpty() || !pSplit.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isMatch("a"
                , ".*..a*"));
    }
}
