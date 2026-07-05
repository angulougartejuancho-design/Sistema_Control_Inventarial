/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package presentacion;

/**
 *
 * @author angul
 */
import negocio.ArticuloNegocio;
import modelo.Articulo;
import repositorio.ArticuloRepositorio;

public class ListaArticuloPanel extends javax.swing.JPanel {
    
    private ArticuloNegocio articuloNegocio;
    private ArticuloPanel articuloPanel;
    private EstadisticaPanel estadisticaPanel;

    /**
     * Creates new form ListaArticulos
     */
    public ListaArticuloPanel(ArticuloNegocio articuloNegocio) {
        initComponents();
        this.articuloNegocio = articuloNegocio;
        cargarTabla(articuloNegocio.listar());
        cargarCategorias();
        
    }

    public void setArticuloPanel(ArticuloPanel articuloPanel) {
        this.articuloPanel = articuloPanel;
    }

    public void setEstadisticaPanel(EstadisticaPanel estadisticaPanel) {
        this.estadisticaPanel = estadisticaPanel;
    }
    private void cargarCategorias() {
        cmbFiltro.removeAllItems();
        cmbFiltro.addItem("Todas");
        cmbFiltro.addItem("Hogar");
        cmbFiltro.addItem("Tecnologia");
        cmbFiltro.addItem("Deportes");
        cmbFiltro.addItem("Entretenimiento");
        cmbFiltro.addItem("Moda");
        cmbFiltro.addItem("Otros");
    }

    public void cargarTabla(java.util.List<Articulo> articulos) {
        javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Código");
        modelo.addColumn("Nombre");
        modelo.addColumn("Categoría");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio");
        modelo.addColumn("Disponible");
        

        for (Articulo a : articulos) {
            modelo.addRow(new Object[]{
                a.getId(),
                a.getCodigo(),
                a.getTitulo(),
                a.getCategoria(),
                a.getCantidad(),
                "$" + a.getPrecio(),
                a.isDisponible() ? "Sí" : "No"
                
            });
        }
       

        tblArticulos.setModel(modelo);
    }
    
    public void editararticuloSeleccionado() {
        int fila = tblArticulos.getSelectedRow();

        if (fila == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Seleccione un producto.");
            return;
        }

        String codigo = tblArticulos.getValueAt(fila, 1).toString();
        Articulo articulo = articuloNegocio.buscarPorCodigo(codigo);

        if (articulo != null && articuloPanel != null) {
            articuloPanel.cargarProductoParaEditar(articulo);
            javax.swing.JOptionPane.showMessageDialog(this, "Producto cargado para editar.");
        }
    }

    public void eliminararticuloSeleccionado() {
        int fila =tblArticulos.getSelectedRow();

        if (fila == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Seleccione un producto.");
            return;
        }

        int id = Integer.parseInt(tblArticulos.getValueAt(fila, 0).toString());
        String nombre = tblArticulos.getValueAt(fila, 2).toString();

        int opcion = javax.swing.JOptionPane.showConfirmDialog(
                this,
                "¿Desea eliminar este producto?",
                "Confirmar eliminación",
                javax.swing.JOptionPane.YES_NO_OPTION
        );

        if (opcion == javax.swing.JOptionPane.YES_OPTION) {
            articuloNegocio.eliminar(id, nombre);
            cargarTabla(articuloNegocio.listar());

            if (estadisticaPanel != null) {
                estadisticaPanel.actualizarEstadisticas();
            }
        }
    }
    private void filtrarPorCategoria() {
    String categoria = cmbFiltro.getSelectedItem().toString();

    if (categoria.equals("Todas")) {
        cargarTabla(articuloNegocio.listar());
    } else {
        cargarTabla(articuloNegocio.filtrarPorCategoria(categoria));
    }
}
   
    
    public void ordenarPorNombre() {
        cargarTabla(articuloNegocio.ordenarPorNombre());
    }

    public void ordenarPorPrecio() {
        cargarTabla(articuloNegocio.ordenarPorPrecio());
    }

    public void ordenarPorCantidad() {
        cargarTabla(articuloNegocio.ordenarPorCantidad());
    }
    
    public void exportarInventario() {
        javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();

        int opcion = chooser.showSaveDialog(this);

        if (opcion == javax.swing.JFileChooser.APPROVE_OPTION) {
            java.io.File archivo = chooser.getSelectedFile();

            try {
                util.ArchivoUtil.exportarInventario(articuloNegocio.listar(), archivo);
                articuloNegocio.registrarExportacionCorrecta();

                javax.swing.JOptionPane.showMessageDialog(this, "Inventario exportado correctamente.");

            } catch (Excepciones.ArchivoException e) {
                javax.swing.JOptionPane.showMessageDialog(
                        this,
                        e.getMessage(),
                        "Error",
                        javax.swing.JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtBuscar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblArticulos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        cmbFiltro = new javax.swing.JComboBox<>();
        btnFiltrar = new javax.swing.JButton();
        btnPorPrecio = new javax.swing.JButton();
        btnPorNombre = new javax.swing.JButton();
        btnPorCantidad = new javax.swing.JButton();

        setBackground(new java.awt.Color(153, 204, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Buscar por nombre o codigo");

        btnBuscar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(this::btnBuscarActionPerformed);

        tblArticulos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tblArticulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Codigo", "Titulo", "Categoria", "Cantidad", "Precio", "Disponible"
            }
        ));
        jScrollPane1.setViewportView(tblArticulos);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Categoria");

        cmbFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hogar", "Tecnologia", "Deportes", "Entretenimiento", "Moda", "Otros..." }));
        cmbFiltro.addActionListener(this::cmbFiltroActionPerformed);

        btnFiltrar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(this::btnFiltrarActionPerformed);

        btnPorPrecio.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnPorPrecio.setText("Ordenar precio");
        btnPorPrecio.addActionListener(this::btnPorPrecioActionPerformed);

        btnPorNombre.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnPorNombre.setText("Ordenar Nombre");
        btnPorNombre.addActionListener(this::btnPorNombreActionPerformed);

        btnPorCantidad.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnPorCantidad.setText("Ordenar cantidad");
        btnPorCantidad.addActionListener(this::btnPorCantidadActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtBuscar)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscar))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(btnPorPrecio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPorNombre))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(cmbFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnFiltrar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPorCantidad)))
                .addContainerGap(371, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addGap(36, 36, 36)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFiltrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPorPrecio)
                    .addComponent(btnPorNombre)
                    .addComponent(btnPorCantidad))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnPorCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPorCantidadActionPerformed
        // TODO add your handling code here:
        ordenarPorCantidad();
    }//GEN-LAST:event_btnPorCantidadActionPerformed

    private void btnPorNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPorNombreActionPerformed
        // TODO add your handling code here:
        ordenarPorNombre();
    }//GEN-LAST:event_btnPorNombreActionPerformed

    private void btnPorPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPorPrecioActionPerformed
        // TODO add your handling code here:
        ordenarPorPrecio();
    }//GEN-LAST:event_btnPorPrecioActionPerformed

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        // TODO add your handling code here:String categoria = cmbFiltroCategoria.getSelectedItem().toString();
        filtrarPorCategoria();
        
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void cmbFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFiltroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbFiltroActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        cargarTabla(articuloNegocio.buscarPorNombreOCodigo(txtBuscar.getText()));
    }//GEN-LAST:event_btnBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnPorCantidad;
    private javax.swing.JButton btnPorNombre;
    private javax.swing.JButton btnPorPrecio;
    private javax.swing.JComboBox<String> cmbFiltro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblArticulos;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables

  
}
