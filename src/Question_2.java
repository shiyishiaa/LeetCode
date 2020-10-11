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
public class Question_2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode sum;
        if (l1 != null || l2 != null)
            if (l1 == null) {
                sum = new ListNode(l2.val);
                sum.next = l2.next;
                return sum;
            } else if (l2 == null) {
                sum = new ListNode(l1.val);
                sum.next = l1.next;
                return sum;
            } else {
                boolean hasCarry = (l1.val + l2.val) >= 10;
                sum = new ListNode(hasCarry ? l1.val + l2.val - 10 : l1.val + l2.val);
                sum.next = hasCarry ?
                        addTwoNumbers(new ListNode(1), addTwoNumbers(l1.next, l2.next)) :
                        addTwoNumbers(l1.next, l2.next);
                return sum;
            }
        return null;
    }
}


class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution_2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }
}
