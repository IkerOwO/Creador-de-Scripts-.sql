package main.java;
import main.java.crearScript.CrearScript;
import java.util.*;

public class Main {
    public static void getData() {
        Scanner s = new Scanner(System.in);
        // NOMBRE BBDD
        System.out.print("Nombre de la Base de datos: ");
        String nombreBD = s.next();

        // TABLAS
        System.out.print("Numero de tablas a crear: ");
        int numTablas = s.nextInt();
        // CREAMOS EL MAP QUE CONTENDRA LOS NOMBRE DE LAS TABLAS JUNTO CON LOS CAMPOS DE CADA UNA
        Map<String, List<String>> tablas = new HashMap<>();
        for (int i = 0; i < numTablas; i++) {
            // NOMBRES DE LAS TABLAS
            System.out.print("Nombre de la tabla numero " + (i + 1) + ": ");
            String nombreTabla = s.next();

            // CAMPOS
            List<String> listaCampos = new ArrayList<>();
            System.out.println("Cuantos campos tiene la tabla: ");
            int numCampos = s.nextInt();
            // LIMPIAR EL SCANNER
            s.nextLine();
            for(int j = 0; j < numCampos; j++){
                System.out.print("Campo "+ (j+1) + ": ");
                listaCampos.add(s.nextLine());
            }
            tablas.put(nombreTabla, listaCampos);
        }

        // EN CASO DE QUE HAYAN FOREIGN KEYS
        System.out.print("Hay Foreign Keys?: ");
        String confirmacionF = s.next();

        List<String> foreignKeys = new ArrayList<>();

        while(confirmacionF.equalsIgnoreCase("si")){
            System.out.print("Nombre de la tabla que contiene la Foreign key: ");
            String foreignReference = s.next();
            System.out.print("Nombre del campo que se va a usar: ");
            String campoForeign = s.next();
            System.out.print("Nombre del campo al que tiene que hacer referencia: ");
            String campoRef = s.next();
            System.out.print("Nombre de la tabla a la que hace referencia: ");
            String tableReference = s.next();

            boolean tablaDestinoExiste = tablas.containsKey(tableReference);
            boolean tablaOrigenExiste = tablas.containsKey(foreignReference);
            if(tablaDestinoExiste && tablaOrigenExiste){
                String queryForeign = String.format(
                        "FOREIGN KEY (%s) REFERENCES %s(%s)",
                        campoForeign, tableReference, campoRef
                );
                foreignKeys.add(queryForeign);
            } else {
                System.out.println("Error: alguna de las tablas no existe.");
            }

            System.out.print("Hay alguna foreign key más?: ");
            confirmacionF = s.next();
        }

        sendData(nombreBD, tablas, foreignKeys);
    }

    // MADAMOS LOS DATOS A LA CLASE "CrearScript"
    public static void sendData(String nombreDB,Map<String, List<String>> tablas ,List<String> queryForeign){
        CrearScript.scriptCompleto(nombreDB, tablas, queryForeign);
    }

    public static void main(String[] args){
        System.out.println("## CREADOR DE SCRIPTS .sql ##");
        getData();
    }
}
