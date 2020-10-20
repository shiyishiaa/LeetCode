import java.util.*;

public class Question_23 {
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) return null;
        var k = lists.length;
        if (k == 0) return null;
        if (k == 1) return lists[0];

        ListNode l1, l2;
        if (k != 2) {
            l1 = mergeKLists(Arrays.copyOfRange(lists, 0, lists.length / 2));
            l2 = mergeKLists(Arrays.copyOfRange(lists, lists.length / 2, lists.length));
        } else return mergeTwoLists(lists[0], lists[1]);
        return mergeTwoLists(l1, l2);
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode nextL1 = l1, nextL2 = l2;
        ListNode newList = new ListNode(0, null);
        ListNode head = newList;
        while (nextL1 != null && nextL2 != null) {
            if (nextL1.val < nextL2.val) {
                newList.next = nextL1;
                nextL1 = nextL1.next;
            } else {
                newList.next = nextL2;
                nextL2 = nextL2.next;
            }
            newList = newList.next;
        }
        if (nextL1 == null) newList.next = nextL2;
        if (nextL2 == null) newList.next = nextL1;

        return head.next;
    }

    public static void main(String[] args) {
        System.out.println(mergeKLists(new ListNode[]{
                        ListNode.of(1, 2, 3, 5, 7, 9),
                        ListNode.of(2, 5, 9, 10),
                        ListNode.of(5, 8, 10)
                }
        ));
    }
}

class AnotherMethod {
    public static ListNode mergeKLists(ListNode[] lists) {
        List<ListNode> nodes = new LinkedList<>();

        for (var l : lists)
            while (l != null) {
                nodes.add(l);
                l = l.next;
            }

        nodes.sort(Comparator.comparingInt(o -> o.val));

        var dummy = new ListNode(0);
        var head = dummy;
        for (var l : nodes) {
            dummy.next = l;
            dummy = dummy.next;
        }

        return head.next;
    }
}

/**
 * 优先队列
 *
 * @see PriorityQueue
 * @see <a href="https://leetcode-cn.com/problems/merge-k-sorted-lists/solution/he-bing-kge-pai-xu-lian-biao-by-leetcode-solutio-2/">LeetCode 23</a>
 */
class Solution_23 {
    PriorityQueue<Status> queue = new PriorityQueue<>();

    public ListNode mergeKLists(ListNode[] lists) {
        for (ListNode node : lists) {
            if (node != null) {
                queue.offer(new Status(node.val, node));
            }
        }
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (!queue.isEmpty()) {
            Status f = queue.poll();
            tail.next = f.ptr;
            tail = tail.next;
            if (f.ptr.next != null) {
                queue.offer(new Status(f.ptr.next.val, f.ptr.next));
            }
        }
        return head.next;
    }

    static class Status implements Comparable<Status> {
        int val;
        ListNode ptr;

        Status(int val, ListNode ptr) {
            this.val = val;
            this.ptr = ptr;
        }

        public int compareTo(Status status2) {
            return this.val - status2.val;
        }
    }
}
