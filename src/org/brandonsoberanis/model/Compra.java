package org.brandonsoberanis.model;

import java.sql.Date;

public class Compra {
    private int compraId;
    private Date fechaCompra;
    private double totalCompra;

    public Compra() {
        
    }

    public Compra(int compraId, Date fechaCompra, double totalCompra) {
        this.compraId = compraId;
        this.fechaCompra = fechaCompra;
        this.totalCompra = totalCompra;
    }

    public int getCompraId() {
        return compraId;
    }

    public void setCompraId(int compraId) {
        this.compraId = compraId;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public double getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(double totalCompra) {
        this.totalCompra = totalCompra;
    }

    @Override
    public String toString() {
        return "Id: " + compraId;
    }
    
    
}
