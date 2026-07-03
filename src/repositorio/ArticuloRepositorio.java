/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorio;

/**
 *
 * @author angul
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import modelo.Articulo;


public class ArticuloRepositorio {

    private static final List<Articulo> articulos = new ArrayList<>();
    private static final Set<String> codigos = new HashSet<>();
    private static int contadorId = 1;

    public boolean agregar(Articulo articulo) {

        if (codigos.contains(articulo.getCodigo())) {
            return false;
        }

        articulo.setId(contadorId);
        contadorId++;

        articulos.add(articulo);
        codigos.add(articulo.getCodigo());

        return true;
    }

    public List<Articulo> listar() {
        return articulos;
    }

    public Articulo buscarPorCodigo(String codigo) {

        for (Articulo articulo : articulos) {
            if (articulo.getCodigo().equalsIgnoreCase(codigo)) {
                return articulo;
            }
        }

        return null;
    }

    public boolean existeCodigo(String codigo) {
        return codigos.contains(codigo);
    }

    public boolean editar(Articulo articuloEditado) {

        for (int i = 0; i < articulos.size(); i++) {

            Articulo articuloActual = articulos.get(i);

            if (articuloActual.getId() == articuloEditado.getId()) {

                if (!articuloActual.getCodigo().equalsIgnoreCase(articuloEditado.getCodigo())
                        && codigos.contains(articuloEditado.getCodigo())) {
                    return false;
                }

                codigos.remove(articuloActual.getCodigo());
                articulos.set(i, articuloEditado);
                codigos.add(articuloEditado.getCodigo());

                return true;
            }
        }

        return false;
    }

    public boolean eliminar(int id) {

        Articulo articuloEliminar = null;

        for (Articulo articulo : articulos) {
            if (articulo.getId() == id) {
                articuloEliminar = articulo;
                break;
            }
        }

        if (articuloEliminar != null) {
            articulos.remove(articuloEliminar);
            codigos.remove(articuloEliminar.getCodigo());
            return true;
        }

        return false;
    }
}
