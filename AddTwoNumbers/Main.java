package org.LeetCode.AddTwoNumbers;

class ListNode {
    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
//Da Overflow con arrays demasiado largos
class SolutionNoValida {

    public long decode(ListNode node, long acc) {
        if (node.next != null) {
            acc = decode(node.next, acc);
        }

        return acc * 10 + node.val;
    }

    public ListNode encode(long number, boolean first) {
        if (number == 0 && !first) {
            return null;
        }

        ListNode node = new ListNode(Math.toIntExact(number % 10));

        if (number / 10 != 0) {
            node.next = encode(number / 10, false);
        }

        return node;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        long number1 = decode(l1, 0);
        long number2 = decode(l2, 0);

        long sum= number2+number1;

        return encode(sum, true);

    }
}

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return add(l1, l2, 0);
    }

    private ListNode add(ListNode l1, ListNode l2, int carry) {

        if (l1 == null && l2 == null && carry == 0) {
            return null;
        }

        int sum = carry;

        if (l1 != null) {
            sum += l1.val;
        }

        if (l2 != null) {
            sum += l2.val;
        }

        ListNode node = new ListNode(sum % 10);

        node.next = add(
                l1 != null ? l1.next : null,
                l2 != null ? l2.next : null,
                sum / 10
        );

        return node;
    }
}
