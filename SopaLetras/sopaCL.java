package SopaLetras;

public class SopaCL {

        static String[][] sopaLetras = new String[10][10];
        static String[][] sopaPalabras = new String[2][2];
        static int[] opciones = new int[2];
        static int[] filas = new int[2];
        static int[] columnas = new int[2];
        final static char[] ALFABETOMINUSCULA = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ',
                'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
        final static char[] ALFABETOMAYUSCULA = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'Ñ',
                'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        // genera los numeros en la fila de 0 al 9
        static void numeracionFila() {
                for (int i = 0; i < sopaLetras.length; i++) {
                        sopaLetras[i][0] = String.valueOf("" + "\033[97m" + i + "\033[97m | ");
                }
        }

        // genera los numeros en la columna de 0 al 9
        static void numeracionColumna() {
                for (int i = 0; i < sopaLetras.length; i++) {
                        sopaLetras[0][i] = String.valueOf("" + "\033[97m" + i + "\033[97m | ");
                }
        }

        // llena todos los espacios de la matriz con un espacio en blanco y un | , es
        // para poder comparar a la hora de ingresar la palabra
        // si esta con un espacio en blanco y un |, ingrese la palabra ya que si se
        // compara con null da error
        static void llenarMatrizBlanco() {
                for (int i = 1; i < sopaLetras.length; i++) {
                        for (int j = 0; j < sopaLetras.length; j++) {
                                sopaLetras[i][j] = "\033[97m" + " " + "\033[97m | ";
                        }
                }
        }

        // despues de ingresar las palabras en la matriz todos los espacios que queden
        // con un espacio en blanco y un |, (espacio libre)
        // llenar la matriz con los valores random
        static void llenarMatrizAleatorio() {
                for (int i = 1; i < sopaLetras.length; i++) {
                        for (int j = 0; j < sopaLetras.length; j++) {
                                if (sopaLetras[i][j].equals("\033[97m" + " " + "\033[97m | ")) {
                                        int numero = (int) (Math.random() * 26 + 1);
                                        char valor = ALFABETOMINUSCULA[numero];
                                        sopaLetras[i][j] = "\033[97m" + valor + "\033[97m | ";
                                }
                        }
                }
        }

        // ingresar la palabra en la matriz hacia la derecha
        static void haciaLaDerecha(String palabra, int filas, int columnas) {
                int k = 0;
                for (int i = (palabra.length() - 1); i >= 0; i--) {
                        sopaLetras[filas][columnas + k] = "" + palabra.charAt(i) + "\033[97m | ";
                        k++;
                }
        }

        // ingresar la palabra en la matriz hacia la izquierda
        static void haciaLaIzquierda(String palabra, int filas, int columnas) {
                for (int i = 0; i < palabra.length(); i++) {
                        sopaLetras[filas][columnas + i] = "" + palabra.charAt(i) + "\033[97m | ";
                }
        }

        // ingresar la palabra en la matriz hacia abajo
        static void haciaAbajo(String palabra, int filas, int columnas) {
                for (int i = 0; i < palabra.length(); i++) {
                        sopaLetras[filas + i][columnas] = "" + palabra.charAt(i) + "\033[97m | ";
                }
        }

        // ingresar la palabra en la matriz hacia arriba
        static void haciaArriba(String palabra, int filas, int columnas) {
                int k = 0;
                for (int i = (palabra.length() - 1); i >= 0; i--) {
                        sopaLetras[filas + k][columnas] = "" + palabra.charAt(i) + "\033[97m | ";
                        k++;
                }
        }

        // ingresar la palabra en la matriz hacia la izquierda diagonal hacia abajo
        static void haciaLaIzquierdaDiagonal(String palabra, int filas, int columnas) {
                for (int i = 0; i < palabra.length(); i++) {
                        sopaLetras[filas + i][columnas + i] = "" + palabra.charAt(i) + "\033[97m | ";
                }
        }

