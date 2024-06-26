package org.brandonsoberanis.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.brandonsoberanis.dao.Conexion;
import org.brandonsoberanis.model.Cliente;
import org.brandonsoberanis.model.Factura;
import org.brandonsoberanis.model.TicketSoporte;
import org.brandonsoberanis.system.Main;

public class MenuTicketSoporteController implements Initializable {
    private Main stage;
    
    private Connection conexion = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;
    
    @FXML
    TextField tfTicketId;
    
    @
    FXML
    TextArea taDescripcion;
    
    @FXML
    ComboBox cmbEstatus, cmbCliente, cmbFactura;
    
    @FXML
    TableView tblTickets;
    
    @FXML
    TableColumn colTicketId, colDescripcion, colEstatus, colCliente, colFacturaId;
    
    @FXML
    Button btnGuardar, btnVaciar, btnRegresar;
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnRegresar){
            stage.menuPrincipalView();
        }else if(event.getSource() == btnGuardar){
            if(tfTicketId.getText().equals("")){
                agregarTicket();
                cargarDatos();
            }else{
                editarTicket();
                cargarDatos();
            }
        }else if(event.getSource() == btnVaciar){
            vaciarForm();
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
        cargarCmbEstatus();
        cmbCliente.setItems(listarClientes());
        cmbFactura.setItems(listarFacturas());
    }
    
    public void cargarDatos(){
        tblTickets.setItems(listarTickets());
        colTicketId.setCellValueFactory(new PropertyValueFactory<TicketSoporte, Integer>("ticketSoporteId"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<TicketSoporte, String>("descripcion"));
        colEstatus.setCellValueFactory(new PropertyValueFactory<TicketSoporte, String>("estatus"));
        colCliente.setCellValueFactory(new PropertyValueFactory<TicketSoporte, String>("cliente"));
        colFacturaId.setCellValueFactory(new PropertyValueFactory<TicketSoporte, String>("factura"));
    }
    
    public void cargarCmbEstatus(){
        cmbEstatus.getItems().add("En proceso");
        cmbEstatus.getItems().add("Finalizado");
    }
    
    public void vaciarForm(){
        tfTicketId.clear();
        taDescripcion.clear();
        cmbEstatus.getSelectionModel().clearSelection();
        cmbCliente.getSelectionModel().clearSelection();
        cmbFactura.getSelectionModel().clearSelection();
    }
    
    @FXML
    public void cargarForm(){
        TicketSoporte ts = (TicketSoporte)tblTickets.getSelectionModel().getSelectedItem();
        if(ts != null){
            tfTicketId.setText(Integer.toString(ts.getTicketSoporteId()));
            taDescripcion.setText(ts.getDescripcion());
            cmbEstatus.getSelectionModel().select(0);
            cmbCliente.getSelectionModel().select(obtenerIndexCliente());
            cmbFactura.getSelectionModel().select(obtenerIndexFactura());
        }
    }
    
    public int obtenerIndexCliente(){
        int index = 0;
        String clienteTbl = ((TicketSoporte)tblTickets.getSelectionModel().getSelectedItem()).getCliente();
        for(int i = 0 ; i <= cmbCliente.getItems().size() ; i++){
            String clienteCmb = cmbCliente.getItems().get(i).toString();
            
            if(clienteTbl.equals(clienteCmb)){
                index = i;
                break;
            }
        }
        
        return index;
    }
    
    public int obtenerIndexFactura(){
        int index = 0;
        String facturaTbl = ((TicketSoporte)tblTickets.getSelectionModel().getSelectedItem()).getFactura();
        for(int i = 0 ; i <= cmbFactura.getItems().size() ; i++){
            String facturaCmb = cmbFactura.getItems().get(i).toString();
            
            if(facturaTbl.equals(facturaCmb)){
                index = i;
                break;
            }
        }
        
        return index;
    }
    
    public ObservableList<TicketSoporte> listarTickets(){
        ArrayList<TicketSoporte> tickets = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_listarTicketSoporte()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int ticketId = resultSet.getInt("ticketSoporteId");
                String descripcion = resultSet.getString("descripcionTicket");
                String estatus = resultSet.getString("estatus");
                String cliente = resultSet.getString("cliente");
                String factura = resultSet.getString("factura");
                
                tickets.add(new TicketSoporte(ticketId, descripcion, estatus, cliente, factura));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                }
                if(statement != null){
                    statement.close();
                }
                if(conexion != null){
                    conexion.close();
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return FXCollections.observableList(tickets);
    }
    
    public ObservableList<Cliente> listarClientes(){
        ArrayList<Cliente> clientes = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_ListarClientes()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int clienteId = resultSet.getInt("clienteId");
                String nombre = resultSet.getString("nombre");  
                String apellido = resultSet.getString("apellido");
                String telefono = resultSet.getString("telefono");
                String nit = resultSet.getString("nit");
                String direccion = resultSet.getString("direccion");
                
                clientes.add(new Cliente(clienteId, nombre, apellido, telefono, nit, direccion));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                }
                if(statement != null){
                    statement.close();
                }
                if(conexion != null){
                    conexion.close();
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        
        return FXCollections.observableList(clientes);
    }
    
    public ObservableList<Factura> listarFacturas(){
        ArrayList<Factura> facturas = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_listarFacturas()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int facturaId = resultSet.getInt("facturaId");
                Date fecha = resultSet.getDate("fecha");
                Time hora = resultSet.getTime("hora");
                String cliente = resultSet.getString("cliente");
                String empleado = resultSet.getString("empleado");
                double total = resultSet.getDouble("total");
                facturas.add(new Factura(facturaId, fecha, hora, cliente, empleado, total));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                }
                if(statement != null){
                    statement.close();
                }
                if(conexion != null){
                    conexion.close();
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        
        return FXCollections.observableList(facturas);
    }
    
    public void agregarTicket(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_agregarTicketSoporte(?, ?, ?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, taDescripcion.getText());
            statement.setInt(2, ((Cliente)cmbCliente.getSelectionModel().getSelectedItem()).getClienteId());
            statement.setInt(3, ((Factura)cmbFactura.getSelectionModel().getSelectedItem()).getFacturaId());
            statement.execute();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(statement!= null){
                    statement.close();
                }
                if(conexion != null){
                    conexion.close();
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
    
    public void editarTicket(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_editarTicketSoporte(?, ?, ?, ?, ?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfTicketId.getText()));
            statement.setString(2, taDescripcion.getText());
            statement.setString(3, (cmbEstatus.getSelectionModel().getSelectedItem().toString()));
            statement.setInt(4, ((Cliente)cmbCliente.getSelectionModel().getSelectedItem()).getClienteId());
            statement.setInt(5, ((Factura)cmbFactura.getSelectionModel().getSelectedItem()).getFacturaId());
            statement.execute();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(statement != null){
                    statement.close();
                }
                if(conexion != null){
                    conexion.close();
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
    
}
