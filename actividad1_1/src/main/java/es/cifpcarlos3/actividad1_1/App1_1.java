package es.cifpcarlos3.actividad1_1;

import java.io.*;



public class App1_1 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/es/cifpcarlos3/actividad1_1/informacion_ciclos.txt"))) {

            String linea = br.readLine();
            while (linea != null) {
                if (linea.isEmpty() || linea.startsWith("#")) {
                    linea = br.readLine();
                } else {
                    System.out.println(linea);
                    linea = br.readLine();
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        }
    }