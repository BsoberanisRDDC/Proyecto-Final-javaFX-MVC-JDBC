/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.brandonsoberanis.dto;

import org.brandonsoberanis.model.Cliente;

/**
 *
 * @author Informatica
 */
public class ClienteDTO {
    private static ClienteDTO instance;
    public static ClienteDTO getClienteDTO(){
        if(instance == null){
            instance = new ClienteDTO();
        }
        return instance;
    }
    private Cliente cliente;
    
    private ClienteDTO(){
    }
    

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
}
