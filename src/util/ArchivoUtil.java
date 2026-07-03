/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author angul
 */

import Excepciones.ArchivoException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import modelo.Articulo;

public class ArchivoUtil {

    public static void exportarInventario(List<Articulo> articulos, File archivo)
            throws ArchivoException {

        
        
        try (FileWriter escritor = new FileWriter(archivo)) {

            
            escritor.write("ID,Codigo,Nombre,Categoria,Cantidad,Precio,Disponible\n");

            
            
            for (Articulo articulo : articulos) {

                escritor.write(
                        articulo.getId() + ","
                        + articulo.getCodigo() + ","
                        + articulo.getTitulo() + ","
                        + articulo.getCategoria() + ","
                        + articulo.getCantidad() + ","
                        + articulo.getPrecio() + ","
                        + articulo.isDisponible() + "\n"
                );
            }

            
            
            
        } catch (IOException e) {
            throw new ArchivoException("Error al exportar el inventario.", e);
        }
    }
}