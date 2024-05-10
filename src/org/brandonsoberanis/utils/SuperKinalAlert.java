/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.brandonsoberanis.utils;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Informatica
 */
public class SuperKinalAlert {
    private static SuperKinalAlert instance;
    
    
    public static SuperKinalAlert getInstance(){
        if(instance == null){
            instance = new SuperKinalAlert();
        }
        return instance;
    }
    private SuperKinalAlert(){
    }
    
    public void mostrarAlertaInformacion(int code){
        switch (code) {
            case 400:
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Confirmación Registro");
                    alert.setHeaderText("Confirmación Registro");
                    alert.setContentText("¡Registro realizado con éxito!");
                    alert.showAndWait();
                    break;
                }
            case 500:
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Edición Registro");
                    alert.setHeaderText("Edición Registro");
                    alert.setContentText("¡Edición realizada con éxito!");
                    alert.showAndWait();
                    break;
                }
            case 600:
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Campos Pendientes");
                    alert.setHeaderText("Campos Pendientes");
                    alert.setContentText("¡Algunos campos necesarios para el registro están vacíos!");
                    alert.showAndWait();
                    break;
                }
            default:
                break;
        }
    }
    
    public Optional<ButtonType> mostrarAlertaConfirmacion(int code){
        Optional<ButtonType> action = null;
        
        if(code == 504){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Eliminación Registro");
            alert.setHeaderText("Eliminación Registro");
            alert.setContentText("¿Desea confirmar la eliminación del registro?");
            action = alert.showAndWait();
        }else if(code == 505){ //Codigo 505 sirve para confirmar la edicion de registros
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Edición Registro");
            alert.setHeaderText("Edición Registro");
            alert.setContentText("¿Desea confirmar la edición del registro?");
            action = alert.showAndWait();
        }
        
        return action;
    }
    
}
