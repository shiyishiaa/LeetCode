import java.util.*;
import java.util.stream.Collectors;

public class Question_349 {
    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new TreeSet<>();
        for (int n : nums1) set1.add(n);

        Set<Integer> set2 = new TreeSet<>();
        for (int n : nums2) set2.add(n);

        PriorityQueue<Integer> n1 = new PriorityQueue<>(set1);
        PriorityQueue<Integer> n2 = new PriorityQueue<>(set2);

        List<Integer> ans = new ArrayList<>();
        while (!(n1.isEmpty() || n2.isEmpty())) {
            int num1 = n1.peek();
            int num2 = n2.peek();
            if (num1 == num2) {
                ans.add(num1);
                n1.poll();
                n2.poll();
            } else if (num1 > num2) n2.poll();
            else n1.poll();
        }
        int[] ret = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++)
            ret[i] = ans.get(i);
        return ret;
    }

    public static int[] intersection_2(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        for (int i : nums1) list.add(i);
        for (int i : nums2) set.add(i);
        list.retainAll(set);
        return new HashSet<>(list).stream().mapToInt(i -> i).toArray();
    }

    public static int[] intersection_3(int[] nums1, int[] nums2) {
        return Arrays.stream(nums2).distinct().filter(
                Arrays.stream(nums1).boxed().collect(Collectors.toSet())::contains).toArray();
    }

    public static void main(String[] args) {
        int[] nums1 = {4, 9, 5}, nums2 = {9, 4, 9, 8, 4};
        List<Integer> list = new ArrayList<>();
        for (int i : nums1) list.add(i);
        System.out.println((list.stream().mapToInt(value -> value).average()));

//        System.out.println(Arrays.toString(intersection_3(nums1, nums2)));
    }
}

class Solution_394 {

}
