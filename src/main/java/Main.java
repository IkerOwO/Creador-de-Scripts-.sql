package main.java;
//import main.java.customExcepcion.CustomException;
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
        String[] nombreTablas = null;
        for(int i = 0; i<=numTablas; i++){
            System.out.println("Nombre de la tabla numero "+i+": ");
            nombreTablas = new String[]{s.nextLine()};
        }


        // MANDAR DATOS
        sendData(nombreBD, nombreTablas);
    }


    public static void sendData(String nombreDB, String[] nombreTablas){
        CrearScript.scriptCompleto(nombreDB, nombreTablas);
    }

    public static void main(String[] args){
        System.out.println("## CREADOR DE SCRIPTS .sql ##");
        getData();
    }
}
