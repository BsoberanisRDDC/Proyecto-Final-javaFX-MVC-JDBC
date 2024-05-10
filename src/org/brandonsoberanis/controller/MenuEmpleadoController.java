package org.brandonsoberanis.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.brandonsoberanis.dao.Conexion;
import org.brandonsoberanis.model.Empleado;
import org.brandonsoberanis.system.Main;

public class MenuEmpleadoController implements Initializable {
    private Main stage;
    private int op;

    private static Connection conexion;
    private static PreparedStatement statement;
    private static ResultSet resultSet;

    @FXML
    TableView<Empleado> tblEmpleados;
    @FXML
    TableColumn<Empleado, Integer> colEmpleadoId;
    @FXML
    TableColumn<Empleado, String> colNombre;
    @FXML
    TableColumn<Empleado, String> colApellido;
    @FXML
    TableColumn<Empleado, Integer> colCargaId;
    @FXML
    TableColumn<Empleado, Integer> colEncargadoId;
    @FXML
    Button btnRegresar, btnAgregar, btnEditar, btnEliminar, btnBuscar;
    @FXML
    TextField tfEmpleadoId;

    @FXML
    ComboBox<Empleado> cmbEncargado;

    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnRegresar) {
            stage.menuPrincipalView();
        } else if (event.getSource() == btnAgregar) {
            stage.formEmpleadoView(1);
        } else if (event.getSource() == btnEditar) {
            Empleado empleadoSeleccionado = tblEmpleados.getSelectionModel().getSelectedItem();
            if (empleadoSeleccionado != null) {
                Empleado encargadoSeleccionado = cmbEncargado.getValue();
                if (encargadoSeleccionado != null) {
                    empleadoSeleccionado.setEncargadoID(encargadoSeleccionado.getEmpleadoId());
                    // Actualizar el empleado en la base de datos si es necesario
                    // Luego, cargar los datos nuevamente
                    cargarDatos();
                } else {
                    mostrarAlerta("Por favor seleccione un encargado.");
                }
            } else {
                mostrarAlerta("Por favor seleccione un empleado para editar.");
            }
        } else if (event.getSource() == btnEliminar) {
            eliminarEmpleado(tblEmpleados.getSelectionModel().getSelectedItem());
            cargarDatos();
        } else if (event.getSource() == btnBuscar) {
            tblEmpleados.getItems().clear();
            if (tfEmpleadoId.getText().equals("")) {
                cargarDatos();
            } else {
                op = 3;
                cargarDatos();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
    }

    public void cargarDatos() {
        if (op == 3) {
            // LLENAR LA TABLA CON EL EMPLEADO BUSCADO
            Empleado empleadoBuscado = buscarEmpleado(Integer.parseInt(tfEmpleadoId.getText()));
            if (empleadoBuscado != null) {
                tblEmpleados.getItems().add(empleadoBuscado);
            }
            op = 0;
        } else {
            tblEmpleados.setItems(listarEmpleados());
        }
        colEmpleadoId.setCellValueFactory(new PropertyValueFactory<>("empleadoId"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreEmpleado"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colCargaId.setCellValueFactory(new PropertyValueFactory<>("cargaId"));
        colEncargadoId.setCellValueFactory(new PropertyValueFactory<>("encargadoID"));
    }

    public ObservableList<Empleado> listarEmpleados() {
        ArrayList<Empleado> empleados = new ArrayList<>();

        try {
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "SELECT * FROM empleados";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int empleadoId = resultSet.getInt("empleadoId");
                String nombre = resultSet.getString("nombreEmpleado");
                String apellido = resultSet.getString("apellido");
                int cargaId = resultSet.getInt("cargaId");
                int encargadoID = resultSet.getInt("encargadoID");

                empleados.add(new Empleado(empleadoId, nombre, apellido, cargaId, encargadoID));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return FXCollections.observableList(empleados);
    }

    public void eliminarEmpleado(Empleado empleado) {
        try {
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "DELETE FROM empleados WHERE empleadoId = ?";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, empleado.getEmpleadoId());
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Empleado buscarEmpleado(int empleadoId) {
        Empleado empleado = null;
        try {
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "SELECT * FROM empleados WHERE empleadoId = ?";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, empleadoId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nombre = resultSet.getString("nombreEmpleado");
                String apellido = resultSet.getString("apellido");
                int cargaId = resultSet.getInt("cargaId");
                int encargadoID = resultSet.getInt("encargadoID");

                empleado = new Empleado(empleadoId, nombre, apellido, cargaId, encargadoID);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return empleado;
    }

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }

    // Método para mostrar una alerta
    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
