package main.java.crearScript;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CrearScript {
    // RECIBIMOS LOS DISTINTOS OBJETOS DESDE LA CLASE "sendData"
    public static void scriptCompleto(String nombreBD,
                                      Map<String, List<String>> tablas,
                                      String queryForeign){
        // CREAMOS LOS "create table" CON LOS DISTINTOS NOMBRES ASIGNADOS Y LOS DATOS ELEGIDOS
        StringBuilder createTablas = new StringBuilder();
        for(String tabla : tablas.keySet()){
            // USAMOS .append PARA JUNTAR LAS VARIABLES CON EL TEXTO
            createTablas.append("CREATE TABLE ")
                    .append(tabla)
                    .append(" (");
            List<String> listaCampos = tablas.get(tabla);
            for(int i = 0; i < listaCampos.size(); i++){
                createTablas.append(listaCampos.get(i));
                if(i < listaCampos.size()-1){
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
                        "%s" +
                        " ",
                nombreBD,
                nombreBD,
                nombreBD,
                createTablas,
                queryForeign
        );

        System.out.println("Directorio de ejecución: " + System.getProperty("user.dir"));

        // CREAMOS LA CARPETA Y EL SCRIPT .sql CON EL NOMBRE DE LA BBDD
        File carpeta = new File("src/main/java/Scripts");
        carpeta.mkdirs();
        File archivo = new File(carpeta, nombreBD + ".sql");
        try (FileWriter file = new FileWriter(archivo)){
            file.write(texto);
        } catch (IOException e) {
            System.out.println("ERROR AL CREAR/ESCRIBIR EL ARCHIVO");
            throw new RuntimeException(e);
        }

    }
}
