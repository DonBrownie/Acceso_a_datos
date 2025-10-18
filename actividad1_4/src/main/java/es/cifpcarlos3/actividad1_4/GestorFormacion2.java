package es.cifpcarlos3.actividad1_4;


import es.cifpcarlos3.actividad1_4.vo.Ciclo;
import es.cifpcarlos3.actividad1_4.vo.FamiliaProfesional;
import es.cifpcarlos3.actividad1_4.vo.Grado;

import java.io.*;
import java.sql.ClientInfoStatus;
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
            File path = new File("src/main/java/es/cifpcarlos3/actividad1_4/");
            File ficheroFamilia = new File(path, "familia_profesional.dat");
            File ficheroGrado = new File(path, "grados.csv");
            File ficheroCilcos = new File(path, "informacion_ciclos.txt");
            File ficheroSalida = new File(path, "lista_ciclos.txt");

            List<Grado> Grado = new ArrayList<>();
            List<FamiliaProfesional> listadoDeFamilia = new ArrayList<>();



        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public static List<FamiliaProfesional> leerFamilias(File fichero) {
        List<FamiliaProfesional> familia = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) !=  null) {
                String[] partes = linea.split(" = ");
                familia.add(new FamiliaProfesional(partes[0], partes[1]));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String linea = "";

        return familia;
    }

    public static List<Grado> leerGrados(File fichero) {
        List<Grado> grado = new ArrayList<>();
        grado.add(new Grado("Grado 1", "Grado 1"));
        return grado;
    }


}