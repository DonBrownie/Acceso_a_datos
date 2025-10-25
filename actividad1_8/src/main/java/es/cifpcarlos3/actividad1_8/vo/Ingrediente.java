package es.cifpcarlos3.actividad1_8.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingrediente {
    private String nombre;
    private String cantidad;
}
