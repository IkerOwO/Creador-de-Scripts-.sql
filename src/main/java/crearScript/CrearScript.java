package main.java.crearScript;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CrearScript {
    public static void scriptCompleto(String nombreBD, String[] nombreTablas, String[] campos){
        StringBuilder createTablas = new StringBuilder();
        for (String nombreTabla : nombreTablas) {
            // USAMOS .append PARA UNIR EL TEXTO JUNTO CON LAS VARIABLES
            createTablas.append("CREATE TABLE ").append(nombreTabla).append(" (");
            // PONER LOS CAMPOS
            for (int j = 0; j < campos.length; j++) {
                createTablas.append(campos[j]);
                // LOS SEPARAMOS CON COMA
                if (j < campos.length - 1) {
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

        System.out.println("Directorio de ejecución: " + System.getProperty("user.dir"));

        // CREAMOS EL SCRIPT .sql CON EL NOMBRE DE LA BBDD
        File carpeta = new File("src/main/java/Scripts");
        carpeta.mkdirs();
        File archivo = new File(carpeta, nombreBD + ".sql");

        try(FileWriter file = new FileWriter(archivo)){
            file.write(texto);
        } catch (IOException e) {
            System.out.println("ERROR AL CREAR/ESCRIBIR EL ARCHIVO");
            throw new RuntimeException(e);
        }

    }
}
