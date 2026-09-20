package org.LeetCode.MedianOfTwoSortedArrays;

class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int[] merged = mergeSorted(nums1, nums2);
            int n = merged.length;

            // Buscar mediana
            if (n % 2 == 1) {
                return (double) merged[n / 2];
            } else {
                int mid1 = merged[n / 2 - 1];
                int mid2 = merged[n / 2];
                return (mid1 + mid2) / 2.0;
            }
        }

        private int[] mergeSorted(int[] arr1, int[] arr2) {
            int[] result = new int[arr1.length + arr2.length];
            int i = 0, j = 0, k = 0;

            // Comparar y copiar
            while (i < arr1.length && j < arr2.length) {
                if (arr1[i] <= arr2[j]) {
                    result[k++] = arr1[i++];
                } else {
                    result[k++] = arr2[j++];
                }
            }
            //Cuando nos quedemos sin un arrray, el resto son elementos del otro seguro
            while (i < arr1.length) {
                result[k++] = arr1[i++];
            }
            while (j < arr2.length) {
                result[k++] = arr2[j++];
            }

            return result;
        }
}