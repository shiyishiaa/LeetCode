public class Question_25 {
    public static ListNode reverseKGroup(ListNode head, int k) {
        var ret = new ListNode(0, head);
        var start = ret.next;
        var end = ret.next;
        var retEnd = ret;
        all:
        while (end != null) {
            for (var i = 0; i < k - 1; i++) {
                end = end.next;
                if (end == null)
                    break all;
            }
            retEnd.next = null;
            var temp = end.next;
            reverseGroup(start, end);

            start.next = temp;
            retEnd.next = end;

            retEnd = start;
            start = start.next;
            end = start;
        }

        return ret.next;
    }

    public static void reverseGroup(ListNode head, ListNode end) {
        if (head.equals(end)) return;

        var nowNode = head;
        ListNode lastNode = null;
        do {
            var tempNode = nowNode.next;
            nowNode.next = lastNode;
            lastNode = nowNode;
            nowNode = tempNode;
        } while (!nowNode.equals(end));
        nowNode.next = lastNode;
    }

    public static void main(String[] args) {
        ListNode node = ListNode.of(1, 2, 3, 4, 5, 6, 7, 8);
        System.out.println(reverseKGroup(node, 3));
    }
}
