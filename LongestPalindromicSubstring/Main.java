package org.LeetCode.LongestPalindromicSubstring;


class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        String longest = "";

        // Para cada posición posible como centro
        for (int i = 0; i < s.length(); i++) {
            // Caso 1: Centro en UN carácter (palíndromos impares: "aba")
            String p1 = expandAroundCenter(s, i, i);

            // Caso 2: Centro ENTRE dos caracteres (palíndromos pares: "abba")
            String p2 = expandAroundCenter(s, i, i + 1);

            // Guardamos el más largo
            String current = (p1.length() > p2.length()) ? p1 : p2;
            if (current.length() > longest.length()) {
                longest = current;
            }
        }

        return longest;
    }

    private String expandAroundCenter(String s, int left, int right) {
        // Expandimos mientras:
        // 1. left >= 0 (no salimos del borde izquierdo)
        // 2. right < s.length() (no salimos del borde derecho)
        // 3. Los caracteres son iguales
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;   // Expandir a la izquierda
            right++;  // Expandir a la derecha
        }

        // Cuando el while termina, left y right están "fuera"
        // Así que retornamos substring(left+1, right)
        return s.substring(left + 1, right);
    }

    public boolean isPalindrome(String s){
        if (s.length()<=1){
            return true;
        }
        char i,d;
        i=s.charAt(0);
        d=s.charAt(s.length()-1);

        if (i==d){
            return isPalindrome(s.substring(1,s.length()-1));
        }else{
            return false;
        }
    }

}