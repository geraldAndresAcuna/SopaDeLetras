package SopaLetras;

import java.io.*;

public class sopaCL {
    static String[][] sopaPalabras = new String[2][2];

    static String[][] sopaLetras = new String[10][10];
    final static char[] ALFABETOMINUSCULA = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
    final static char[] ALFABETOMAYUSCULA = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'Ñ',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

    static void llenarSopaPalabras(int contador, String palabra) throws IOException {
        if (validarMatrizPalabras(palabra) == -1) {
            sopaPalabras[0][contador] = palabra;
        }
    }

    static void llenarGuionesSopaPalabras(int contador) throws IOException {
        sopaPalabras[1][contador] = "-";
    }

    static int validarMatrizPalabras(String nombre) throws IOException {
        int existe = -1;
        for (int i = 0; i < 2; i++) {
            if (sopaPalabras[0][i] != null) {
                if (sopaPalabras[0][i].equals(nombre)) {
                    return i;
                }
            }
        }
        return existe;
    }

    static void limpiarMatriz() throws IOException {
        for (int i = 0; i < sopaPalabras.length; i++) {
            for (int j = 0; j < sopaPalabras[i].length; j++) {
                sopaPalabras[i][j] = "";
            }

        }
    }

    static void marcarPalabraEncontrada(int posicion) throws IOException {
        sopaPalabras[1][posicion] = "x";
    }

}