        // En realidad es la misma diagonal hacia la izquierda y abajo pero con el orden
        // de la letras invertidas
        static void haciaLaDerechaDiagonal(String palabra, int filas, int columnas) {
                int k = 0;
                for (int i = (palabra.length() - 1); i >= 0; i--) {
                        sopaLetras[filas + k][columnas + k] = "" + palabra.charAt(i) + "\033[97m | ";
                        k++;
                }
        }

        // en el menu de direcciones se elije una opcion para el ingreso de la palabra
        static void ingresarPalabra(int opcion, String palabra, int fila, int columna) {
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
                        case 5:
                                haciaLaIzquierdaDiagonal(palabra, fila, columna);
                                break;
                        case 6:
                                haciaLaDerechaDiagonal(palabra, fila, columna);
                                break;
                        default:
                                break;
                }
        }

        // las rutinas de sobre escribir van a validar si en el espacio que se quiere
        // ingresar la letra el espacio esta vacio o si esta la misma letra que se desea
        // ingresa
        // si se cumple cualquiera de las dos se aumenta el contador y si el contador es
        // == al length de la palabra el resultado sera true
        static boolean sobreEscribirDerecha(String palabra, int fila, int columna) {
                boolean bandera = false;
                int contador = 0, k = 0;
                for (int i = (palabra.length() - 1); i >= 0; i--) {
                        if (sopaLetras[fila][columna + k].equals("\033[97m" + " " + "\033[97m | ")
                                || sopaLetras[fila][columna + k].equals(palabra.charAt(i) + "\033[97m | ")) {
                                contador++;
                        }
                        k++;
                }
                if (contador == palabra.length()) {
                        bandera = true;
                }
                return bandera;
        }

        static boolean sobreEscribirDerechaDiagonal(String palabra, int fila, int columna) {
                boolean bandera = false;
                int contador = 0, k = 0;
                for (int i = (palabra.length() - 1); i >= 0; i--) {
                        if (sopaLetras[fila][columna + k].equals("\033[97m" + " " + "\033[97m | ")
                                || sopaLetras[fila + k][columna + k].equals(palabra.charAt(i) + "\033[97m | ")) {
                                contador++;
                        }
                        k++;
                }
                if (contador == palabra.length()) {
                        bandera = true;
                }
                return bandera;
        }

        static boolean sobreEscribirArriba(String palabra, int fila, int columna) {
                boolean bandera = false;
                int contador = 0;
                int k = 0;
                for (int i = (palabra.length() - 1); i >= 0; i--) {
                        if (sopaLetras[fila + k][columna].equals("\033[97m" + " " + "\033[97m | ")
                                || sopaLetras[fila + k][columna].equals(palabra.charAt(i) + "\033[97m | ")) {
                                contador++;
                        }
                        k++;
                }
                if (contador == palabra.length()) {
                        bandera = true;
                }
                return bandera;
        }

        static boolean sobreEscribirAbajo(String palabra, int fila, int columna) {
                boolean bandera = false;
                int contador = 0;
                for (int i = 0; i < palabra.length(); i++) {
                        if (sopaLetras[fila + i][columna].equals("\033[97m" + " " + "\033[97m | ")
                                || sopaLetras[fila + i][columna].equals(palabra.charAt(i) + "\033[97m | ")) {
                                contador++;
                        }
                }
                if (contador == palabra.length()) {
                        bandera = true;
                }
                return bandera;
        }

        static boolean sobreEscribirIzquierda(String palabra, int fila, int columna) {
                boolean bandera = false;
                int contador = 0;
                for (int i = 0; i < palabra.length(); i++) {
                        if (sopaLetras[fila][columna + i].equals("\033[97m" + " " + "\033[97m | ")
                                || sopaLetras[fila][columna + i].equals(palabra.charAt(i) + "\033[97m | ")) {
                                contador++;
                        }
                }
                if (contador == palabra.length()) {
                        bandera = true;
                }
                return bandera;
        }

        static boolean sobreEscribirIzquierdaDiagonal(String palabra, int fila, int columna) {
                boolean bandera = false;
                int contador = 0;
                for (int i = 0; i < palabra.length(); i++) {
                        if (sopaLetras[fila + i][columna + i].equals("\033[97m" + " " + "\033[97m | ")
                                || sopaLetras[fila + i][columna + i].equals(palabra.charAt(i) + "\033[97m | ")) {
                                contador++;
                        }
                }
                if (contador == palabra.length()) {
                        bandera = true;
                }
                return bandera;
        }

        // valida el tamaño de la palabra de acuerdo a la fila y columna en la que se
        // desea ingresar, no debe exceder el rango
        static boolean validarLimitesPalabra(String palabra, int columna, int fila) {
                boolean valido = false;
                int limite = sopaLetras.length - fila;
                if ((palabra.length() < limite && fila >= 0 && columna >= 0 && columna < 10 && fila < 10)) {
                        valido = true;
                }
                return valido;
        }

        // valida si cumple con el tamaño de la palabra y los espacios en donde se desea
        // ingresar la palabra combina los juegos de funciones anteriores
        static boolean validarHaciaAbajo(String palabra, int columna, int fila) {
                boolean valido = validarLimitesPalabra(palabra, columna, fila), resultado = false;
                if ((valido) && sobreEscribirAbajo(palabra, fila, columna)) {
                        resultado = true;
                }
                return resultado;
        }

        static boolean validarHaciaArriba(String palabra, int columna, int fila) {
                boolean valido = validarLimitesPalabra(palabra, columna, fila), resultado = false;
                if (valido && sobreEscribirArriba(palabra, fila, columna)) {
                        resultado = true;
                }
                return resultado;
        }

        static boolean validarHaciaLaIzquieda(String palabra, int columna, int fila) {
                boolean valido = validarLimitesPalabra(palabra, columna, fila), resultado = false;
                if (valido && sobreEscribirIzquierda(palabra, fila, columna)) {
                        resultado = true;
                }
                return resultado;
        }

        static boolean validarHaciaLaIzquierdaDiagonal(String palabra, int columna, int fila) {
                boolean valido = validarLimitesPalabra(palabra, columna, fila), resultado = false;
                if (valido && sobreEscribirIzquierdaDiagonal(palabra, fila, columna)) {
                        resultado = true;
                }
                return resultado;
        }

        static boolean validarHaciaLaDerechaDiagonal(String palabra, int columna, int fila) {
                boolean valido = validarLimitesPalabra(palabra, columna, fila), resultado = false;
                if (valido && sobreEscribirDerechaDiagonal(palabra, fila, columna)) {
                        resultado = true;
                }
                return resultado;
        }

        static boolean validarHaciaLaDerecha(String palabra, int columna, int fila) {
                boolean valido = validarLimitesPalabra(palabra, columna, fila), resultado = false;
                if (valido && sobreEscribirDerecha(palabra, fila, columna)) {
                        resultado = true;
                }
                return resultado;
        }

        static boolean validacionDireccionLetras(String palabra, int direccion, int fila, int columna) {
                boolean resultado = false;
                switch (direccion) {
                        case 1:
                                resultado = validarHaciaLaIzquieda(palabra, columna, fila);
                                break;
                        case 2:
                                resultado = validarHaciaLaDerecha(palabra, columna, fila);
                                break;
                        case 3:
                                resultado = validarHaciaAbajo(palabra, columna, fila);
                                break;
                        case 4:
                                resultado = validarHaciaArriba(palabra, columna, fila);
                                break;
                        case 5:
                                resultado = validarHaciaLaIzquierdaDiagonal(palabra, columna, fila);
                                break;
                        case 6:
                                resultado = validarHaciaLaDerechaDiagonal(palabra, columna, fila);
                        default:
                                break;
                }
                return resultado;
        }

        static void llenarSopaPalabras(int contador, String palabra) {
                if (validarMatrizPalabras(palabra) == -1) {
                        sopaPalabras[0][contador] = palabra;
                }
        }

        static void llenarGuionesSopaPalabras(int contador) {
                sopaPalabras[1][contador] = "-";
        }

        static int validarMatrizPalabras(String nombre) {
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

        static void limpiarMatriz() {
                for (int i = 0; i < sopaPalabras.length; i++) {
                        for (int j = 0; j < sopaPalabras[i].length; j++) {
                                sopaPalabras[i][j] = "";
                        }

                }
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

        static boolean palabraEncontradaHaciaLaIzquierdaValidacion(String palabra, int fila, int columna) {
                int contador = 0;
                boolean validada = false;
                String palabraCreada = "\033[31m", palabraIngresada = "\033[31m", palabraMayuscula = "\033[31m";
                for (int i = 0; i < palabra.length(); i++) {
                        palabraIngresada = palabraIngresada + "" + palabra.charAt(i) + "\033[97m | ";
                        palabraCreada = palabraCreada + sopaLetras[fila][columna + i];
                        palabraMayuscula = palabraMayuscula + "" + pasarMayuscula(palabra.charAt(i)) + "\033[97m | ";
                        if (palabraIngresada.charAt(i) == palabraCreada.charAt(i)
                                || palabraIngresada.charAt(i) == palabraMayuscula.charAt(i)) {
                                contador++;
                        }
                }
                if (contador == palabra.length()) {
                        validada = true;
                }
                return validada;
        }

        static boolean palabraEncontradaHaciaLaIzquierdaDiagonalValidacion(String palabra, int fila, int columna) {
                int contador = 0;
                boolean validada = false;
                String palabraCreada = "\033[31m", palabraIngresada = "\033[31m", palabraMayuscula = "\033[31m";
                for (int i = 0; i < palabra.length(); i++) {
                        palabraIngresada = palabraIngresada + "" + palabra.charAt(i) + "\033[97m | ";
                        palabraCreada = palabraCreada + sopaLetras[fila + i][columna + i];
                        palabraMayuscula = palabraMayuscula + "" + pasarMayuscula(palabra.charAt(i)) + "\033[97m | ";
                        palabraIngresada.charAt(i);
                        palabraCreada.charAt(i);
                        palabraMayuscula.charAt(i);
                        if (palabraIngresada.charAt(i) == palabraCreada.charAt(i)
                                || palabraIngresada.charAt(i) == palabraMayuscula.charAt(i)) {
                                contador++;
                        }
                }
                if (contador == palabra.length()) {
                        validada = true;
                }
                return validada;
        }

        static boolean palabraEncontradaHaciaLaDerechaValidacion(String palabra, int fila, int columna) {
                int contador = 0;
                boolean validada = false;
                String palabraCreada = "\033[31m", palabraIngresada = "\033[31m", palabraMayuscula = "\033[31m";
                int k = 0;
                for (int i = (palabra.length() - 1); i >= 0; i--) {
                        palabraIngresada = palabraIngresada + "" + palabra.charAt(i) + "\033[97m | ";
                        palabraCreada = palabraCreada + sopaLetras[fila][columna + k];
                        palabraMayuscula = palabraMayuscula + "" + pasarMayuscula(palabra.charAt(k)) + "\033[97m | ";
                        k++;
                        if (palabraIngresada.charAt(i) == palabraCreada.charAt(i)
                                || palabraIngresada.charAt(i) == palabraMayuscula.charAt(i)) {
                                contador++;
                        }
                }
                if (contador == palabra.length()) {
                        validada = true;
                }
                return validada;
        }

        static boolean palabraEncontradaHaciaLaDerechaValidacionDiagonal(String palabra, int fila, int columna) {
                int contador = 0;
                boolean validada = false;
                String palabraCreada = "\033[31m", palabraIngresada = "\033[31m", palabraMayuscula = "\033[31m";
                int k = 0;
                for (int i = (palabra.length() - 1); i >= 0; i--) {
                        palabraIngresada = palabraIngresada + "" + palabra.charAt(i) + "\033[97m | ";
                        palabraCreada = palabraCreada + sopaLetras[fila + k][columna + k];
                        palabraMayuscula = palabraMayuscula + "" + pasarMayuscula(palabra.charAt(k)) + "\033[97m | ";
                        k++;
                        if (palabraIngresada.charAt(i) == palabraCreada.charAt(i)
                                || palabraIngresada.charAt(i) == palabraMayuscula.charAt(i)) {
                                contador++;
                        }
                }
                if (contador == palabra.length()) {
                        validada = true;
                }
                return validada;
        }

        static boolean palabraEncontradaHaciaAbajoValidacion(String palabra, int fila, int columna) {
                int contador = 0;
                char letra;
                boolean validada;
                String palabraCreada = "\033[31m", palabraIngresada = "\033[31m", palabraMayuscula = "\033[31m";
                for (int i = 0; i < palabra.length(); i++) {
                        palabraIngresada = palabraIngresada + "" + palabra.charAt(i) + "\033[97m | ";
                        palabraCreada = palabraCreada + sopaLetras[fila + i][columna];
                        letra = palabra.charAt(i);
                        palabraMayuscula = palabraMayuscula + "" + pasarMayuscula(letra) + "\033[97m | ";
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

        static void marcarPalabraEncontrada(int posicion) {
                sopaPalabras[1][posicion] = "x";
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
                        case 5:
                                resultado = palabraEncontradaHaciaLaIzquierdaDiagonalValidacion(palabra, fila, columna);
                                break;
                        case 6:
                                resultado = palabraEncontradaHaciaLaDerechaValidacionDiagonal(palabra, fila, columna);
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
                        letraMayuscula = pasarMayuscula(letra);
                        sopaLetras[fila][columna + i] = "\033[31m" + letraMayuscula + "\033[97m | ";
                }
        }

        static void cambioPalabraHorizontalIzquierdaDiagonal(String palabra, int fila, int columna) {
                char letra, letraMayuscula;
                for (int i = 0; i < palabra.length(); i++) {
                        letra = palabra.charAt(i);
                        letraMayuscula = pasarMayuscula(letra);
                        sopaLetras[fila + i][columna + i] = "\033[31m" + letraMayuscula + "\033[97m | ";
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

        static void cambioPalabraHorizontalDerechaDiagonal(String palabra, int fila, int columna) {
                char letra, letraMayuscula;
                int k = 0;
                for (int i = (palabra.length() - 1); i >= 0; i--) {
                        letra = palabra.charAt(i);
                        letraMayuscula = pasarMayuscula(letra);
                        sopaLetras[fila + k][columna + k] = "\033[31m" + letraMayuscula + "\033[97m | ";
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

        static void cambiarPalabraEncontrada(int direccion, String palabra, int fila, int columna) {
                switch (direccion) {
                        case 1:
                                cambioPalabraHorizontalIzquierda(palabra, fila, columna);
                                break;
                        case 2:
                                cambioPalabraHorizontalDerecha(palabra, fila, columna);
                                break;
                        case 3:
                                cambioPalabraVerticalAbajo(palabra, fila, columna);
                                break;
                        case 4:
                                cambioPalabraVerticalArriba(palabra, fila, columna);
                                break;
                        case 5:
                                cambioPalabraHorizontalIzquierdaDiagonal(palabra, fila, columna);
                                break;
                        case 6:
                                cambioPalabraHorizontalDerechaDiagonal(palabra, fila, columna);
                                break;
                        default:
                                break;
                }
        }

        static int ganarJuego() {
                int contador = 0;
                for (int i = 0; i < sopaPalabras.length; i++) {
                        if (sopaPalabras[1][i] == "x") {
                                contador++;
                        }
                }
                return contador;
        }

        static void limpiarSopa() {
                for (int j = 0; j < sopaLetras.length; j++) {
                        for (int i = 0; i < sopaLetras[j].length; i++) {
                                sopaLetras[j][i] = null;
                        }
                }
        }
}
