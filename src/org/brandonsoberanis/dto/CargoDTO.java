package org.brandonsoberanis.dto;

import org.brandonsoberanis.model.Cargo;

public class CargoDTO {
    private static CargoDTO instance;
    private Cargo cargo;
    
    private CargoDTO(){
    
    }
    
    public static CargoDTO getCargoDTO(){
        if(instance == null){
            instance = new CargoDTO();
        }
        return instance;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
    
    
}
