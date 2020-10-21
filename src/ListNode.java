public class ListNode {
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

    public static ListNode of(int... values) {
        ListNode head = new ListNode();
        ListNode pointer = head;
        for (int i = 0; i < values.length; i++) {
            pointer.val = values[i];
            if (i != values.length - 1) pointer = pointer.next = new ListNode();
        }
        return head;
    }

    public ListNode getHead() {
        return this;
    }

    public ListNode getEnd() {
        ListNode p = this;
        while (p.next != null) p = p.next;
        return p;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        ListNode pointer = this;
        for (var i = 0; pointer != null; i++) {
//            sb.append("\t[").append(i).append("]:\t").append(pointer.val).append("\n");
            sb.append("[").append(pointer.val).append("]->");
            pointer = pointer.next;
        }
        sb.replace(sb.length() - 2, sb.length(), "");
        return sb.toString();
    }
}
