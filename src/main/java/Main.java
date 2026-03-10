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
        // TODO PONERLO EN UN BUCLE PARA QUE HAGA FOREIGN KEYS HASTA QUE EN LA COMPROBACION EL USUARIO PONGA UN "no"
        System.out.print("Hay Foreign Keys?: ");
        String confirmacionF = s.next();
        String queryForeign = null;

        if (confirmacionF.equalsIgnoreCase("si")) {
            System.out.print("Nombre de la tabla que contiene la Foreign key: ");
            String foreignReference = s.next();
            System.out.print("Nombre del campo que se va a usar: ");
            String campoForeign = s.next();
            System.out.print("Nombre del campo de al que tiene que hacer referencia: ");
            String campoRef = s.next();
            System.out.print("Nombre de la tabla a la que hace referencia: ");
            String tableReference = s.next();

            // COMPROBAMOS SI LAS TABLAS CONTIENE EL CAMPO INDICADO
            boolean tablaExiste = tablas.containsKey(tableReference);
            boolean campoExiste = tablas.containsKey(foreignReference);
            if (tablaExiste && campoExiste) {
                queryForeign = String.format(
                        "FOREIGN KEY (%s) REFERENCES %s(%s);",
                        campoForeign, tableReference, campoRef
                );
            }
        }



        // MANDAR DATOS
        sendData(nombreBD, tablas, queryForeign);
    }

    // MADAMOS LOS DATOS A LA CLASE "CrearScript"
    public static void sendData(String nombreDB,Map<String, List<String>> tablas ,String queryForeign){
        CrearScript.scriptCompleto(nombreDB, tablas, queryForeign);
    }

    public static void main(String[] args){
        System.out.println("## CREADOR DE SCRIPTS .sql ##");
        getData();
    }
}
