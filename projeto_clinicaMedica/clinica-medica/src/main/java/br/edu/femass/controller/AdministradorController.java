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

public class AdministradorController implements Initializable {


    @FXML
    private void btn_especialidades(ActionEvent action)
    {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Especialidade.fxml"));
            
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
    private void btn_planosSaude(ActionEvent action)
    {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/PlanoSaude.fxml"));
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            scene.getRoot().setStyle("-fx-font-family: 'serif'");
            Stage stage = new Stage();
            
            stage.setTitle("Planos de Saúde");
            stage.setScene(scene);
            stage.show();
            } catch (Exception ex){
                ex.printStackTrace();
            }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
}
