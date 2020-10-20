import java.util.Stack;

public class Question_24 {
    public static ListNode swapPairs(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;

        var pointerOriginal = head;
        var oddStack = new Stack<ListNode>();
        var evenStack = new Stack<ListNode>();

        var index = 1;
        do {
            if (index % 2 == 0) evenStack.push(pointerOriginal);
            else oddStack.push(pointerOriginal);
            pointerOriginal = pointerOriginal.next;
            index++;
        } while (pointerOriginal != null);

        var oddOne = oddStack.size() > evenStack.size() ? oddStack.pop() : null;
        ListNode lastEven = null;
        do {
            var odd = oddStack.pop();
            var even = evenStack.pop();
            odd.next = null;
            even.next = odd;
            if (lastEven != null) odd.next = lastEven;
            else even.next.next = oddOne;
            lastEven = even;
            if (evenStack.isEmpty()) return lastEven;
        } while (true);
    }

    public static void main(String[] args) {
        System.out.println(swapPairs(
                ListNode.of(1, 2, 3, 4, 5, 6, 7)
        ));
    }
}
