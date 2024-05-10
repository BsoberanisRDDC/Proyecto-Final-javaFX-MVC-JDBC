/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.brandonsoberanis.model;

public class DetalleFactura {
    private int detalleFacturasId;
    private int facturaId;
    private int productoID;

    public DetalleFactura(int detalleFacturasId, int facturaId, int productoID) {
        this.detalleFacturasId = detalleFacturasId;
        this.facturaId = facturaId;
        this.productoID = productoID;
    }

    public int getDetalleFacturasId() {
        return detalleFacturasId;
    }

    public void setDetalleFacturasId(int detalleFacturasId) {
        this.detalleFacturasId = detalleFacturasId;
    }

    public int getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(int facturaId) {
        this.facturaId = facturaId;
    }

    public int getProductoID() {
        return productoID;
    }

    public void setProductoID(int productoID) {
        this.productoID = productoID;
    }
}

