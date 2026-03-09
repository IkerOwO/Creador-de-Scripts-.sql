package main.java;
import main.java.crearScript.CrearScript;
import java.util.Scanner;
import java.io.*;

public class Main {
    public static void getData(){
        Scanner s = new Scanner(System.in);
        // NOMBRE BBDD
        System.out.print("Nombre de la Base de datos: ");
        String nombreBD = s.nextLine();

        // TABLAS
        System.out.print("Numero de tablas a crear: ");
        int numTablas = s.nextInt();
        // CREAMOS LOS ARRAYS PARA LOS NOMBRES Y LOS CAMPOS
        String[] nombreTablas = new String[numTablas];
        String[] campos = new String[numTablas];
        for(int i = 0; i < numTablas; i++){
            // NOMBRES DE LAS TABLAS
            System.out.println("Nombre de la tabla numero " + (i+1) + ": ");
            nombreTablas[i] = s.nextLine();

            // CAMPOS
            System.out.println("Campos que va a tener la tabla (Nombre, tipo y cantidad separados con espacios) " + nombreTablas[i]);
            campos[i] = s.nextLine().toLowerCase();
            System.out.println("La tabla " + nombreTablas[i] + " va a contener estos campos:\n" + campos[i] + "\n¿Es correcto?: ");
            String confirmacion = s.nextLine();

            if(!confirmacion.equalsIgnoreCase("si")){
                System.out.println("Campos que va a tener la tabla " + nombreTablas[i]);
                campos[i] = s.nextLine().toLowerCase();
            }
        }
        // MANDAR DATOS
        sendData(nombreBD, nombreTablas, campos);
    }

    public static void sendData(String nombreDB, String[] nombreTablas, String[] campos){
        CrearScript.scriptCompleto(nombreDB, nombreTablas, campos);
    }

    public static void main(String[] args){
        System.out.println("## CREADOR DE SCRIPTS .sql ##");
        getData();
    }
}
