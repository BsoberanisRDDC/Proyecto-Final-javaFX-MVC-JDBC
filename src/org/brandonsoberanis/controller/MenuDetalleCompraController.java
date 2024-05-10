/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.brandonsoberanis.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MenuDetalleCompraController {

    @FXML
    private Label lblCompraId;

    @FXML
    private Label lblFechaCompra;

    @FXML
    private Label lblTotal;

    // Método para inicializar el controlador
    public void initialize(Compra compra) {
        // Mostrar los detalles de la compra en las etiquetas
        lblCompraId.setText(Integer.toString(compra.getCompraId()));
        lblFechaCompra.setText(compra.getFechaCompra().toString());
        lblTotal.setText(Double.toString(compra.getTotal()));
    }
    public void initialize(URL location, ResourceBundle resources) {
        // TODO
    }  

    private static class Compra {

        public Compra() {
        }

        private int getCompraId() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private Object getFechaCompra() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private double getTotal() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
}

      

