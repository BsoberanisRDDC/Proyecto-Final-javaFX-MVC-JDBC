package org.brandonsoberanis.dto;

import org.brandonsoberanis.model.DetalleFactura;

public class DetalleFacturaDTO {
    private static DetalleFacturaDTO instance;
    private DetalleFactura detalleFactura;
    
    private DetalleFacturaDTO(){
    
    }
    
    public static DetalleFacturaDTO getDetalleFacturaDTO(){
        if(instance == null){
            instance = new DetalleFacturaDTO();
        }
        return instance;
    }

    public DetalleFactura getDetalleFactura() {
        return detalleFactura;
    }

    public void setDetalleFactura(DetalleFactura detalleFactura) {
        this.detalleFactura = detalleFactura;
    }
}
