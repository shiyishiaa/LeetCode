import java.util.Arrays;

public class Question_16 {
    public static int threeSumClosest(int[] nums, int target) {
        var length = nums.length;
        Arrays.sort(nums);

        var closest = nums[0] + nums[1] + nums[2];


        for (var i = 0; i < length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            var k = length - 1;

            for (var j = i + 1; j < length - 1; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                var nowSum = nums[i] + nums[j] + nums[k];
                while (j < k && nowSum > target) {
                    k--;
                    nowSum = nums[i] + nums[j] + nums[k];
                }

                if (j != k && nowSum == target) return target;
                int cAbs = Math.abs(closest - target);
                int nAbs = Math.abs(nowSum - target);
                if (k == length - 1) {
                    if (nAbs < cAbs)
                        closest = nowSum;
                    continue;
                }

                int afterSum = nums[i] + nums[j] + nums[k + 1];
                int aAbs = Math.abs(afterSum - target);
                if (j == k) {
                    if (cAbs > aAbs) closest = afterSum;
                    break;
                }

                int[] abs = {cAbs, nAbs, aAbs};
                Arrays.sort(abs);

                if (abs[0] == cAbs) continue;
                if (abs[0] == nAbs) closest = nowSum;
                else if (abs[0] == aAbs) closest = afterSum;
            }
        }
        return closest;
    }

    public static void main(String[] args) {
        System.out.println(threeSumClosest(new int[]{
                0, 0, 0
        }, 1));
    }
}
