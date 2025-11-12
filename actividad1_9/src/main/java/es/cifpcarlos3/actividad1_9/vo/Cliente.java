package es.cifpcarlos3.actividad1_9.vo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor



public class Cliente {
    @JacksonXmlProperty(isAttribute = true, localName = "id")
    private long id;
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "nombre")
    private String nombre;
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "sucursal")
    private List<Sucursal> sucursal;

    public void validarClientes() {
        if (id <= 0) {
            System.out.println("El id debe ser mayor o igual a 0");
        } else if (nombre == null || nombre.isEmpty()) {
            System.out.println("El nombre debe de contener un valor");
        }  else if (sucursal == null) {
            System.out.println("El cliente debe de tener al menos una sucursal");
        }

    }



}
