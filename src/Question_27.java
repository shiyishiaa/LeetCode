import java.util.Arrays;

public class Question_27 {
    public static int removeElement(int[] nums, int val) {
        int length = nums.length;
        if (length == 0) return 0;

        var lastIndex = 0;
        for (var i = 0; i < length; i++)
            if (nums[i] != val) {
                nums[lastIndex] = nums[i];
                lastIndex++;
            }

        return lastIndex;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 3, 4, 5, 6};
        System.out.println(removeElement(a, 3));
        System.out.println(Arrays.toString(a));
    }
}
