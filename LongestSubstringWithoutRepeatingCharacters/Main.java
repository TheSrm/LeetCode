package org.LeetCode.LongestSubstringWithoutRepeatingCharacters;


import java.util.HashMap;
import java.util.Map;

class Solution {
    public int lengthOfLongestSubstringBase(String s) {
        String buffer = "";
        int maxLength = 0;

        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);

            if (buffer.contains(String.valueOf(currentChar))) {
                maxLength = Math.max(maxLength, buffer.length());

                int indexOfChar = buffer.indexOf(String.valueOf(currentChar));
                buffer = buffer.substring(indexOfChar + 1);
                System.out.println("Nuevo buffer: " + buffer);
            }

            buffer = buffer.concat(String.valueOf(currentChar));
            System.out.println("Agregado '" + currentChar + "' -> buffer: " + buffer);
        }

        return Math.max(maxLength, buffer.length());
    }


    public int lengthOfLongestSubstring(String s) {
        // HashMap para guardar el último índice de cada carácter
        Map<Character, Integer> charIndex = new HashMap<>();
        int maxLength = 0;
        int left = 0; // Inicio de la ventana deslizante

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // Si el carácter ya existe en la ventana actual
            if (charIndex.containsKey(currentChar)) {
                // Mueve el inicio de la ventana a la derecha del carácter anterior
                left = Math.max(left, charIndex.get(currentChar) + 1);
            }

            // Actualiza el último índice del carácter actual
            charIndex.put(currentChar, right);

            // Actualiza la longitud máxima
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}