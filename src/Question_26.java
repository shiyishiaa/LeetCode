public class Question_26 {
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return 1;

        var length = nums.length;
        var max = nums[0];
        var newIndex = 1;
        for (var i = 0; i < length; i++) {
            if (nums[i] > max) {
                max = nums[newIndex] = nums[i];
                newIndex++;
            }
        }
        return newIndex;
    }

    public static void main(String[] args) {
        System.out.println(removeDuplicates(new int[]{1, 1, 1, 1}));
    }
}
