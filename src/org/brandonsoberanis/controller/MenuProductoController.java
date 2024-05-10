/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.brandonsoberanis.controller;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.brandonsoberanis.model.Producto;

public class MenuProductoController {

    @FXML
    private Label lblProductoId;

    @FXML
    private Label lblNombreProducto;

    @FXML
    private Label lblDescripcionProducto;

    @FXML
    private Label lblCantidadStock;

    @FXML
    private Label lblPrecioVentaUnitario;

    @FXML
    private Label lblPrecioVentaMayor;

    @FXML
    private Label lblPrecioCompra;

    // Método para inicializar el controlador
    public void initialize(Producto producto) {
        // Mostrar los detalles del producto en las etiquetas
        lblProductoId.setText(Integer.toString(producto.getProductoId()));
        lblNombreProducto.setText(producto.getNombreProducto());
        lblDescripcionProducto.setText(producto.getDescripcionProducto());
        lblCantidadStock.setText(Integer.toString(producto.getCantidadStock()));
        lblPrecioVentaUnitario.setText(Double.toString(producto.getPrecioVentaUnitario()));
        lblPrecioVentaMayor.setText(Double.toString(producto.getPrecioVentaMayor()));
        lblPrecioCompra.setText(Double.toString(producto.getPrecioCompra()));
    }
    public void initialize(URL location, ResourceBundle resources) {
        // TODO
    }    
    
}

    

