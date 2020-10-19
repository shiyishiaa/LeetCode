import java.util.*;

public class Question_18 {
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        var length = nums.length;
        if (length <= 3) return List.of();

        Arrays.sort(nums);

        var ans = new ArrayList<List<Integer>>();

        var first = 0;
        var second = length - 3;
        var third = length - 2;
        var fourth = length - 1;
        var nowSum = nums[first] + nums[second] + nums[third] + nums[fourth];

        for (; first < length; first++) {
            if (first > 0 && nums[first] == nums[first - 1]) continue;

            second = length - 3;
            while (first < second) {

                for (third = second + 1; third < length; third++) {
                    if (third != second + 1 && nums[third] == nums[third - 1]) continue;

                    fourth = length - 1;
                    nowSum = nums[first] + nums[second] + nums[third] + nums[fourth];
                    while (third < fourth && nowSum > target) {
                        fourth--;
                        nowSum = nums[first] + nums[second] + nums[third] + nums[fourth];
                    }
                    if (third == fourth)
                        break;
                    if (nowSum == target) ans.add(List.of(nums[first], nums[second], nums[third], nums[fourth]));
                }
                second--;
            }
        }

        Set<List<Integer>> set = new TreeSet<>(new Comparator<>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                Integer[] i1, i2;
                if (o1.size() < o2.size()) {
                    i1 = getInteger(o1);
                    i2 = getInteger(o2);
                } else {
                    i1 = getInteger(o2);
                    i2 = getInteger(o1);
                }

                for (var i = 0; i < i1.length; i++) {
                    if (i1[i] < i2[i]) return 1;
                    if (i1[i] > i2[i]) return -1;
                }
                return i1.length == i2.length ? 0 : 1;
            }

            private Integer[] getInteger(List<Integer> o) {
                Object[] os = o.toArray();
                Integer[] is = new Integer[os.length];
                for (var i = 0; i < is.length; i++)
                    is[i] = (Integer) os[i];
                return is;
            }
        });
        set.addAll(ans);
        return List.copyOf(set);
    }

    public static void main(String[] args) {
        System.out.println(fourSum(new int[]{
                -3, -2, -1, 0, 0, 1, 2, 3
        }, 0));
    }
}
