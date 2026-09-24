package org.LeetCode.IntegertoRoman;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

class Solution {

    public static Map<Integer, String> mapa = new LinkedHashMap<>();

    static {
        mapa.put(1000, "M");
        mapa.put(900, "CM");
        mapa.put(500, "D");
        mapa.put(400, "CD");
        mapa.put(100, "C");
        mapa.put(90, "XC");
        mapa.put(50, "L");
        mapa.put(40, "XL");
        mapa.put(10, "X");
        mapa.put(9, "IX");
        mapa.put(5, "V");
        mapa.put(4, "IV");
        mapa.put(1, "I");

        mapa = Collections.unmodifiableMap(mapa);
    }

    public String intToRoman(int num) {
        StringBuilder resultado = new StringBuilder();

        for (Map.Entry<Integer, String> entrada : mapa.entrySet()) {
            int valor = entrada.getKey();
            String simbolo = entrada.getValue();

            while (num >= valor) {
                resultado.append(simbolo);
                num -= valor;
            }
        }

        return resultado.toString();
    }

    public int romanToInt(String s) {
        int resultado = 0;

        for (Map.Entry<Integer, String> entrada : mapa.entrySet()) {
            String simbolo = String.valueOf(entrada.getKey());
            int valor = Integer.parseInt(entrada.getValue());

            while (s.startsWith(simbolo)) {
                resultado += valor;
                s = s.substring(simbolo.length());

                // Atajo: si ya consumimos toda la cadena, salimos del bucle
                if (s.isEmpty()) {
                    break;
                }
            }

            return resultado;
        }

        return resultado;
    }
}