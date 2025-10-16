package es.cifpcarlos3;

import java.io.*;

public class Main {

    public static void main(String[] args) {

        String rutaBase = "src/main/java/es/cifpcarlos3/";
        File ficheroEntrada = new File(rutaBase + "frases.txt");
        File carpetaProcesados = new File(rutaBase + "procesados");
        File ficheroSalida = new File(carpetaProcesados, "frases_filtradas.txt");

        if (!carpetaProcesados.exists()) {
            if (carpetaProcesados.mkdir()) {
                System.out.println("Carpeta procesados creada correctamente.");
            } else {
                System.out.println("No se pudo crear la carpeta procesados.");
                return;
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader(ficheroEntrada));
             BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroSalida))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) continue;

                String[] partes = linea.split("\\|");
                if (partes.length != 3) continue;

                String codigo = partes[0].trim();
                String frase = partes[1].trim();
                String autor = partes[2].trim();

                if (codigo.startsWith("2") || autor.endsWith("Monroe") || autor.endsWith("Davis")) {
                    bw.write("\"" + frase + "\" - " + autor);
                    bw.newLine();
                }
            }

            System.out.println("Archivo filtrado creado en: " + ficheroSalida.getPath());

        } catch (IOException e) {
            System.err.println("Error al procesar el fichero: " + e.getMessage());
        }

        if (ficheroEntrada.exists()) {
            if (ficheroEntrada.delete()) {
                System.out.println("Archivo original frases.txteliminado correctamente.");
            } else {
                System.out.println("No se pudo eliminar frases.txt.");
            }
        }
    }
}
