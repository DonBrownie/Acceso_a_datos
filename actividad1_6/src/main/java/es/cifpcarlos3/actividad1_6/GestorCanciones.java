package es.cifpcarlos3.actividad1_6;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.cifpcarlos3.actividad1_6.vo.Cancion;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class GestorCanciones {

    public static void main(String[] args) {

        //Creamos la ruta del archivo que queremos leer y la salida del archivo
        Path dir_base = Path.of("src","main","java","es","cifpcarlos3","actividad1_6");
        Path txt = dir_base.resolve("canciones.txt");
        Path json = dir_base.resolve("canciones.json");

        //Lista de canciones para almacenar
        List<Cancion> canciones = new ArrayList<>();

        //Creamos las diferentes variables para saber cuantas líneas se leen se validan y se ignoran
        int lineasLeidas = 0;
        int lineasValidas = 0;
        int lineasIgnoradas = 0;

        try (var br = Files.newBufferedReader(txt, StandardCharsets.UTF_8)) {


            String linea;
            String[] partes;
            //Bucle para leer el archivo de canciones
            while ((linea = br.readLine()) != null) {
                partes = linea.split(",");
                //Sumamos 1 a las lineas leidas
                lineasLeidas++;
                //Validamos de que sean 5 lineas si no entendemos de que hay una linea que ha sido ignorada
                if (partes.length != 5) {
                    System.out.println("Alguna linea ignorada");
                    lineasIgnoradas++;
                }
                try {
                    //Variables para asignar las lineas del archivo a las variables
                    int anio = Integer.parseInt(partes[0].trim());
                    String titulo = partes[1].trim();
                    String artista = partes[2].trim();
                    String duracion = partes[3].trim();
                    boolean espanola = Boolean.parseBoolean(partes[4].trim().toLowerCase());

                    //Creamos un objeto de la clase cancion y le pasamos las variables
                    Cancion c = new Cancion(anio, titulo, artista, duracion, espanola);
                    //añadimos a la lista el objeto de cancion
                    canciones.add(c);
                    //Sumamos a las lineas validadas
                    lineasValidas++;


                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            try {
                var mapper = new ObjectMapper();
                mapper.writerWithDefaultPrettyPrinter().writeValue(json.toFile(), canciones);

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            //Y por ultimo imprimimos los resultados
            System.out.println("Leidas " +  lineasLeidas + " | Validas " + lineasValidas + " | Ignoradas " + lineasIgnoradas);
            System.out.println("JSON generado en: ");
            System.out.println(json);
        }
        catch (IOException e) {
        throw new RuntimeException(e);

        }
    }
}
