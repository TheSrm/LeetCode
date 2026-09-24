package org.LeetCode.LastStoneWeigth;

import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    int lastStoneWeight(int[] stones) {
        // Crear una cola de prioridad que mantenga los elementos de mayor a menor
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int stone : stones) {
            maxHeap.add(stone);
        }


        while (maxHeap.size() > 1) {
            int y = maxHeap.poll();
            int x = maxHeap.poll();

            if (y != x) {
                maxHeap.add(y - x);
            }
        }

        return maxHeap.isEmpty() ? 0 : maxHeap.peek();
    }
}