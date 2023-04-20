package SopaLetras;

import java.io.*;

public class sopa {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static PrintStream out = System.out;
    static String[][] sopaLetras = new String[10][10];
    static String[][] sopaPalabras = new String[2][2];
    static int configuracion;
    static int[] opciones = new int[2];
    static int[] filas = new int[2];
    static int[] columnas = new int[2];
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

    static int menuPrincipal() throws IOException {
        int opcion;
        do {
            imprimirTexto("");
            imprimirTexto("\033[97m Sopa de letras \n" +
                    "MENU PRINCIPAL\n" +
                    "1) Configurar\n" +
                    "2) Jugar\n" +
                    "3) Reiniciar\n" +
                    "0) Salir");
            opcion = Integer.parseInt(leerTexto());
            if (opcion < -1 || opcion >= 4) {
                imprimirTexto("Opción invalida");
            }
        } while (opcion <= -1 || opcion >= 5);
        return opcion;
    }

    static int menuDirecciones() throws IOException {
        int direccion;
        do {
            imprimirTexto("Ingrese la dirección de la palabra: \n" +
                    "1) Derecha a izquierda\n" +
                    "2) Izquierda a derecha\n" +
                    "3) Arriba a abajo\n" +
                    "4) Abajo a arriba");
            direccion = Integer.parseInt(leerTexto());
            if (direccion <= 0 || direccion >= 5) {
                imprimirTexto("Opción invalida");
            }
        } while (direccion <= 0 || direccion >= 5);
        return direccion;
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

    static boolean validarVerticalInvertido(String palabra, int columna, int fila) throws IOException {
        boolean valido;
        int limite;
        limite = sopaLetras.length - fila;
        if ((palabra.length() < limite && columna > 0 && fila > 0 && fila > 10)
                && palabraEnSopaVerticalInvertido(palabra, fila, columna) == true) {
            valido = true;
        } else {
            valido = false;
        }
        return valido;
    }

    static boolean palabraEnSopaVerticalInvertido(String palabra, int fila, int columna) throws IOException {
        boolean bandera = false;
        int contador = 0;
        int k = 0;
        for (int i = (palabra.length() - 1); i >= 0; i--) {
            char letra = palabra.charAt(k);
            if (sopaLetras[fila + k][columna] == null || sopaLetras[fila + k][columna].equals(letra)) {
                contador++;
            }
            k++;
        }
        if (contador == palabra.length()) {
            bandera = true;
        } else {
            bandera = false;
        }
        imprimirTexto("" + bandera);
        return bandera;
    }

    static boolean validarVertical(String palabra, int columna, int fila)
            throws IOException {
        boolean valido;
        int limite = sopaLetras.length - fila;
        if ((palabra.length() < limite && fila >= 0 && columna >= 0 && columna < 10)
                && sobreEscribirAbajo(palabra, fila, columna) == true) {
            valido = true;
        } else {
            valido = false;
        }
        return valido;
    }

    static boolean validarHorizontal(String palabra, int columna, int fila)
            throws IOException {
        boolean valido;
        int limite;
        limite = sopaLetras.length - columna;
        if ((palabra.length() < limite && columna >= 0 && fila >= 0 && fila < 10)
                && sobreEscribirIzquierda(palabra, fila, columna) == true) {
            valido = true;
        } else {
            valido = false;
        }
        return valido;
    }

    static boolean sobreEscribirAbajo(String palabra, int fila, int columna) throws IOException {
        boolean bandera = false;
        int contador = 0;
        for (int i = 0; i < palabra.length(); i++) {
            if (sopaLetras[fila + i][columna] == null) {
                contador++;
            }
        }
        if (contador == palabra.length()) {
            bandera = true;
        } else {
            bandera = false;
        }
        return bandera;
    }

    static boolean sobreEscribirIzquierda(String palabra, int fila, int columna) throws IOException {
        boolean bandera = false;
        int contador = 0;
        for (int i = 0; i < palabra.length(); i++) {
            if (sopaLetras[fila][columna + i] == null) {
                contador++;
            }
        }
        if (contador == palabra.length()) {
            bandera = true;
        } else {
            bandera = false;
        }
        return bandera;
    }

    static boolean validarHorizontalInvertida(String palabra, int columna, int fila) {
        boolean valido;
        int limite;
        limite = sopaLetras.length - columna;
        if (limite < palabra.length() || columna <= 0 || fila <= 0 || fila >= 10) {
            valido = false;
        } else {
            valido = true;
        }
        return valido;
    }

    static boolean validacionDireccionLetras(String palabra, int direccion, int fila, int columna) throws IOException {
        boolean resultado = false;
        switch (direccion) {
            case 1:
                resultado = validarHorizontal(palabra, columna, fila);
                break;
            case 2:
                resultado = validarHorizontalInvertida(palabra, columna, fila);
                break;
            case 3:
                resultado = validarVertical(palabra, columna, fila);
                break;
            case 4:
                resultado = validarVerticalInvertido(palabra, columna, fila);
                break;
            default:
                break;
        }
        return resultado;
    }

    static void imprimirMatrizPalabras() throws IOException {
        for (int j = 0; j < sopaPalabras.length; j++) {
            for (int i = 0; i < sopaPalabras[j].length; i++) {
                out.print(sopaPalabras[j][i]);
            }
            out.println("");
        }
    }

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

    static void ganarJuego() throws IOException {
        int contador = 0;
        for (int i = 0; i < sopaPalabras.length; i++) {
            if (sopaPalabras[1][i] == "x") {
                contador++;
            }
        }
        if (contador == 2) {
            imprimirTexto("\n" +
                    "▒█░▒█ █▀▀ ▀▀█▀▀ █▀▀ █▀▀▄ 　 █▀▀▀ █▀▀█ █▀▀▄ █▀▀█ 　 █▀▀ █░░ 　 ░░▀ █░░█ █▀▀ █▀▀▀ █▀▀█\n" +
                    "▒█░▒█ ▀▀█ ░░█░░ █▀▀ █░░█ 　 █░▀█ █▄▄█ █░░█ █░░█ 　 █▀▀ █░░ 　 ░░█ █░░█ █▀▀ █░▀█ █░░█\n" +
                    "░▀▄▄▀ ▀▀▀ ░░▀░░ ▀▀▀ ▀▀▀░ 　 ▀▀▀▀ ▀░░▀ ▀░░▀ ▀▀▀▀ 　 ▀▀▀ ▀▀▀ 　 █▄█ ░▀▀▀ ▀▀▀ ▀▀▀▀ ▀▀▀▀\n" +
                    "\n" +
                    "▒█▀▀▀ █▀▀ █░░ ░▀░ █▀▀ ░▀░ █▀▀▄ █▀▀█ █▀▀▄ █▀▀ █▀▀\n" +
                    "▒█▀▀▀ █▀▀ █░░ ▀█▀ █░░ ▀█▀ █░░█ █▄▄█ █░░█ █▀▀ ▀▀█\n" +
                    "▒█░░░ ▀▀▀ ▀▀▀ ▀▀▀ ▀▀▀ ▀▀▀ ▀▀▀░ ▀░░▀ ▀▀▀░ ▀▀▀ ▀▀▀\n");
            configuracion = 0;
            limpiarMatriz();
            limpiarSopa();
        }
    }

    static void limpiarSopa() throws IOException {
        for (int j = 0; j < sopaLetras.length; j++) {
            for (int i = 0; i < sopaLetras[j].length; i++) {
                sopaLetras[j][i] = null;
            }
        }
    }

    static void menuJuego(int opcion) throws IOException {
        switch (opcion) {
            case 1:
                configurar();
                break;
            case 2:
                jugar();
                break;
            case 3:
                reiniciar();
                break;
            default:
                break;
        }
    }

    static void configurar() throws IOException {
        int fila, columna, opcion, contador = 0;
        String palabra;
        if (configuracion < 1) {
            imprimirTexto("Para configurar el juego debe de ingresar 6 palabras validas");
            do {
                do {
                    imprimirTexto("Ingrese la palabra " + (contador + 1) + ": ");
                    palabra = leerTexto().toLowerCase();
                    do {
                        opcion = menuDirecciones();
                        fila = ingresarFila();
                        columna = ingresarColumna();
                        if (validacionDireccionLetras(palabra, opcion, fila, columna) == false) {
                            imprimirTexto(
                                    "La palabra excede el tamaño permitido de acuerdo a la posición dentro de la matriz, o el espacio se encuentra ocupado.");
                        }
                    } while (validacionDireccionLetras(palabra, opcion, fila, columna) == false);
                    if (validarMatrizPalabras(palabra) != -1) {
                        imprimirTexto("La palabra ya se encuentra dentros de la sopa");
                    }
                } while (validarMatrizPalabras(palabra) != -1);
                opciones[contador] = opcion;
                filas[contador] = fila;
                columnas[contador] = columna;
                llenarSopaPalabras(contador, palabra);
                llenarGuionesSopaPalabras(contador);
                ingresarPalabra(opcion, palabra, fila, columna);
                contador++;
            } while (contador < 2);
            llenarSopa();
            imprimirSopa();
            imprimirTexto("\n" + "La configuracion fue realizada exitosamente");
            configuracion++;
        } else {
            imprimirTexto("El juego ya esta configurado, elija la opción 2 para jugar");
        }
    }

    static void jugar() throws IOException {
        int fila, columna, direccion, posicion = 0;
        if (configuracion > 0) {
            String palabra;
            boolean validarDireccion;
            do {
                imprimirTexto("Ingrese la palabra encontrada: ");
                palabra = leerTexto().toLowerCase();
                posicion = validarMatrizPalabras(palabra);
                if (posicion < 0) {
                    imprimirTexto("La palabra no se encuentra en la sopa");
                }
            } while (posicion < 0);
            do {
                direccion = menuDirecciones();
                fila = ingresarFila();
                columna = ingresarColumna();
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
        marcarPalabraEncontrada(posicion);
        ganarJuego();
    }

    static void reiniciar() throws IOException {
        if (configuracion > 0) {
            String palabra;
            int opcion, fila, columna;
            for (int i = 0; i < 2; i++) {
                palabra = sopaPalabras[0][i];
                opcion = opciones[i];
                fila = filas[i];
                columna = columnas[i];
                ingresarPalabra(opcion, palabra, fila, columna);
                llenarGuionesSopaPalabras(i);
                llenarSopaPalabras(i, palabra);
            }
            llenarSopa();
            imprimirSopa();
            configuracion++;
        }
        if (configuracion == 0) {
            imprimirTexto("Debe de configurar el juego previamente");
        }
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

    static void posicionesHorizontal() {
        for (int i = 0; i < sopaLetras.length; i++) {
            sopaLetras[0][i] = "" + (i) + " | ";
        }
    }

    static void posicionesVertical() {
        for (int i = 0; i < sopaLetras.length; i++) {
            sopaLetras[i][0] = " | " + (i) + (" | ");
        }
    }

    static void ingresarPalabra(int opcion, String palabra, int fila, int columna) throws IOException {
        switch (opcion) {
            case 1:
                haciaLaIzquierda(palabra, fila, columna);
                break;
            case 2:
                haciaLaDerecha(palabra, fila, columna);
                break;
            case 3:
                haciaAbajo(palabra, fila, columna);
                break;
            case 4:
                haciaArriba(palabra, fila, columna);
                break;
            default:
                break;
        }
    }

    static int ingresarFila() throws IOException {
        int fila;
        do {
            imprimirTexto("Ingrese la coordenada de la primera letra de la palabra: \n" +
                    "ingrese la fila: ");
            fila = Integer.parseInt(leerTexto());
            if (fila <= 0 || fila >= 10) {
                imprimirTexto("Opción invalida");
            }
        } while (fila <= 0 || fila >= 10);
        return fila;
    }

    static int ingresarColumna() throws IOException {
        int columna;
        do {
            imprimirTexto("ingrese la columna: ");
            columna = Integer.parseInt(leerTexto());
            if (columna <= 0 || columna >= 10) {
                imprimirTexto("Opción invalida");
            }
        } while (columna <= 0 || columna >= 10);
        return columna;
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

    static void palabraEncontradaHaciaAbajo(String palabra, int fila, int columna) throws IOException {
        for (int i = 0; i < palabra.length(); i++) {
            cambioPalabraVerticalAbajo(palabra, fila, columna);
        }
        imprimirSopa();
    }

    static void palabraEncontradaHaciaArriba(String palabra, int fila, int columna) throws IOException {
        for (int i = 0; i < palabra.length(); i++) {
            cambioPalabraVerticalArriba(palabra, fila, columna);
        }
        imprimirSopa();
    }

    static void palabraEncontradaHaciaLaDerecha(String palabra, int fila, int columna) throws IOException {
        for (int i = (palabra.length() - 1); i >= 0; i--) {
            cambioPalabraHorizontalDerecha(palabra, fila, columna);
        }
        imprimirSopa();
    }

    static void palabraEncontradaHaciaLaIzquierda(String palabra, int fila, int columna) throws IOException {
        for (int i = 0; i < palabra.length(); i++) {
            cambioPalabraHorizontalIzquierda(palabra, fila, columna);
        }
        imprimirSopa();
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
        int opcion = -1;
        do {
            opcion = menuPrincipal();
            menuJuego(opcion);
        } while (opcion != 0);
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

    static void cambioPalabraHorizontalIzquierda(String palabra, int fila, int columna) {
        char letra, letraMayuscula;
        for (int i = 0; i < palabra.length(); i++) {
            letra = palabra.charAt(i);
            letraMayuscula = sopaCL.pasarMayuscula(letra);
            sopaLetras[fila][columna + i] = "\033[31m" + letraMayuscula + "\033[97m | ";
        }
    }

    static void cambioPalabraHorizontalDerecha(String palabra, int fila, int columna) {
        char letra, letraMayuscula;
        int k = 0;
        for (int i = (palabra.length() - 1); i >= 0; i--) {
            letra = palabra.charAt(i);
            letraMayuscula = sopaCL.pasarMayuscula(letra);
            sopaLetras[fila][columna + k] = "\033[31m" + letraMayuscula + "\033[97m | ";
            k++;
        }
    }

    static void cambioPalabraVerticalAbajo(String palabra, int fila, int columna) {
        char letra, letraMayuscula;
        for (int i = 0; i < palabra.length(); i++) {
            letra = palabra.charAt(i);
            letraMayuscula = sopaCL.pasarMayuscula(letra);
            sopaLetras[fila + i][columna] = "\033[31m" + letraMayuscula + "\033[97m | ";
        }
    }

    static void cambioPalabraVerticalArriba(String palabra, int fila, int columna) {
        int k = 0;
        char letra, letraMayuscula;
        for (int i = (palabra.length() - 1); i >= 0; i--) {
            letra = palabra.charAt(i);
            letraMayuscula = sopaCL.pasarMayuscula(letra);
            sopaLetras[fila + k][columna] = "\033[31m" + letraMayuscula + "\033[97m | ";
            k++;
        }
    }

    static boolean palabraEncontradaHaciaLaDerechaValidacion(String palabra, int fila, int columna) {
        int contador = 0;
        boolean validada;
        String palabraCreada = "", palabraIngresada = "", palabraMayuscula = "";
        int k = 0;
        for (int i = (palabra.length() - 1); i >= 0; i--) {
            palabraIngresada = palabraIngresada + "" + palabra.charAt(i) + "\033[97m | ";
            palabraCreada = palabraCreada + sopaLetras[fila][columna + k];
            palabraMayuscula = palabraMayuscula + "" + sopaCL.pasarMayuscula(palabra.charAt(k)) + "\033[97m | ";
            k++;
            if (palabraIngresada.charAt(i) == palabraCreada.charAt(i)
                    || palabraIngresada.charAt(i) == palabraMayuscula.charAt(i)) {
                contador++;
            }
        }
        if (contador == palabra.length()) {
            validada = true;
        } else {
            validada = false;
        }
        return validada;
    }

    static boolean palabraEncontradaHaciaAbajoValidacion(String palabra, int fila, int columna) {
        int contador = 0;
        char letra;
        boolean validada;
        String palabraCreada = "", palabraIngresada = "", palabraMayuscula = "";
        for (int i = 0; i < palabra.length(); i++) {
            palabraIngresada = palabraIngresada + "" + palabra.charAt(i) + "\033[97m | ";
            palabraCreada = palabraCreada + sopaLetras[fila + i][columna];
            letra = palabra.charAt(i);
            palabraMayuscula = palabraMayuscula + "" + sopaCL.pasarMayuscula(letra) + "\033[97m | ";
            if (palabraIngresada.charAt(i) == palabraCreada.charAt(i)
                    || palabraIngresada.charAt(i) == palabraMayuscula.charAt(i)) {
                contador++;
            }
        }
        if (contador == palabra.length()) {
            validada = true;
        } else {
            validada = false;
        }
        return validada;
    }

    static boolean palabraEncontradaHaciaLaIzquierdaValidacion(String palabra, int fila, int columna) {
        int contador = 0;
        boolean validada;
        String palabraCreada = "", palabraIngresada = "", palabraMayuscula = "";
        for (int i = 0; i < palabra.length(); i++) {
            palabraIngresada = palabraIngresada + "" + palabra.charAt(i) + "\033[97m | ";
            palabraCreada = palabraCreada + sopaLetras[fila][columna + i];
            palabraMayuscula = palabraMayuscula + "" + sopaCL.pasarMayuscula(palabra.charAt(i)) + "\033[97m | ";
            if (palabraIngresada.charAt(i) == palabraCreada.charAt(i)
                    || palabraIngresada.charAt(i) == palabraMayuscula.charAt(i)) {
                contador++;
            }
        }
        if (contador == palabra.length()) {
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

    static void imprimirSopa() throws IOException {
        for (int i = 0; i < sopaLetras.length; i++) {
            for (int j = 0; j < sopaLetras.length; j++) {
                out.print(sopaLetras[i][j]);
            }
            out.println("");
        }
        llenarSopa();
    }

}