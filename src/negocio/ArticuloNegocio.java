/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

/**
 *
 * @author angul
 */

import Excepciones.DatoInvalidoException;
import Excepciones.ArticuloDuplicadoException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import modelo.Articulo;
import repositorio.ArticuloRepositorio;



public class ArticuloNegocio {

    private final ArticuloRepositorio repositorio = new ArticuloRepositorio();
    private final Stack<String> historial = new Stack<>();

    public void agregar(Articulo articulo)
            throws DatoInvalidoException, ArticuloDuplicadoException {

        validarArticulo(articulo);

        if (repositorio.existeCodigo(articulo.getCodigo())) {
            throw new ArticuloDuplicadoException("Ya existe un articulo con ese código.");
        }

        boolean agregado = repositorio.agregar(articulo);

        if (agregado) {
            historial.push("Articulo registrado: " + articulo.getTitulo());
        }
    }

    public void eliminar(int id, String nombre) {

        boolean eliminado = repositorio.eliminar(id);

        if (eliminado) {
            historial.push("Articulo eliminado: " + nombre);
        }
    }
    
    public void editar(Articulo articulo)
            throws DatoInvalidoException, ArticuloDuplicadoException {

        validarArticulo(articulo);

        boolean editado = repositorio.editar(articulo);

        if (!editado) {
            throw new ArticuloDuplicadoException("No se pudo editar. El código ya existe.");
        }

        historial.push("Articulo editado: " + articulo.getTitulo());
    }
    

    public List<Articulo> listar() {
        return repositorio.listar();
    }

    public Articulo buscarPorCodigo(String codigo) {
        return repositorio.buscarPorCodigo(codigo);
    }

    public List<Articulo> buscarPorNombreOCodigo(String texto) {

        List<Articulo> resultado = new ArrayList<>();

        for (Articulo articulo : repositorio.listar()) {

            if (articulo.getTitulo().toLowerCase().contains(texto.toLowerCase())
                    || articulo.getCodigo().toLowerCase().contains(texto.toLowerCase())) {

                resultado.add(articulo);
            }
        }

        return resultado;
    }

    public List<Articulo> filtrarPorCategoria(String categoria) {

        List<Articulo> resultado = new ArrayList<>();

        for (Articulo articulo : repositorio.listar()) {

            if (articulo.getCategoria().equalsIgnoreCase(categoria)) {
                resultado.add(articulo);
            }
        }

        return resultado;
    }

    public List<Articulo> ordenarPorNombre() {

        List<Articulo> lista = new ArrayList<>(repositorio.listar());

        Collections.sort(lista, new Comparator<Articulo>() {
            @Override
            public int compare(Articulo p1, Articulo p2) {
                return p1.getTitulo().compareToIgnoreCase(p2.getTitulo());
            }
        });

        historial.push("Articulos ordenados por nombre");

        return lista;
    }

    public List<Articulo> ordenarPorPrecio() {

        List<Articulo> lista = new ArrayList<>(repositorio.listar());

        Collections.sort(lista, new Comparator<Articulo>() {
            @Override
            public int compare(Articulo p1, Articulo p2) {
                return Double.compare(p1.getPrecio(), p2.getPrecio());
            }
        });

        historial.push("Articulos ordenados por precio");

        return lista;
    }

    public List<Articulo> ordenarPorCantidad() {

        List<Articulo> lista = new ArrayList<>(repositorio.listar());

        Collections.sort(lista, new Comparator<Articulo>() {
            @Override
            public int compare(Articulo p1, Articulo p2) {
                return Integer.compare(p1.getCantidad(), p2.getCantidad());
            }
        });

        historial.push("Articulos ordenados por cantidad");

        return lista;
    }

    public int totalArticulos() {
        return repositorio.listar().size();
    }

    public int articulosDisponibles() {

        int total = 0;

        for (Articulo articulo : repositorio.listar()) {
            if (articulo.isDisponible()) {
                total++;
            }
        }

        return total;
    }

    public int ArticulosNoDisponibles() {
        return totalArticulos() - articulosDisponibles();
    }

    public int unidadesAlmacenadas() {

        int total = 0;

        for (Articulo articulo : repositorio.listar()) {
            total += articulo.getCantidad();
        }

        return total;
    }

    public Articulo articuloMayorPrecio() {

        if (repositorio.listar().isEmpty()) {
            return null;
        }

        Articulo mayor = repositorio.listar().get(0);

        for (Articulo articulo : repositorio.listar()) {
            if (articulo.getPrecio() > mayor.getPrecio()) {
                mayor = articulo;
            }
        }

        return mayor;
    }

    public Articulo articuloMenorPrecio() {

        if (repositorio.listar().isEmpty()) {
            return null;
        }

        Articulo menor = repositorio.listar().get(0);

        for (Articulo articulo : repositorio.listar()) {
            if (articulo.getPrecio() < menor.getPrecio()) {
                menor = articulo;
            }
        }

        return menor;
    }

    public double valorTotalInventario() {

        double total = 0;

        for (Articulo articulo : repositorio.listar()) {
            total += articulo.getCantidad() * articulo.getPrecio();
        }

        return total;
    }

    public Map<String, Integer> articulosPorCategoria() {

        Map<String, Integer> mapa = new HashMap<>();

        for (Articulo articulo : repositorio.listar()) {

            String categoria = articulo.getCategoria();

            if (mapa.containsKey(categoria)) {
                mapa.put(categoria, mapa.get(categoria) + 1);
            } else {
                mapa.put(categoria, 1);
            }
        }

        return mapa;
    }

    public Stack<String> obtenerHistorial() {
        return historial;
    }

    public void registrarExportacionCorrecta() {
        historial.push("Inventario exportado correctamente");
    }

    private void validarArticulo(Articulo articulo) throws DatoInvalidoException {

        if (articulo.getCodigo() == null || articulo.getCodigo().trim().isEmpty()) {
            throw new DatoInvalidoException("El código es obligatorio.");
        }

        if (articulo.getTitulo() == null || articulo.getTitulo().trim().isEmpty()) {
            throw new DatoInvalidoException("El titulo es obligatorio.");
        }

        if (articulo.getTitulo().trim().length() < 3) {
            throw new DatoInvalidoException("El titulo debe tener mínimo tres caracteres.");
        }

        if (articulo.getCantidad() < 0) {
            throw new DatoInvalidoException("La cantidad debe ser mayor o igual que cero.");
        }

        if (articulo.getPrecio() <= 0) {
            throw new DatoInvalidoException("El precio debe ser mayor que cero.");
        }

        if (articulo.getCategoria() == null
                || articulo.getCategoria().trim().isEmpty()
                || articulo.getCategoria().equals("Seleccione")) {

            throw new DatoInvalidoException("La categoría es obligatoria.");
        }
    }

}
