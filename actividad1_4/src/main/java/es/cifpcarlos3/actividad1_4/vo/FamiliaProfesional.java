package es.cifpcarlos3.actividad1_4.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class FamiliaProfesional implements Serializable {
    String cod;
    String nombre;
}
