package SopaLetras;

import java.io.*;

public class sopa {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static PrintStream out = System.out;
    static String[][] sopaLetras = new String[10][10];
    static String[][] sopaPalabraReinicio = new String[10][10];
    static String[][] sopaPalabraReinicioPrueba = new String[10][10];
    static int configuracion;
    static String[][] sopaPalabras = new String[10][10];
    final static char[] ALFABETOMINUSCULA = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
    final static char[] ALFABETOMAYUSCULA = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'Ñ',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

    static void imprimirTexto(String msj) {
        out.println(msj);
    }

    static String leerTexto() throws IOException {
        return in.readLine();
    }

    static void posicionesHorizontal() {
        for (int i = 0; i < sopaLetras.length; i++) {
            sopaLetras[0][i] = "" + (i + 1 - 1) + " | ";
        }
    }

    static void posicionesVertical() {
        for (int i = 0; i < sopaLetras.length; i++) {
            sopaLetras[i][0] = " | " + (i + 1 - 1) + (" | ");
        }
    }

    static void haciaLaIzquierda(String palabra, int filas, int columnas) throws IOException {
        for (int i = 0; i < palabra.length(); i++) {
            sopaLetras[filas][columnas + i] = "" + palabra.charAt(i) + "\033[97m | ";
        }
    }

    static void haciaLaDerecha(String palabra, int filas, int columnas) throws IOException {
        int k = 0;
        for (int i = (palabra.length() - 1); i >= 0; i--) {
            sopaLetras[filas][columnas + k] = "" + palabra.charAt(i) + "\033[97m | ";
            k++;
        }
    }

    static void haciaAbajo(String palabra, int filas, int columnas) throws IOException {
        for (int i = 0; i < palabra.length(); i++) {
            sopaLetras[filas + i][columnas] = "" + palabra.charAt(i) + "\033[97m | ";
        }
    }

    static void haciaArriba(String palabra, int filas, int columnas) throws IOException {
        int k = 0;
        for (int i = (palabra.length() - 1); i >= 0; i--) {
            sopaLetras[filas + k][columnas] = "" + palabra.charAt(i) + "\033[97m | ";
            k++;
        }
    }

    static boolean validarVertical(String palabra, int columna, int fila, String[][] sopa) {
        boolean valido;
        int limite = sopa.length - fila;
        if (palabra.length() > limite || fila <= 0 || columna <= 0 || columna >= 10) {
            valido = false;
        } else {
            valido = true;
        }
        return valido;
    }

    static boolean validarVerticalInvertido(String palabra, int columna, int fila, String[][] sopa) {
        boolean valido;
        int limite;
        limite = sopa.length - fila;
        if (limite < palabra.length() || columna <= 0 || fila <= 0 || fila >= 10) {
            valido = false;
        } else {
            valido = true;
        }
        return valido;
    }

    static boolean validarHorizontal(String palabra, int columna, int fila, String[][] sopa) {
        boolean valido;
        int limite;
        limite = sopa.length - columna;
        if (palabra.length() > limite || columna <= 0 || fila <= 0 || fila >= 10) {
            valido = false;
        } else {
            valido = true;
        }
        return valido;
    }

    static boolean validarHorizontalInvertida(String palabra, int columna, int fila, String[][] sopa) {
        boolean valido;
        int limite;
        limite = sopa.length - columna;
        if (limite < palabra.length() || columna <= 0 || fila <= 0 || fila >= 10) {
            valido = false;
        } else {
            valido = true;
        }
        return valido;
    }

    static boolean validacionDireccionLetras(String palabra, int direccion, int fila, int columna) {
        boolean resultado = false;
        switch (direccion) {
            case 1:
                resultado = validarHorizontal(palabra, columna, fila, sopaLetras);
                break;
            case 2:
                resultado = validarHorizontalInvertida(palabra, columna, fila, sopaLetras);
                break;
            case 3:
                resultado = validarVertical(palabra, columna, fila, sopaLetras);
                break;
            case 4:
                resultado = validarVerticalInvertido(palabra, columna, fila, sopaLetras);
                break;
            default:
                break;
        }
        return !resultado;
    }

