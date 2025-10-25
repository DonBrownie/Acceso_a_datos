package es.cifpcarlos3.actividad1_7.vo;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "coche")
public class Coches {

    private String marca;
    private String modelo;
    private String color;
    private int anio;
}
