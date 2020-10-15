public class Question_11 {
    public static int maxArea(int[] height) {
        var max = 0;
        for (var i = 1; i <= height.length - 1; i++)
            for (var j = 0; i + j < height.length; j++) {
                var nowArea = i * Math.min(height[j], height[j + i]);
                if (nowArea > max)
                    max = nowArea;
            }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{1, 1}));
    }
}

/**
 * 双指针
 * @see <a href="https://leetcode-cn.com/problems/container-with-most-water/solution/sheng-zui-duo-shui-de-rong-qi-by-leetcode-solution/">LeetCode 第十一题</a>
 */
class Solution_11 {
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r) {
            int area = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, area);
            if (height[l] <= height[r]) {
                ++l;
            } else {
                --r;
            }
        }
        return ans;
    }
}
