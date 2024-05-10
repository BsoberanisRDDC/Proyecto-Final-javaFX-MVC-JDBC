/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.brandonsoberanis.model;

/**
 *
 * @author JULIO SOBERANIS
 */
public class Empleado {
    private int empleadoId;
    private String nombreEmpleado;
    private String apellido;
    private int cargaId; // Cambiado a tipo int según su nombre, puede necesitar otro tipo dependiendo del contexto
    private int encargadoID; // Cambiado a tipo int según su nombre, puede necesitar otro tipo dependiendo del contexto

    public Empleado() {
    }

    public Empleado(int empleadoId, String nombreEmpleado, String apellido, int cargaId, int encargadoID) {
        this.empleadoId = empleadoId;
        this.nombreEmpleado = nombreEmpleado;
        this.apellido = apellido;
        this.cargaId = cargaId;
        this.encargadoID = encargadoID;
    }

    // Métodos getter y setter para acceder a los atributos privados

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getCargaId() {
        return cargaId;
    }

    public void setCargaId(int cargaId) {
        this.cargaId = cargaId;
    }

    public int getEncargadoID() {
        return encargadoID;
    }

    public void setEncargadoID(int encargadoID) {
        this.encargadoID = encargadoID;
    }
}
