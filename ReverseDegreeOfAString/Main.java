package ReverseDegreeOfAString;

class Solution {
    public int reverseDegree(String s) {
        int resultado = 0;

        for (int i = 0; i < s.length(); i++) {
            char letra = s.charAt(i);
            int posicion = letra - 'a' + 1;
            int valorInverso = 27 - posicion;

            resultado += valorInverso * (i + 1);
        }

        return resultado;
    }
}
