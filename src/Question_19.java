import java.util.ArrayDeque;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class Question_19 {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next == null) return null;

        var nodes = new ArrayDeque<ListNode>();
        ListNode pointer = head;
        do {
            nodes.push(pointer);
            pointer = pointer.next;
        } while (pointer != null);
        ListNode breakPoint = new ListNode();
        for (var i = 0; i <= n; i++) {
            breakPoint = nodes.peekLast();
            if (breakPoint != null)
                breakPoint = nodes.pop();
            else
                return head.next;
        }
        breakPoint.next = breakPoint.next.next;
        return head;
    }

    public static void main(String[] args) {
    }
}

/**
 * 快慢指针
 * @see <a href="https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/solution/shan-chu-lian-biao-de-dao-shu-di-nge-jie-dian-b-61/">LeetCode 19</a>
 */
class Solution_19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

}
