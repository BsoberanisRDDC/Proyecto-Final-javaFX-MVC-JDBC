/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.brandonsoberanis.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.brandonsoberanis.model.DetalleFactura;

public class MenuDetalleFacturaController {

    @FXML
    private Label lblDetalleFacturasId;

    @FXML
    private Label lblFacturaId;

    @FXML
    private Label lblProductoID;

    // Método para inicializar el controlador
    public void initialize(DetalleFactura detalleFactura) {
        // Mostrar los detalles del detalle de factura en las etiquetas
        lblDetalleFacturasId.setText(Integer.toString(detalleFactura.getDetalleFacturasId()));
        lblFacturaId.setText(Integer.toString(detalleFactura.getFacturaId()));
        lblProductoID.setText(Integer.toString(detalleFactura.getProductoID()));
    }
}




    
