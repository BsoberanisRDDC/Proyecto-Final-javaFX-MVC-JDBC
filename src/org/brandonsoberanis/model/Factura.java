/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.brandonsoberanis.model;

/**
 *
 * @author JULIO SOBERANIS
 */
import java.time.LocalDateTime;
import java.util.List;

public class Factura {
    private final int detalleFacturasId;
    private final int facturaId;
    private final int productoID;
    private final double cantidad;
    private final double precioUnitario;
    private int facturaID;
    private LocalDateTime fechaHora;
    private int clienteId;
    private double total;
    private List<DetalleFactura> detalles;

    // Constructor
    public Factura(int detalleFacturasId, int facturaId, int productoID, double cantidad, double precioUnitario) {
        this.detalleFacturasId = detalleFacturasId;
        this.facturaId = facturaId;
        this.productoID = productoID;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    // Métodos getter y setter

    public int getDetalleFacturaID() {
        int facturaID = 0;
        return facturaID;
    }

    public void setFacturaID(int facturaID) {
        this.facturaID = facturaID;
    }

    public LocalDateTime getFechaHora() {
        LocalDateTime fechaHora = null;
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getClienteId() {
        int clienteId = 0;
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public double getTotal() {
        double total = 0;
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<DetalleFactura> getDetalles() {
        List<DetalleFactura> detalles = null;
        return detalles;
    }

    public void setDetalles(List<DetalleFactura> detalles) {
        this.detalles = detalles;
    }

    // Método para agregar un detalle a la factura
    public void agregarDetalle(DetalleFactura detalle) {
        detalles.add(detalle);
        calcularTotal(); // Recalcular total al agregar un detalle
    }

    // Método para calcular el total de la factura automáticamente
      public double getPrecioTotal() {
        return cantidad * precioUnitario;
    }

    private void calcularTotal() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getFacturaID() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object getFecha() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object getHora() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getEmpleadoId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
        }
       
    


