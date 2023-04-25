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

public class AtendenteController implements Initializable {

    @FXML
    private void btn_pacientes(ActionEvent action)
    {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Paciente.fxml"));
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            scene.getRoot().setStyle("-fx-font-family: 'serif'");
            Stage stage = new Stage();
            
            stage.setTitle("Clínica médica");
            stage.setScene(scene);
            stage.show();
            } catch (Exception ex){
                ex.printStackTrace();
            }
    }

    @FXML
    private void btn_medicos(ActionEvent action){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Medico.fxml"));
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            scene.getRoot().setStyle("-fx-font-family: 'serif'");
            Stage stage = new Stage();
            
            stage.setTitle("Médico");
            stage.setScene(scene);
            stage.show();
            } catch (Exception ex){
                ex.printStackTrace();
            }
    }

    @FXML
    private void btn_consultas(ActionEvent action)
    {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Consulta.fxml"));
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            scene.getRoot().setStyle("-fx-font-family: 'serif'");
            Stage stage = new Stage();
            
            stage.setTitle("Consultas");
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
