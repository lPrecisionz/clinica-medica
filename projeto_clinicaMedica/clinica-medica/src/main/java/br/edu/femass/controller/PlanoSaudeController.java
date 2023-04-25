package br.edu.femass.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.femass.Diversos.DiversosJavaFx;
import br.edu.femass.dao.PlanoSaudeDao;
import br.edu.femass.model.PlanoSaude;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class PlanoSaudeController implements Initializable {

    PlanoSaudeDao planoDao = new PlanoSaudeDao();

    @FXML
    private TextField txt_plano;

    @FXML
    private ListView<PlanoSaude> listaPlanos;

    @FXML
    private void btn_adicionar(ActionEvent action)
    {
        try{
            PlanoSaude plano = new PlanoSaude(
                txt_plano.getText()
            );

            if(planoDao.gravar(plano) == false)
            {
                DiversosJavaFx.exibirMensagem("Nao foi possível cadastrar o plano");
                return;
            }
            exibirPlanos();
            txt_plano.setText("");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @FXML
    public void btn_excluir(ActionEvent action)
    {
        PlanoSaude plano = listaPlanos.getSelectionModel().getSelectedItem();
        if (plano==null) return;

        try {
            if (planoDao.excluir(plano) == false){
                DiversosJavaFx.exibirMensagem("Não foi possível excluir o plano selecionado");
            }
            exibirPlanos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void exibirPlanos()
    {
        try{
            ObservableList<PlanoSaude> data = FXCollections.observableArrayList(planoDao.buscar());
            listaPlanos.setItems(data);
        } catch (Exception ex){
        ex.printStackTrace();
        }
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exibirPlanos();
    }
}
