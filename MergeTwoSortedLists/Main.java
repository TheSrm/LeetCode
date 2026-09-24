package org.LeetCode.MergeTwoSortedLists;

import org.LeetCode.AddTwoNumbers.ListNode;

class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Centinela (dummy) para simplificar el manejo de la cabeza de la lista
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // Comparamos nodo a nodo mientras ambas listas tengan elementos
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        // Conectamos los nodos restantes de la lista que no se haya vaciado
        current.next = (list1 != null) ? list1 : list2;

        return dummy.next;
    }
}