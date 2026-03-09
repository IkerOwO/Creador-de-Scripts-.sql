package main.java.crearScript;
import java.io.FileWriter;
import java.io.IOException;

public class CrearScript {
    public static void scriptCompleto(String nombreBD, String[] nombreTablas, String[] campos){
        StringBuilder createTablas = new StringBuilder();
        for(int i = 0; i < nombreTablas.length; i++){
            // USAMOS .append PARA UNIR EL TEXTO JUNTO CON LAS VARIABLES
            createTablas.append("CREATE TABLE ").append(nombreTablas[i]).append(" (");
            // PONER LOS CAMPOS
            for(int j = 0; j < campos.length; j++){
                createTablas.append(campos[j]);
                // LOS SEPARAMOS CON COMA
                if(j < campos.length - 1){
                    createTablas.append(", ");
                }
            }
            createTablas.append(");\n");
        }
        // CREAMOS EL TEXTO QUE IRA DENTRO DEL ARCHIVO
        String texto = String.format(
                "DROP DATABASE IF EXISTS %s;\n" +
                        "CREATE DATABASE %s;\n" +
                        "USE %s;\n" +
                        "%s" +
                        "",
                nombreBD,
                nombreBD,
                nombreBD,
                createTablas
        );

        // CREAMOS EL SCRIPT .sql CON EL NOMBRE DE LA BBDD
        try(FileWriter file = new FileWriter("src/main/java/Scripts/"+nombreBD+".sql")){
            file.write(texto);
        } catch (IOException e) {
            System.out.println("ERROR AL CREAR/ESCRIBIR EL ARCHIVO");
            throw new RuntimeException(e);
        }

    }
}
