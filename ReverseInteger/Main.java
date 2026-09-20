package org.LeetCode.ReverseInteger;

/* Podría mejorarse tanto en tiempo como en ejecución*/

class Solution {
    public static boolean alInvertirSuperaLimite(int numero) {

        // 1. Caso especial de Integer.MIN_VALUE (-2147483648)
        if (numero == Integer.MIN_VALUE) {
            return true;
        }

        boolean esNegativo = false;
        if (numero < 0) {
            esNegativo = true;
        }
        int numeroPositivo = numero;
        if (esNegativo == true) {
            numeroPositivo = -numero;
        }

        // 4. Convertimos a String e invertimos el texto
        String textoOriginal = String.valueOf(numeroPositivo);
        StringBuilder sb = new StringBuilder(textoOriginal);
        String textoInvertido = sb.reverse().toString();

        // 5. Si tiene menos de 10 cifras, cabe seguro en un int
        int cantidadDeCifras = textoInvertido.length();
        if (cantidadDeCifras < 10) {
            return false;
        }

        char primerCaracter = textoInvertido.charAt(0);

        // Si empieza por '3', '4', '5'..., supera los 3.000.000.000
        if (primerCaracter > '2') {
            return true;
        }

        // Si empieza por '1', cabe seguro
        if (primerCaracter < '2') {
            return false;
        }

        // 7. Si empieza por '2', definimos el límite según el signo
        String limiteTexto = "2147483647";
        if (esNegativo == true) {
            limiteTexto = "2147483648";
        }

        int comparacion = textoInvertido.compareTo(limiteTexto);
        if (comparacion > 0) {
            return true; // Se pasa del límite
        } else {
            return false; // Cabe dentro del límite
        }
    }
    public static int reverse(int numero) {

        if (alInvertirSuperaLimite(numero)) {
            System.out.println("No se puede invertir: supera el límite de un int");
            return 0;
        }

        // 2. Guardamos el signo
        boolean esNegativo = numero < 0;

        int positivo = esNegativo ? -numero : numero;
        String texto = String.valueOf(positivo);

        String textoInvertido = new StringBuilder(texto).reverse().toString();

        int numeroInvertido = Integer.parseInt(textoInvertido);

        if (esNegativo) {
            numeroInvertido = -numeroInvertido;
        }

        return numeroInvertido;
    }


    }
