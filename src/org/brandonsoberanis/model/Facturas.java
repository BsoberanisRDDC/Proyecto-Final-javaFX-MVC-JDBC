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

public class DetalleFactura {
    private int detalleFacturasId;
    private int facturaId;
    private int productoID;
    private double cantidad;
    private double precioUnitario;

    // Constructor
    public DetalleFactura(int detalleFacturasId, int facturaId, int productoID, double cantidad, double precioUnitario) {
        this.detalleFacturasId = detalleFacturasId;
        this.facturaId = facturaId;
        this.productoID = productoID;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    // Métodos getter y setter

    public int getFacturaID() {
        return facturaID;
    }

    public void setFacturaID(int facturaID) {
        this.facturaID = facturaID;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<DetalleFactura> getDetalles() {
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
        }
        this.total = totalCalculado;
    }
}

