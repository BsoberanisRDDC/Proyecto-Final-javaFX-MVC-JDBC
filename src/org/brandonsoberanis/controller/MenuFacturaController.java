/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.brandonsoberanis.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.brandonsoberanis.model.Factura;

public class MenuFacturaController {

    @FXML
    private Label lblFacturaID;

    @FXML
    private Label lblFecha;

    @FXML
    private Label lblHora;

    @FXML
    private Label lblClienteId;

    @FXML
    private Label lblEmpleadoId;

    @FXML
    private Label lblTotal;

    // Método para inicializar el controlador
    public void initialize(Factura factura) {
        // Mostrar los detalles de la factura en las etiquetas
        lblFacturaID.setText(Integer.toString(factura.getFacturaID()));
        lblFecha.setText(factura.getFecha().toString());
        lblHora.setText(factura.getHora().toString());
        lblClienteId.setText(Integer.toString(factura.getClienteId()));
        lblEmpleadoId.setText(Integer.toString(factura.getEmpleadoId()));
        lblTotal.setText(Double.toString(factura.getTotal()));
    }
}



    

  
   
    


  
      
    

