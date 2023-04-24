import java.io.*;

public class SopaUI {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static PrintStream out = System.out;
    static int configuracion = 0;
    public static void main(String[] args) throws IOException {
        int opcion = -1;
        do {
            opcion = menuPrincipal();
            menuJuego(opcion);
        } while (opcion != 0);
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
        SopaCL.llenarMatrizBlanco();
        int fila, columna, opcion, contador = 0;
        String palabra;
        if (configuracion < 1) {
            imprimirTextoLn("Para configurar el juego debe de ingresar 6 palabras validas");
            do {
                do {
                    imprimirTextoLn("Ingrese la palabra " + (contador + 1) + ": ");
                    palabra = leerTexto().toLowerCase();
                    do {
                        opcion = menuDirecciones();
                        fila = ingresarFila();
                        columna = ingresarColumna();
                        if (!SopaCL.validacionDireccionLetras(palabra, opcion, fila, columna)) {
                            imprimirTextoLn(
                                    "La palabra excede el tamaño permitido de acuerdo a la posición dentro de la matriz, o el espacio se encuentra ocupado.");
                        }
                    } while (!SopaCL.validacionDireccionLetras(palabra, opcion, fila, columna));
                    if (SopaCL.validarMatrizPalabras(palabra) != -1) {
                        imprimirTextoLn("La palabra ya se encuentra dentros de la Sopa");
                    }
                } while (SopaCL.validarMatrizPalabras(palabra) != -1);
                SopaCL.opciones[contador] = opcion;
                SopaCL.filas[contador] = fila;
                SopaCL.columnas[contador] = columna;
                SopaCL.llenarSopaPalabras(contador, palabra);
                SopaCL.llenarGuionesSopaPalabras(contador);
                SopaCL.ingresarPalabra(opcion, palabra, fila, columna);
                contador++;
            } while (contador < 3);
            imprimirSopa();
            imprimirTextoLn("La configuracion fue realizada exitosamente");
            configuracion++;
        } else {
            imprimirTextoLn("El juego ya esta configurado, elija la opcion 2 para iniciar el juego");
        }
    }

    static void jugar() throws IOException {
        int fila, columna, direccion, posicion = 0;
        if (configuracion > 0) {
            String palabra;
            boolean validarDireccion;
            do {
                imprimirTextoLn("Ingrese la palabra encontrada");
                palabra = leerTexto().toLowerCase();
                posicion = SopaCL.validarMatrizPalabras(palabra);
                if (posicion < 0) {
                    imprimirTextoLn(" La palabra no se encuentra en la sopa, intente nuevamente");
                }
            } while (posicion < 0);
            do {
                direccion = menuDirecciones();
                fila = ingresarFila();
                columna = ingresarColumna();
                validarDireccion = SopaCL.validarDireccionPalabra(direccion, palabra, fila, columna);
                if (!validarDireccion) {
                    imprimirTexto("Las coordenadas ingresadas no son correctas");
                }
            } while (!validarDireccion);
            SopaCL.cambiarPalabraEncontrada(direccion, palabra, fila, columna);
            imprimirSopa();
        }
        if (configuracion == 0) {
            imprimirTextoLn("Debe de configurar el juego previamente en la opción 1.");
        }
        SopaCL.marcarPalabraEncontrada(posicion);
        mensajeGanador(SopaCL.ganarJuego());
    }

    static void reiniciar()  {
        if (configuracion > 0) {
            String palabra;
            int opcion, fila, columna;
            for (int i = 0; i < 2; i++) {
                palabra = SopaCL.sopaPalabras[0][i];
                opcion = SopaCL.opciones[i];
                fila = SopaCL.filas[i];
                columna = SopaCL.columnas[i];
                SopaCL.ingresarPalabra(opcion, palabra, fila, columna);
                SopaCL.llenarGuionesSopaPalabras(i);
                SopaCL.llenarSopaPalabras(i, palabra);
            }
            imprimirSopa();
            configuracion++;
        }
        if (configuracion == 0) {
            imprimirTexto("Debe de configurar el juego previamente");
        }
    }

    static String leerTexto() throws IOException {
        return in.readLine();
    }

    static void imprimirTextoLn(String msj) {
        out.println(msj);
    }

    static void imprimirTexto(String msj) {
        out.print(msj);
    }

