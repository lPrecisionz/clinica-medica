package br.edu.femass.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController implements Initializable {
    
    @FXML
    private void btn_administrador (ActionEvent event) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Administrador.fxml"));
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            scene.getRoot().setStyle("-fx-font-family: 'serif'");
            Stage stage = new Stage();
            
            stage.setTitle("Administrador");
            stage.setScene(scene);
            stage.show();
            } catch (Exception ex){
                ex.printStackTrace();
            }
    }

    @FXML
    private void btn_atendente(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Atendente.fxml"));
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            scene.getRoot().setStyle("-fx-font-family: 'serif'");
            Stage stage = new Stage();
            
            stage.setTitle("Atendente");
            stage.setScene(scene);
            stage.show();
            } catch (Exception ex){
                ex.printStackTrace();
            }
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
