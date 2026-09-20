package org.LeetCode.ZigzagConversion;


class Solution {
    public String convert(String s, int numRows) {
        // Caso base: si solo hay 1 fila o la palabra es más corta que las filas,
        // devolvemos el string original sin procesar.
        if (numRows <= 1 || s.length() <= numRows) {
            return s;
        }
        //Array de filas
        StringBuilder[] filas = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            filas[i] = new StringBuilder();
        }

        int filaActual = 0;
        boolean bajando = false; // Controla la dirección del rebote

        // Recorremos la cadena carácter por carácter
        for (char c : s.toCharArray()) {
            filas[filaActual].append(c); // Añadimos la letra a su fila correspondiente

            // Al llegar a la Fila 0 o a la última fila, invertimos la dirección
            if (filaActual == 0 || filaActual == numRows - 1) {
                bajando = !bajando;
            }

            // Cambiamos de fila según la dirección actual
            filaActual += bajando ? 1 : -1;
        }

        // Unimos el contenido de todos los StringBuilder en un único String final
        StringBuilder resultado = new StringBuilder();
        for (StringBuilder fila : filas) {
            resultado.append(fila);
        }

        return resultado.toString();
    }
}