    static void llenarSopa() throws IOException {
        posicionesHorizontal();
        posicionesVertical();
        for (int i = 0; i < sopaLetras.length; i++) {
            for (int j = 0; j < sopaLetras.length; j++) {
                if (sopaLetras[i][j] == null) {
                    int numero = (int) (Math.random() * 26 + 1);
                    char valor = ALFABETOMINUSCULA[numero];
                    sopaLetras[i][j] = "\033[97m" + valor + "\033[97m | ";
                }
            }
        }
    }

    static void imprimirSopa(String[][] sopa) throws IOException {
        for (int i = 0; i < sopa.length; i++) {
            for (int j = 0; j < sopa.length; j++) {
                out.print(sopa[i][j]);
            }
            out.println("");
        }
        llenarSopa();
    }

    static void menuPrincipal(int opcion) throws IOException {
        switch (opcion) {
            case 1:
                configurar();
                break;
            case 2:
                jugar();
                break;
            case 3:
                break;
            default:
                break;
        }
    }

    static void configurar() throws IOException {
        int fila, columna, opcion, contador = 0;
        String palabra;
        imprimirTexto("Para configurar el juego debe de ingresar 6 palabras validas");
        do {
            do {
                imprimirTexto("Ingrese la palabra " + (contador + 1) + ": ");
                palabra = leerTexto().toLowerCase();
                ;
                imprimirTexto("Ingrese la direccion en la que desea ingresar la palabra: \n" +
                        "1) Derecha a izquierda\n" +
                        "2) Izquierda a derecha\n" +
                        "3) Arriba a abajo\n" +
                        "4) Abajo a arriba");
                imprimirTexto("Seleccione una opción");
                opcion = Integer.parseInt(leerTexto());
                imprimirTexto("Ingrese la fila en donde empezara la palabra: ");
                fila = Integer.parseInt(leerTexto());
                imprimirTexto("Ingrese la columna en la que va a estar la palabra: ");
                columna = Integer.parseInt(leerTexto());
                if (validacionDireccionLetras(palabra, opcion, fila, columna)) {
                    imprimirTexto(
                            "La palabra excede el tamaño permitido de acuerdo a la posición dentro de la matriz");
                }
            } while (validacionDireccionLetras(palabra, opcion, fila, columna));
            switch (opcion) {
                case 1:
                    haciaLaIzquierda(palabra, fila, columna);
                    imprimirSopa(sopaLetras);
                    imprimirSopa(sopaPalabras);
                    break;
                case 2:
                    haciaLaDerecha(palabra, fila, columna);
                    imprimirSopa(sopaLetras);
                    imprimirSopa(sopaPalabras);
                    break;
                case 3:
                    haciaAbajo(palabra, fila, columna);
                    imprimirSopa(sopaLetras);
                    imprimirSopa(sopaPalabras);
                    break;
                case 4:
                    haciaArriba(palabra, fila, columna);
                    imprimirSopa(sopaLetras);
                    imprimirSopa(sopaPalabras);
                    break;
                default:
                    break;
            }
            contador++;
        } while (contador < 2);
        llenarSopa();
        imprimirSopa(sopaLetras);
        sopaPalabraReinicio = sopaLetras;
        sopaPalabraReinicioPrueba = sopaPalabraReinicio;
        imprimirTexto("\n" + "La configuracion fue realizada exitosamente");
        configuracion++;
    }

    static boolean validarPalabra(String palabra) {
        boolean encontrada = false;
        for (int i = 0; i < sopaPalabras.length; i++) {
            if (sopaPalabras[i].equals(palabra)) {
                encontrada = true;
            } else {
                encontrada = false;
            }
        }
        return encontrada;
    }

    static char pasarMayuscula(char letra) {
        int posicion = 0;
        for (int i = 0; i < ALFABETOMINUSCULA.length; i++) {
            if (Character.toString(letra).matches(String.valueOf(ALFABETOMINUSCULA[i]))) {
                posicion = i;
            }
        }
        return ALFABETOMAYUSCULA[posicion];
    }

