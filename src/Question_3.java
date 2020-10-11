public class Question_3 {
    public static int lengthOfLongestSubstring(String s) {
        StringBuilder sb = new StringBuilder(s);
        String temp = "";
        int maxLength = 0;
        all:
        while (sb.length() != 0)
            for (int i = 0; i < sb.length(); i++) {
                if (!temp.contains(sb.substring(i, i + 1)))
                    temp = sb.substring(0, i + 1);
                else {
                    maxLength = Math.max(temp.length(), maxLength);
                    sb.replace(0, sb.indexOf(String.valueOf(sb.charAt(i))) + 1, "");
                    temp = "";
                    break;
                }
                if (i == sb.length() - 1) {
                    maxLength = Math.max(temp.length(), maxLength);
                    break all;
                }
            }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(Solution_3.lengthOfLongestSubstring(" "));
    }
}

class Solution_3 {
    public static int lengthOfLongestSubstring(String s) {
        // 记录字符上一次出现的位置
        int[] last = new int[128];
        for (int i = 0; i < 128; i++) {
            last[i] = -1;
        }
        int n = s.length();

        int res = 0;
        int start = 0; // 窗口开始位置
        for (int i = 0; i < n; i++) {
            int index = s.charAt(i);
            start = Math.max(start, last[index] + 1);
            res = Math.max(res, i - start + 1);
            last[index] = i;
        }

        return res;
    }
}