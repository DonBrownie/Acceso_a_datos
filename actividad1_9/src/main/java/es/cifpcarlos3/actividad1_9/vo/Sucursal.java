package es.cifpcarlos3.actividad1_9.vo;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sucursal {
    @JacksonXmlProperty(localName = "calle")
    private String calle;
    @JacksonXmlProperty(localName = "ciudad")
    private String ciudad;
    @JacksonXmlProperty(localName = "provincia")
    private String provincia;
    @JacksonXmlProperty(localName = "cp")
    private String cp;


}