    static void cambioPalabraHorizontalIzquierda(String palabra, int fila, int columna) {
        char letra, letraMayuscula;
        for (int i = 0; i < palabra.length(); i++) {
            letra = palabra.charAt(i);
            letraMayuscula = pasarMayuscula(letra);
            sopaLetras[fila][columna + i] = "\033[31m" + letraMayuscula + "\033[97m | ";
        }
    }

    static void cambioPalabraHorizontalDerecha(String palabra, int fila, int columna) {
        char letra, letraMayuscula;
        int k = 0;
        for (int i = (palabra.length() - 1); i >= 0; i--) {
            letra = palabra.charAt(i);
            letraMayuscula = pasarMayuscula(letra);
            sopaLetras[fila][columna + k] = "\033[31m" + letraMayuscula + "\033[97m | ";
            k++;
        }
    }

    static void cambioPalabraVerticalAbajo(String palabra, int fila, int columna) {
        char letra, letraMayuscula;
        for (int i = 0; i < palabra.length(); i++) {
            letra = palabra.charAt(i);
            letraMayuscula = pasarMayuscula(letra);
            sopaLetras[fila + i][columna] = "\033[31m" + letraMayuscula + "\033[97m | ";
        }
    }

    static void cambioPalabraVerticalArriba(String palabra, int fila, int columna) {
        int k = 0;
        char letra, letraMayuscula;
        for (int i = (palabra.length() - 1); i >= 0; i--) {
            letra = palabra.charAt(i);
            letraMayuscula = pasarMayuscula(letra);
            sopaLetras[fila + k][columna] = "\033[31m" + letraMayuscula + "\033[97m | ";
            k++;
        }
    }

    static void palabraEncontradaHaciaAbajo(String palabra, int fila, int columna) throws IOException {
        for (int i = 0; i < palabra.length(); i++) {
            cambioPalabraVerticalAbajo(palabra, fila, columna);
        }
        imprimirSopa(sopaLetras);
    }

    static void palabraEncontradaHaciaArriba(String palabra, int fila, int columna) throws IOException {
        int k = 0;
        for (int i = 0; i < palabra.length(); i++) {
            cambioPalabraVerticalArriba(palabra, fila, columna);
            k++;
        }
        imprimirSopa(sopaLetras);
    }

    static void palabraEncontradaHaciaLaDerecha(String palabra, int fila, int columna) throws IOException {
        int k = 0;
        for (int i = (palabra.length() - 1); i >= 0; i--) {
            cambioPalabraHorizontalDerecha(palabra, fila, columna);
            k++;
        }
        imprimirSopa(sopaLetras);
    }

    static void palabraEncontradaHaciaLaIzquierda(String palabra, int fila, int columna) throws IOException {
        for (int i = 0; i < palabra.length(); i++) {
            cambioPalabraHorizontalIzquierda(palabra, fila, columna);
        }
        imprimirSopa(sopaLetras);
    }

    static boolean palabraEncontradaHaciaLaIzquierdaValidacion(String palabra, int fila, int columna) {
        boolean validada;
        String palabraCreada = "", palabraIngresada = "";
        for (int i = 0; i < palabra.length(); i++) {
            palabraIngresada = palabraIngresada + "" + palabra.charAt(i) + "\033[97m | ";
            palabraCreada = palabraCreada + sopaLetras[fila][columna + i];
        }
        if (palabraCreada.equals(palabraIngresada)) {
            validada = true;
        } else {
            validada = false;
        }
        return validada;
    }

    static boolean palabraEncontradaHaciaLaDerechaValidacion(String palabra, int fila, int columna) {
        boolean validada;
        String palabraCreada = "", palabraIngresada = "";
        int k = 0;
        for (int i = (palabra.length() - 1); i >= 0; i--) {
            palabraIngresada = palabraIngresada + "" + palabra.charAt(i) + "\033[97m | ";
            palabraCreada = palabraCreada + sopaLetras[fila][columna + k];
            k++;
        }
        if (palabraCreada.equals(palabraIngresada)) {
            validada = true;
        } else {
            validada = false;
        }
        return validada;
    }