    static int menuPrincipal() throws IOException {
        int opcion;
        do {
            imprimirTexto("");
            imprimirTextoLn("\033[97m Sopa de letras \n" +
                    "MENU PRINCIPAL\n" +
                    "1) Configurar\n" +
                    "2) Jugar\n" +
                    "3) Reiniciar\n" +
                    "0) Salir");
            opcion = Integer.parseInt(leerTexto());
            if (opcion < -1 || opcion >= 4) {
                imprimirTextoLn("Opción invalida");
            }
        } while (opcion <= -1 || opcion >= 5);
        return opcion;
    }

    static int menuDirecciones() throws IOException {
        int direccion;
        do {
            imprimirTextoLn("Ingrese la dirección de la palabra: \n" +
                    "1) Derecha a izquierda\n" +
                    "2) Izquierda a derecha\n" +
                    "3) Arriba a abajo\n" +
                    "4) Abajo a arriba\n" +
                    "5) Diagonal Izquierda\n"+
                    "6) Diagonal derecha");
            direccion = Integer.parseInt(leerTexto());
            if (direccion <= 0 || direccion >= 7) {
                imprimirTextoLn("Opción invalida");
            }
        } while (direccion <= 0 || direccion >= 7);
        return direccion;
    }

    static int ingresarFila() throws IOException {
        int fila;
        do {
            imprimirTextoLn("Ingrese la coordenada de la primera letra de la palabra: \n" +
                    "ingrese la fila: ");
            fila = Integer.parseInt(leerTexto());
            if (fila <= 0 || fila >= 10) {
                imprimirTextoLn("Opción invalida");
            }
        } while (fila <= 0 || fila >= 10);
        return fila;
    }

    static int ingresarColumna() throws IOException {
        int columna;
        do {
            imprimirTextoLn("ingrese la columna: ");
            columna = Integer.parseInt(leerTexto());
            if (columna <= 0 || columna >= 10) {
                imprimirTextoLn("Opción invalida");
            }
        } while (columna <= 0 || columna >= 10);
        return columna;
    }

    static void imprimirSopa() {
        SopaCL.numeracionFila();
        SopaCL.numeracionColumna();
        SopaCL.llenarMatrizAleatorio();
        for (int i = 0; i < SopaCL.sopaLetras.length; i++) {
            for (int j = 0; j < SopaCL.sopaLetras[i].length; j++) {
                imprimirTexto("" + SopaCL.sopaLetras[i][j]);
            }
            imprimirTextoLn("");
        }
    }

    static void mensajeGanador(int contador) {
        if (SopaCL.ganarJuego() == 2) {
            imprimirTexto("\n" +
                    "▒█░▒█ █▀▀ ▀▀█▀▀ █▀▀ █▀▀▄ 　 █▀▀▀ █▀▀█ █▀▀▄ █▀▀█ 　 █▀▀ █░░ 　 ░░▀ █░░█ █▀▀ █▀▀▀ █▀▀█\n" +
                    "▒█░▒█ ▀▀█ ░░█░░ █▀▀ █░░█ 　 █░▀█ █▄▄█ █░░█ █░░█ 　 █▀▀ █░░ 　 ░░█ █░░█ █▀▀ █░▀█ █░░█\n" +
                    "░▀▄▄▀ ▀▀▀ ░░▀░░ ▀▀▀ ▀▀▀░ 　 ▀▀▀▀ ▀░░▀ ▀░░▀ ▀▀▀▀ 　 ▀▀▀ ▀▀▀ 　 █▄█ ░▀▀▀ ▀▀▀ ▀▀▀▀ ▀▀▀▀\n" +
                    "\n" +
                    "▒█▀▀▀ █▀▀ █░░ ░▀░ █▀▀ ░▀░ █▀▀▄ █▀▀█ █▀▀▄ █▀▀ █▀▀\n" +
                    "▒█▀▀▀ █▀▀ █░░ ▀█▀ █░░ ▀█▀ █░░█ █▄▄█ █░░█ █▀▀ ▀▀█\n" +
                    "▒█░░░ ▀▀▀ ▀▀▀ ▀▀▀ ▀▀▀ ▀▀▀ ▀▀▀░ ▀░░▀ ▀▀▀░ ▀▀▀ ▀▀▀\n");
            configuracion = 0;
            SopaCL.limpiarMatriz();
            SopaCL.limpiarSopa();
        }
    }
}
