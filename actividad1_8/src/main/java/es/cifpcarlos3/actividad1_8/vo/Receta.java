package es.cifpcarlos3.actividad1_8.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Receta {
    private String nombre;
    private String tipo;
    private Origen origen;
    private List<Ingrediente> ingredientes;
}