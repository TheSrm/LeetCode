package org.LeetCode.ValidParentheses;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Map;

class Solution {
    private static final Map<Character, Character> matches = Map.of(
            '}', '{',
            ']', '[',
            ')', '('
    );

    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (matches.containsValue(c)) {  // Opening bracket
                stack.push(c);
            } else if (matches.containsKey(c)) {  // Closing bracket
                if (stack.isEmpty() || stack.pop() != matches.get(c)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}

class Solution2 {

    // Usamos List de Character en lugar de String
    List<Character> listaAbertura = List.of('{', '[', '(');
    List<Character> listaClausura = List.of('}', ']', ')');

    public boolean isValid(String s) {
        // Cambiamos Queue por Deque para usar comportamiento de PILA
        Deque<Character> pila = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // Si es un símbolo de apertura, lo guardamos en la cima de la pila
            if (listaAbertura.contains(c)) {
                pila.push(c);
            }
            // Si es un símbolo de cierre
             if (listaClausura.contains(c)) {
                // Si encontramos un cierre pero la pila está vacía, no es válido
                if (pila.isEmpty()) {
                    return false;
                }

                // Sacamos el ÚLTIMO símbolo de apertura guardado
                char ultimoApertura = pila.pop();

                // Verificamos si los índices coinciden en las listas
                // Ej: '{' está en el índice 0 de apertura y '}' en el índice 0 de clausura
                int indiceCierre = listaClausura.indexOf(c);
                int indiceApertura = listaAbertura.indexOf(ultimoApertura);

                if (indiceCierre != indiceApertura) {
                    return false;
                }
            }
        }

        // Si al final la pila quedó vacía, todos los paréntesis se cerraron bien
        return pila.isEmpty();
    }
}