package main.java;
//import main.java.customExcepcion.CustomException;
import main.java.Objects.Objects;
import java.util.Scanner;
import java.io.*;

public class Main {
    public static void getData(){
        Objects objetos = new Objects();
        Scanner s = new Scanner(System.in);
        System.out.print("Nombre de la Base de datos: ");
        objetos.nombreBBDD = s.nextLine();
    }

    public static void scriptCompleto() throws IOException {
        // CREAMOS EL SCRIPT .sql CON EL NOMBRE DE LA BBDD
        FileWriter file = new FileWriter(Objects.nombreBBDD+".sql");

    }

    public static void main(String[] args){
        System.out.println("## CREADOR DE SCRIPTS .sql ##");
        getData();
    }
}
