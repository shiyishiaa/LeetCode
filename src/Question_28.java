public class Question_28 {
    public static int strStr(String haystack, String needle) {
        int needleLength = needle.length();
        if (needleLength == 0) return 0;
        int haystackLength = haystack.length();
        if (haystackLength < needleLength) return -1;

        var start = needle.charAt(0);
        var end = needle.charAt(needleLength - 1);
        for (var i = 0; i + needleLength <= haystackLength; i++)
            if (haystack.charAt(i) == start && haystack.charAt(i + needleLength - 1) == end) {
                var substring = haystack.substring(i, i + needleLength);
                if (needle.equals(substring))
                    return i;
            }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(strStr("abc", "b"));
    }
}

/**
 * 比较哈希值
 *
 * @see <a href="https://leetcode-cn.com/problems/implement-strstr/solution/shi-xian-strstr-by-leetcode/">LeetCode 28</a>
 */
class Solution_28 {
    // function to convert character to integer
    public int charToInt(int idx, String s) {
        return (int) s.charAt(idx) - (int) 'a';
    }

    public int strStr(String haystack, String needle) {
        int L = needle.length(), n = haystack.length();
        if (L > n) return -1;

        // base value for the rolling hash function
        int a = 26;
        // modulus value for the rolling hash function to avoid overflow
        long modulus = (long) Math.pow(2, 31);

        // compute the hash of strings haystack[:L], needle[:L]
        long h = 0, ref_h = 0;
        for (int i = 0; i < L; ++i) {
            h = (h * a + charToInt(i, haystack)) % modulus;
            ref_h = (ref_h * a + charToInt(i, needle)) % modulus;
        }
        if (h == ref_h) return 0;

        // const value to be used often : a**L % modulus
        long aL = 1;
        for (int i = 1; i <= L; ++i) aL = (aL * a) % modulus;

        for (int start = 1; start < n - L + 1; ++start) {
            // compute rolling hash in O(1) time
            h = (h * a - charToInt(start - 1, haystack) * aL
                    + charToInt(start + L - 1, haystack)) % modulus;
            if (h == ref_h) return start;
        }
        return -1;
    }
}
