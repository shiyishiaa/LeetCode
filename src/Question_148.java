import java.util.Comparator;
import java.util.PriorityQueue;

public class Question_148 {
    public static void main(String[] args) {
        ListNode node = ListNode.of(3, 5, 0, 2, 6, -2, 6, -2);
        System.out.println((sortList(node).toString()));
    }

    public static ListNode sortList(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;
        PriorityQueue<ListNode> nodeQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        ListNode p = head;
        while (p != null) {
            nodeQueue.add(p);
            p = p.next;
        }
        ListNode first = nodeQueue.poll(), second;
        ListNode dumb = first;
        do {
            second = nodeQueue.poll();
            assert second != null;
            second.next = null;
            first.next = second;
            first = first.next;
            second = nodeQueue.peek();
        } while (second != null);
        return dumb;
    }
}

class Solution_148 {
    // 双指针折中归并
}