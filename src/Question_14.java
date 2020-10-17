import java.util.Arrays;
import java.util.TreeSet;

public class Question_14 {
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        var sb = new StringBuilder();
        var treeSet = new TreeSet<>(Arrays.asList(strs));

        String first = treeSet.first();

        all:
        for (var i = 0; i < first.length(); i++) {
            var nowChar = first.charAt(i);
            for (var s : treeSet)
                if (s.charAt(i) != nowChar)
                    break all;
            sb.append(nowChar);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{
                        "fm",
                        "flag",
                        "fla",
                }
        ));
    }
}

/**
 * 分治/二分
 * @see <a href="https://leetcode-cn.com/problems/longest-common-prefix/solution/zui-chang-gong-gong-qian-zhui-by-leetcode-solution/">LeetCode 14</a>
 */
class Solution_14 {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        } else {
            return longestCommonPrefix(strs, 0, strs.length - 1);
        }
    }

    public String longestCommonPrefix(String[] strs, int start, int end) {
        if (start == end) {
            return strs[start];
        } else {
            int mid = (end - start) / 2 + start;
            String lcpLeft = longestCommonPrefix(strs, start, mid);
            String lcpRight = longestCommonPrefix(strs, mid + 1, end);
            return commonPrefix(lcpLeft, lcpRight);
        }
    }

    public String commonPrefix(String lcpLeft, String lcpRight) {
        int minLength = Math.min(lcpLeft.length(), lcpRight.length());
        for (int i = 0; i < minLength; i++) {
            if (lcpLeft.charAt(i) != lcpRight.charAt(i)) {
                return lcpLeft.substring(0, i);
            }
        }
        return lcpLeft.substring(0, minLength);
    }
}
