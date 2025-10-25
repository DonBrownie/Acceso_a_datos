package es.cifpcarlos3.actividad1_8;

import com.fasterxml.jackson.databind.json.JsonMapper;
import es.cifpcarlos3.actividad1_8.vo.Ingrediente;
import es.cifpcarlos3.actividad1_8.vo.Receta;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Recetario {
    public static void main(String[] args) {
        Path base = Path.of("src","main","java","es","cifpcarlos3","actividad1_8");
        Path json = base.resolve("receta.json");

        var mapper = JsonMapper.builder().build();

        try (var reader = Files.newBufferedReader(json)) {

            Receta receta = mapper.readValue(reader, Receta.class);

            //Aquí lo que hacemos es validar los datos en caso de que estén null o que estén en blanco(que no haya nada básicamente)
            //Esto lo hacemos con cada variable
            if (receta.getNombre() == null || receta.getNombre().isBlank()) {
                System.err.println("El nombre esta vacío o es nulo");
                return;
            }
            if (receta.getTipo() == null || receta.getTipo().isBlank()) {
                System.err.println("El tipo esta vacío o es nulo");
                return;
            }
            if (receta.getOrigen() == null || receta.getOrigen().getPais() == null || receta.getOrigen().getPais().isBlank()) {
                System.err.println("El pais esta vacío o es nulo");
                return;
            }
            List<Ingrediente> ingredientes = receta.getIngredientes();
            if (ingredientes == null || ingredientes.isEmpty()) {
                System.err.println("El campo ingredientes esta vacío o es nulo");
                return;
            }
            for (Ingrediente ing : ingredientes) {
                if (ing.getNombre() == null || ing.getNombre().isBlank()) {
                    System.err.println("Ingrediente sin nombre");
                    return;
                }
                if (ing.getCantidad() == null || ing.getCantidad().isBlank()) {
                    System.err.println("Ingrediente sin cantidad");
                    return;
                }
            }

            System.out.println("Receta: " + receta.getNombre() + " (tipo: " + receta.getTipo() + ")");
            System.out.println("Origen: " + receta.getOrigen().getPais() + " - " + receta.getOrigen().getRegion());
            System.out.println("Ingredientes (" + ingredientes.size() + "):");
            for (Ingrediente ing : ingredientes) {
                System.out.println(ing.getNombre() + " — " + ing.getCantidad());
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
