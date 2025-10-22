package es.cifpcarlos3.actividad1_4.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Ciclo implements Serializable {
    String nombre;
    FamiliaProfesional familiaProfesional;
    Grado grado;

    @Override
    public String toString() {
        return "El ciclo es " + nombre +
                " La familia es " + familiaProfesional +
                " El grado es " + grado;
    }
}


