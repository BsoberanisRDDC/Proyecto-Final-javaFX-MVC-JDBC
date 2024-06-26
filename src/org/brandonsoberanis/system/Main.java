package org.brandonsoberanis.system;

import java.io.InputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.brandonsoberanis.controller.FormCargoController;
import org.brandonsoberanis.controller.FormCategoriaProductoController;
import org.brandonsoberanis.controller.FormClienteController;
import org.brandonsoberanis.controller.FormCompraController;
import org.brandonsoberanis.controller.FormDetalleCompraController;
import org.brandonsoberanis.controller.FormDetalleFacturaController;
import org.brandonsoberanis.controller.FormDistribuidorController;
import org.brandonsoberanis.controller.FormFacturaController;
import org.brandonsoberanis.controller.FormUsuarioController;
import org.brandonsoberanis.controller.MenuCargoController;
import org.brandonsoberanis.controller.MenuCategoriaProductoController;
import org.brandonsoberanis.controller.MenuClienteController;
import org.brandonsoberanis.controller.MenuCompraController;
import org.brandonsoberanis.controller.MenuDistribuidorController;
import org.brandonsoberanis.controller.MenuEmpleadoController;
import org.brandonsoberanis.controller.MenuFacturaController;
import org.brandonsoberanis.controller.MenuLoginController;
import org.brandonsoberanis.controller.MenuPrincipalController;
import org.brandonsoberanis.controller.MenuProductoController;
import org.brandonsoberanis.controller.MenuPromocionController;
import org.brandonsoberanis.controller.MenuTicketSoporteController;

public class Main extends Application {
    private final String URLVIEW = "/org/brandonsoberanis/view/";
    private Stage stage;
    private Scene scene;
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle("Super Kinal S.A");
        menuLoginView();
        stage.show();
    }
    
    public Initializable switchScene(String fxmlName, int width, int height) throws Exception{
        Initializable resultado = null;
        FXMLLoader loader = new FXMLLoader();
        
        InputStream file = Main.class.getResourceAsStream(URLVIEW + fxmlName);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(URLVIEW + fxmlName));
        
        scene = new Scene((AnchorPane)loader.load(file), width, height);
        stage.setScene(scene);
        stage.sizeToScene();
        
        resultado = (Initializable)loader.getController();
        
        return resultado;
    }
    
    public void menuPrincipalView(){
        try{
            MenuPrincipalController menuPrincipalView = (MenuPrincipalController)switchScene("MenuPrincipalView.fxml", 900, 600);
            menuPrincipalView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuClienteView(){
        try{
            MenuClienteController menuClienteView = (MenuClienteController)switchScene("MenuClienteView.fxml", 1200, 750);
            menuClienteView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formClienteView(int op){
        try{
            FormClienteController formClienteView = (FormClienteController)switchScene("FormClienteView.fxml", 500, 750);
            formClienteView.setOp(op);
            formClienteView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuTicketSoporteView(){
        try{
            MenuTicketSoporteController menuTicketSoporteView = (MenuTicketSoporteController)switchScene("MenuTicketSoporteView.fxml", 1200, 750);
            menuTicketSoporteView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuCargoView(){
        try{
            MenuCargoController menuCargoView = (MenuCargoController)switchScene("MenuCargoView.fxml", 1200, 750);
            menuCargoView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formCargoView(int op){
        try{
            FormCargoController formCargoView = (FormCargoController)switchScene("FormCargoView.fxml", 500, 750);
            formCargoView.setOp(op);
            formCargoView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void menuCompraView(){
        try{
            MenuCompraController menuCompraView = (MenuCompraController)switchScene("MenuCompraView.fxml", 1200, 750);
            menuCompraView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formCompraView(int op){
        try{
            FormCompraController formCompraView = (FormCompraController)switchScene("FormCompraView.fxml", 500, 750);
            formCompraView.setOp(op);
            formCompraView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formDetalleCompraView(int op){
        try{
            FormDetalleCompraController formDetalleCompraView = (FormDetalleCompraController)switchScene("FormDetalleCompraView.fxml", 500, 750);
            formDetalleCompraView.setOp(op);
            formDetalleCompraView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuDistribuidorView(){
        try{
            MenuDistribuidorController menuDistribuidorView = (MenuDistribuidorController)switchScene("MenuDistribuidorView.fxml", 1200, 750);
            menuDistribuidorView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formDistribuidorView(int op){
        try{
            FormDistribuidorController formDistribuidorView = (FormDistribuidorController)switchScene("FormDistribuidorView.fxml", 500, 750);
            formDistribuidorView.setOp(op);
            formDistribuidorView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuCategoriaProductoView(){
        try{
            MenuCategoriaProductoController menuCategoriaProductoView = (MenuCategoriaProductoController)switchScene("MenuCategoriaProductoView.fxml", 1200, 750);
            menuCategoriaProductoView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formCategoriaProductoView(int op){
        try{
            FormCategoriaProductoController formCategoriaProductoView = (FormCategoriaProductoController)switchScene("FormCategoriaProductoView.fxml", 500, 750);
            formCategoriaProductoView.setOp(op);
            formCategoriaProductoView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuEmpleadoView(int op){
        try{
            MenuEmpleadoController menuEmpleadoView = (MenuEmpleadoController)switchScene("MenuEmpleadoView.fxml", 1200, 750);
            menuEmpleadoView.setOp(op);
            menuEmpleadoView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuFacturaView(){
        try{
            MenuFacturaController menuFacturaView = (MenuFacturaController)switchScene("MenuFacturaView.fxml", 1200, 750);
            menuFacturaView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formFacturaView(int op){
        try{
            FormFacturaController formFacturaView = (FormFacturaController)switchScene("FormFacturaView.fxml", 500, 750);
            formFacturaView.setOp(op);
            formFacturaView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formDetalleFacturaView(int op){
        try{
            FormDetalleFacturaController formDetalleFacturaView = (FormDetalleFacturaController)switchScene("FormDetalleFacturaView.fxml", 500, 750);
            formDetalleFacturaView.setOp(op);
            formDetalleFacturaView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuProductoView(){
        try{
            MenuProductoController menuProductoView = (MenuProductoController)switchScene("MenuProductoView.fxml", 1200, 750);
            menuProductoView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuPromocionView(){
        try{
            MenuPromocionController menuPromocionView = (MenuPromocionController)switchScene("MenuPromocionView.fxml", 1200, 750);
            menuPromocionView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formUsuarioView(){
        try{
            FormUsuarioController formUsuarioView = (FormUsuarioController)switchScene("FormUsuarioView.fxml", 500, 750);
            formUsuarioView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuLoginView(){
        try{
            MenuLoginController MenuLoginView = (MenuLoginController)switchScene("MenuLoginView.fxml", 500, 750);
            MenuLoginView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
  
}
