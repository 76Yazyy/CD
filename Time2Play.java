

import java.util.ArrayList;
import java.util.Scanner;

/**
 *\
 * |--> @author Pelayo
 */

public class Time2Play {

    static final String NOMBRE = "Pelayo";
    static final String CONTRASENA = "1234";

    static class Viaje {
        String fecha;
        String hora;
        String origen;
        String destino;
        int plazas;

        Viaje(String fecha, String hora, String origen, String destino, int plazas) {
            this.fecha = fecha;
            this.hora = hora;
            this.origen = origen;
            this.destino = destino;
            this.plazas = plazas;
        }
    }

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Viaje> viajes = new ArrayList<>();

    public static void main(String[] args) {
        iniciarSesion();

        int opcion;
        do {
            System.out.println("\nElige alguna opción:");
            System.out.println("1. Quiero crear un viaje");
            System.out.println("2. Quiero apuntarme a un viaje");
            System.out.println("3. Salir");
            opcion = leerEntero("Opción: ");

            switch (opcion) {
                case 1:
                    crearViaje();
                    break;
                case 2:
                    apuntarseViaje();
                    break;
                case 3:
                    System.out.println("¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 3);
    }

    static void iniciarSesion() {
        String nombre;
        String contrasena;

        System.out.print("Nombre: ");
        nombre = sc.nextLine();
        System.out.print("Contraseña: ");
        contrasena = sc.nextLine();

     if (!nombre.equals(NOMBRE) || !contrasena.equals(CONTRASENA)) {
        System.out.println("Nombre o Contraseña incorrectos");
        System.exit(0);
     } System.out.println("Sesión Iniciada. Bienvenido " +  NOMBRE);
    }

    static void crearViaje() {
        System.out.print("Fecha del viaje (dd/mm/aaaa): ");
        String fecha = sc.nextLine();
        System.out.print("Hora del viaje (hh:mm): ");
        String hora = sc.nextLine();
        System.out.print("Origen: ");
        String origen = sc.nextLine();
        System.out.print("Destino: ");
        String destino = sc.nextLine();
    
        int plazas = leerEntero("Cantidad de pasajeros: ");

        System.out.print("¿Confirmar viaje? (s/n): ");
        String confirmar = sc.nextLine();

        if (confirmar.equalsIgnoreCase("s")) {
            viajes.add(new Viaje(fecha, hora, origen, destino, plazas));
            System.out.println("Viaje creado.");
        } else {
            System.out.println("Viaje cancelado.");
        }

    }

    static void apuntarseViaje() {
        System.out.print("Fecha (dd/mm/aaaa): ");
        String fecha = sc.nextLine();
        System.out.print("Origen: ");
        String origen = sc.nextLine();
        System.out.print("Destino: ");
        String destino = sc.nextLine();
        int pasajeros = leerEntero("Cantidad de pasajeros: ");

        Viaje encontrado = null;
        for (Viaje v : viajes) {
            if (v.fecha.equals(fecha)
                    && v.origen.equalsIgnoreCase(origen)
                    && v.destino.equalsIgnoreCase(destino)
                    && v.plazas >= pasajeros) {
                encontrado = v;
                break;
            }
        }

        if (encontrado == null) {
            System.out.println("No hay ningún viaje disponible.");
        } else {
            System.out.print("Hay un viaje el día " + encontrado.fecha + " a las "
                    + encontrado.hora + ", ¿desea apuntarse? (s/n): ");
            String respuesta = sc.nextLine();
            if (respuesta.equalsIgnoreCase("s")) {
                encontrado.plazas -= pasajeros;
                System.out.println("Te has apuntado al viaje.");
            } else {
                System.out.println("No te has apuntado.");
            }
        }
    }

    static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número válido.");
            }
        }
    }
}

