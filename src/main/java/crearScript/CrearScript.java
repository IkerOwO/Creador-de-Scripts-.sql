package main.java.crearScript;
import java.io.FileWriter;
import java.io.IOException;

public class CrearScript {
    public static void scriptCompleto(String nombreBD, String[] nombreTablas){
        // CREAMOS EL TEXTO QUE IRA DENTRO DEL ARCHIVO
        String texto = String.format("DROP DATABASE %s IF EXISTS",nombreBD);

        // CREAMOS EL SCRIPT .sql CON EL NOMBRE DE LA BBDD
        try(FileWriter file = new FileWriter("src/main/java/Scripts/"+nombreBD+".sql")){
            file.write(texto);
        } catch (IOException e) {
            System.out.println("ERROR AL CREAR/ESCRIBIR EL ARCHIVO");
            throw new RuntimeException(e);
        }

    }
}
