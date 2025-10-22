package es.cifpcarlos3.actividad1_4;

import es.cifpcarlos3.actividad1_4.vo.Ciclo;
import es.cifpcarlos3.actividad1_4.vo.FamiliaProfesional;
import es.cifpcarlos3.actividad1_4.vo.Grado;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorFormacion2 {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Son necesarios dos argumentos el codigoFamilia y codigoGrado");
            return;
        }

        String codigoFamilia = args[0];
        String codigoGrado = args[1];

        try {
            File path = new File("Acceso_a_datos/actividad1_4/src/main/java/es/cifpcarlos3/actividad1_4/");
            File ficheroFamilia = new File(path, "familia_profesional.dat");
            File ficheroGrado = new File(path, "grados.csv");
            File ficheroCilcos = new File(path, "informacion_ciclos.txt");
            File ficheroSalida = new File(path, "lista_ciclos.ser");

            List<Grado> Grado;
            List<FamiliaProfesional> listadoDeFamilia;
            List<Ciclo> listadoDeCiclo;

            Grado = leerGrados(ficheroGrado);
            listadoDeFamilia = leerFamilias(ficheroFamilia);

            FamiliaProfesional familiaSeleccionada = null;
            for (FamiliaProfesional f : listadoDeFamilia ) {
                if (f.getCod().equals(codigoFamilia)) {
                    familiaSeleccionada = f;
                    break;
                }
            }

            Grado gradoSeleccionado = null;
            for (Grado g : Grado) {
                if (g.getCodigo().equals(codigoGrado)) {
                    gradoSeleccionado = g;
                    break;
                }
            }

            if (gradoSeleccionado == null) {
                System.out.println("Grado no encontrado");
                return;
            }

            if (familiaSeleccionada == null) {
                System.out.println("Familia no encontrada");
                return;
            }

            listadoDeCiclo = leerCiclos(ficheroCilcos, familiaSeleccionada, gradoSeleccionado);

            for (Ciclo c: listadoDeCiclo) {
                System.out.println(c);
            }

            serializar(listadoDeCiclo, ficheroSalida);
            System.out.println("Lista serializada "+ ficheroSalida.getName());

        } catch (Exception e) {
            throw   new RuntimeException(e);
        }
    }

    public static List<FamiliaProfesional> leerFamilias(File fichero) {
        List<FamiliaProfesional> familia = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {

                String[] partes = linea.split("=");
                if (partes.length >= 2) {
                    String codigoFamilia = partes[0];
                    String nombre = partes[1];
                    familia.add(new FamiliaProfesional(codigoFamilia, nombre));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return familia;
    }

    public static List<Grado> leerGrados(File fichero) {
        List<Grado> grado = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("#");
                grado.add(new Grado(partes[0], partes[1]));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return grado;
    }

    public static List<Ciclo> leerCiclos(File fichero, FamiliaProfesional familiaProfesional, Grado grado) {
        List<Ciclo> ciclo = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.replace("'", "").split(",");
                if (partes.length == 3) {
                    String codFam = partes[0];
                    String codGrado = partes[1];
                    String nombreCiclo = partes[2];
                    if (codFam.equals(familiaProfesional.getCod()) && codGrado.equals(grado.getCodigo())) {
                        ciclo.add(new Ciclo(nombreCiclo, familiaProfesional, grado));
                    }
                }
            }
            return ciclo;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void serializar(List<Ciclo> ciclo, File ficheroSalida) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ficheroSalida))) {
            oos.writeObject(ciclo);
        } catch (IOException e) {
            System.err.println("Error al serialzar la lista");
        }
    }
}
