package org.LeetCode.LongestCommonPrefix;

class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        String primeraPalabra = strs[0];

        for (int i = 0; i < primeraPalabra.length(); i++) {
            char c = primeraPalabra.charAt(i);

            for (int j = 1; j < strs.length; j++) {

                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return primeraPalabra.substring(0, i);
                }
            }
        }

        return primeraPalabra;
    }
}