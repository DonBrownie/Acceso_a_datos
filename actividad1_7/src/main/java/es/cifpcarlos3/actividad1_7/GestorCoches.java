package es.cifpcarlos3.actividad1_7;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import es.cifpcarlos3.actividad1_7.vo.CatalogoCoches;
import es.cifpcarlos3.actividad1_7.vo.Coches;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GestorCoches {
    public static void main(String[] args) {
        Path base = Path.of("src","main","java","es","cifpcarlos3","actividad1_7");
        Path entrada = base.resolve("coches.txt");
        Path salida = base.resolve("coches.xml");


        List<Coches> listaCoches = new ArrayList<>();

        int leidas = 0;
        int validas = 0;
        int ignoradas = 0;

        try (var br = Files.newBufferedReader(entrada)) {
            String linea;
            int nLinea = 0;
            while ((linea = br.readLine()) != null) {
                leidas++;
                String[] partes = linea.split(",");
                if (partes.length < 3 || partes.length > 4) {
                    System.out.println("Línea ignorada");
                    ignoradas++;
                    continue;
                }
                String marca = partes[0];
                String modelo = partes[1];
                String color = partes[2];

                int anio = 0;
                if (partes.length == 4) {
                try {
                    anio = Integer.parseInt(partes[3]);
                } catch (NumberFormatException e) {
                    ignoradas++;
                    System.err.println("numero de anio invalido");
                    continue;
                }
            }
                Coches anadirCoches = new Coches(marca, modelo, color, anio);
                listaCoches.add(anadirCoches);
                validas++;
            }

            CatalogoCoches catalogo = new CatalogoCoches(listaCoches);

            var mapper = new XmlMapper();
            var writer = mapper.writerWithDefaultPrettyPrinter(); // formato bonito
            writer.writeValue(salida.toFile(), catalogo);

            System.out.println("Leidas: " + leidas + " | Validas: " +  validas + "| ignoradas: " + ignoradas);
            System.out.println("XML generado en: " + salida.toAbsolutePath());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}