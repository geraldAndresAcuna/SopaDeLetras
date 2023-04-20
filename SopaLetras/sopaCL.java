package SopaLetras;

import java.io.*;

public class sopaCL {
    static String[][] sopaLetras = new String[10][10];
    final static char[] ALFABETOMINUSCULA = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
    final static char[] ALFABETOMAYUSCULA = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'Ñ',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

    static char pasarMayuscula(char letra) {
        int posicion = 0;
        for (int i = 0; i < ALFABETOMINUSCULA.length; i++) {
            if (Character.toString(letra).matches(String.valueOf(ALFABETOMINUSCULA[i]))) {
                posicion = i;
            }
        }
        return ALFABETOMAYUSCULA[posicion];
    }

}
