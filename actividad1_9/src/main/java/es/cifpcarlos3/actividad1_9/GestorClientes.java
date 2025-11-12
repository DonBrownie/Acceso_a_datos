package es.cifpcarlos3.actividad1_9;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import es.cifpcarlos3.actividad1_9.vo.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;

public class GestorClientes {
    public static void main(String[] args) {
        Path base = Path.of("src", "main", "java", "es", "cifpcarlos3", "actividad1_9");
        Path xmlFile = base.resolve("clientes.xml");

        try {
            var xmlMapper = XmlMapper.builder().build();

            ListaClientes lista;

            try (var br = Files.newBufferedReader(xmlFile, StandardCharsets.UTF_8)) {
                lista = xmlMapper.readValue(br, ListaClientes.class);
            }

            validar(lista);
            mostrarResumen(lista);

        } catch (Exception e) {
            System.err.println("Error leyendo/deserializando XML: " + e.getMessage());
        }
    }

    private static void validar(ListaClientes lista) {
        if (lista == null || lista.getClientes() == null || lista.getClientes().isEmpty())
            throw new IllegalArgumentException("No se encontraron clientes en el XML.");

        for (Cliente c : lista.getClientes()) {
            if (c.getId() == 0)
                throw new IllegalArgumentException("El id del cliente no puede estar vacio.");
            if (c.getNombre() == null || c.getNombre().isBlank())
                throw new IllegalArgumentException("EL nombre no puede estar vacio");
            if (c.getSucursal() == null || c.getSucursal().isEmpty())
                throw new IllegalArgumentException("El cliente " + c.getNombre() + " no tiene sucursales.");

            for (Sucursal s : c.getSucursal()) {
                if (s.getCalle() == null || s.getCalle().isBlank())
                    throw new IllegalArgumentException("Sucursal con calle vacía en cliente " + c.getNombre());
                if (s.getCiudad() == null || s.getCiudad().isBlank())
                    throw new IllegalArgumentException("Sucursal con ciudad vacía en cliente " + c.getNombre());
            }
        }
    }

    private static void mostrarResumen(ListaClientes lista) {
        for (Cliente c : lista.getClientes()) {
            System.out.println("Cliente: " + c.getNombre() + c.getId());
            List<Sucursal> sucursales = c.getSucursal();
            System.out.println("Sucursales: " + sucursales.size());
            for (Sucursal s : sucursales) {

                System.out.printf("\nSucursal: " + s.getCalle(), s.getCiudad() + s.getProvincia() + s.getCp());

            }
            System.out.println();
        }
    }
}
