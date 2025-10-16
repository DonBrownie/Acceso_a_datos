package es.cifpcarlos3.actividad1_3;

import es.cifpcarlos3.actividad1_3.vo.Ciclo;
import es.cifpcarlos3.actividad1_3.vo.FamiliaProfesional;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GestorFormacion {
    // Las rutas se renombran y se hacen privadas para mayor encapsulación
    private static final String FAMILIAS_FILE = "src/main/java/es/cifpcarlos3/actividad1_3/familia_profesional.dat";
    private static final String CICLOS_FILE = "src/main/java/es/cifpcarlos3/actividad1_3/informacion_ciclos.txt";

    public static void main(String[] argumentos) {
        if (argumentos.length != 1) {
            System.out.println("ADVERTENCIA: Debe proporcionar el código de una familia profesional como único argumento.");
            return;
        }

        String codigoFamiliaInput = argumentos[0].trim();

        FamiliaProfesional familiaEncontrada = obtenerNombreFamilia(codigoFamiliaInput);

        if (familiaEncontrada == null) {
            System.out.println("Error: El código '" + codigoFamiliaInput + "' no corresponde a ninguna familia profesional.");
            return;
        }

        List<Ciclo> listadoDeCiclos = obtenerCiclosDeLaFamilia(codigoFamiliaInput);

        System.out.println(familiaEncontrada);
        System.out.println("Listado de ciclos:");

        if (listadoDeCiclos.isEmpty()) {
            System.out.println("(No se encontraron ciclos para esta familia)");
        } else {
            for (Ciclo ciclo : listadoDeCiclos) {
                System.out.println(ciclo);
            }
        }
    }

    public static FamiliaProfesional obtenerNombreFamilia(String codigo) {
        Path rutaFamilias = Path.of(FAMILIAS_FILE);

        try (BufferedReader lector = Files.newBufferedReader(rutaFamilias)) {
            String registro;
            while ((registro = lector.readLine()) != null) {
                String[] campos = registro.split("=", 2);

                if (campos.length == 2) {
                    String codArchivo = campos[0].trim();
                    String nombreArchivo = campos[1].trim();

                    if (codArchivo.equalsIgnoreCase(codigo.trim())) {
                        return new FamiliaProfesional(codArchivo, nombreArchivo);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error de lectura/escritura en el archivo de familias: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado al buscar familia: " + e.getMessage());
        }
        return null;
    }

    public static List<Ciclo> obtenerCiclosDeLaFamilia(String codigoFamilia) {
        List<Ciclo> listaResultado = new ArrayList<>();
        Path rutaCiclos = Path.of(CICLOS_FILE);

        try (BufferedReader lector = Files.newBufferedReader(rutaCiclos)) {
            String lineaDeDatos;
            while ((lineaDeDatos = lector.readLine()) != null) {

                String datosProcesados = lineaDeDatos.replace('\'', ' ').trim();
                String[] campos = datosProcesados.split(",\\s*");

                if (campos.length >= 4 && campos[3].trim().equalsIgnoreCase(codigoFamilia)) {

                    String codigoCiclo = campos[0].trim();
                    String nombreCiclo = campos[1].trim();

                    int duracionHoras = Integer.parseInt(campos[2].trim());
                    String codigoFam = campos[3].trim();

                    listaResultado.add(new Ciclo(codigoCiclo, nombreCiclo, duracionHoras, codigoFam));
                }
            }
        } catch (IOException e) {
            System.err.println("Fallo al acceder al archivo de ciclos: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Fallo de formato en la duración de un ciclo: " + e.getMessage());
        }
        return listaResultado;
    }
}