    static boolean palabraEncontradaHaciaAbajoValidacion(String palabra, int fila, int columna) {
        boolean validada;
        String palabraCreada = "", palabraIngresada = "";
        for (int i = 0; i < palabra.length(); i++) {
            palabraIngresada = palabraIngresada + "" + palabra.charAt(i) + "\033[97m | ";
            palabraCreada = palabraCreada + sopaLetras[fila + i][columna];
        }
        if (palabraCreada.equals(palabraIngresada)) {
            validada = true;
        } else {
            validada = false;
        }
        return validada;
    }

    static boolean palabraEncontradaHaciaArribaValidacion(String palabra, int fila, int columna) {
        int k = 0;
        boolean validada;
        String palabraCreada = "", palabraIngresada = "";
        for (int i = (palabra.length() - 1); i >= 0; i--) {
            palabraIngresada = palabraIngresada + "" + palabra.charAt(i) + "\033[97m | ";
            palabraCreada = palabraCreada + sopaLetras[fila + k][columna];
            k++;
        }
        if (palabraCreada.equals(palabraIngresada)) {
            validada = true;
        } else {
            validada = false;
        }
        return validada;
    }

    static void jugar() throws IOException {
        if (configuracion > 0) {
            int fila, columna, direccion;
            String palabra;
            boolean validarDireccion;
            do {
                imprimirTexto("Ingrese la palabra encontrada: ");
                palabra = leerTexto().toLowerCase();
                imprimirTexto("Ingrese la dirección de la palabra encontrada : \n" +
                        "1) Derecha a izquierda\n" +
                        "2) Izquierda a derecha\n" +
                        "3) Arriba a abajo\n" +
                        "4) Abajo a arriba");
                direccion = Integer.parseInt(leerTexto());
                imprimirTexto("Ingrese la coordenada de la primera letra de la palabra: \n" +
                        "ingrese la fila: ");
                fila = Integer.parseInt(leerTexto());
                imprimirTexto("Ingrese la columna: ");
                columna = Integer.parseInt(leerTexto());
                validarDireccion = validarDireccionPalabra(direccion, palabra, fila, columna);
                if (!validarDireccion) {
                    imprimirTexto("Las coordenadas ingresadas no son correctas");
                }
            } while (!validarDireccion);
            validarPalabraEncontrada(direccion, palabra, fila, columna);
        }
        if (configuracion == 0) {
            imprimirTexto("Debe de configurar el juego previamente");
        }
    }

    static boolean validarDireccionPalabra(int direccion, String palabra, int fila, int columna) {
        boolean resultado = false;
        switch (direccion) {
            case 1:
                resultado = palabraEncontradaHaciaLaIzquierdaValidacion(palabra, fila, columna);
                break;
            case 2:
                resultado = palabraEncontradaHaciaLaDerechaValidacion(palabra, fila, columna);
                break;
            case 3:
                resultado = palabraEncontradaHaciaAbajoValidacion(palabra, fila, columna);
                break;
            case 4:
                resultado = palabraEncontradaHaciaArribaValidacion(palabra, fila, columna);
                break;
            default:
                break;
        }
        return resultado;
    }

    static void validarPalabraEncontrada(int direccion, String palabra, int fila, int columna) throws IOException {
        switch (direccion) {
            case 1:
                palabraEncontradaHaciaLaIzquierda(palabra, fila, columna);
                break;
            case 2:
                palabraEncontradaHaciaLaDerecha(palabra, fila, columna);
                break;
            case 3:
                palabraEncontradaHaciaAbajo(palabra, fila, columna);
                break;
            case 4:
                palabraEncontradaHaciaArriba(palabra, fila, columna);
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) throws IOException {
        int elecion = -1;
        do {
            imprimirTexto("");
            imprimirTexto("\033[97m Sopa de letras \n" +
                    "MENU PRINCIPAL\n" +
                    "1) Configurar\n" +
                    "2) Jugar\n" +
                    "3) Reiniciar\n" +
                    "0) Salir");
            elecion = Integer.parseInt(leerTexto());
            menuPrincipal(elecion);
        } while (elecion != 0);
        imprimirSopa(sopaPalabraReinicioPrueba);
        imprimirSopa(sopaLetras);
        imprimirSopa(sopaPalabraReinicio);
    }
}