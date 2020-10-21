public class Question_206 {
    public static ListNode reverseList(ListNode head) {
        if (head == null) return null;

        var nowNode = head;
        ListNode lastNode = null;
        do {
            var tempNode = nowNode.next;
            nowNode.next = lastNode;
            lastNode = nowNode;
            nowNode = tempNode;
        } while (nowNode != null);

        return lastNode;
    }

    public static void main(String[] args) {
        System.out.println(reverseList(ListNode.of(1, 2, 3, 4, 5, 6, 7, 8)));
    }
}
