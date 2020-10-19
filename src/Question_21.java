public class Question_21 {
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
        System.out.println(mergeTwoLists(
                ListNode.of(1, 3, 6, 9),
                ListNode.of(2, 3, 5, 8,33,56,78)
        ));
    }
}
