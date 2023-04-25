package br.edu.femass.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.femass.Diversos.DiversosJavaFx;
import br.edu.femass.dao.EspecialidadeDao;
import br.edu.femass.model.Especialidade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class EspecialidadeController implements Initializable {

    private EspecialidadeDao especialidadeDao = new EspecialidadeDao();

    @FXML
    private TextField txt_especialidade;

    @FXML
    private ListView<Especialidade> listaEspecialidades;

    @FXML
    public void btn_adicionar(ActionEvent action)
    {
        try{
            Especialidade especialidade = new Especialidade(
                txt_especialidade.getText()
            );

            if(especialidadeDao.gravar(especialidade) == false)
            {
                DiversosJavaFx.exibirMensagem("Nao foi possível cadastrar a especialidade");
                return;
            }
            exibirEspecialidades();
            txt_especialidade.setText("");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @FXML
    public void btn_excluir(ActionEvent action)
    {
        Especialidade especialidade = listaEspecialidades.getSelectionModel().getSelectedItem();
        if (especialidade==null) return;

        try {
            if (especialidadeDao.excluir(especialidade) == false){
                DiversosJavaFx.exibirMensagem("Não foi possível excluir a especialidade selecionada");
            }
            exibirEspecialidades();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void exibirEspecialidades()
    {
        try{
            ObservableList<Especialidade> data = FXCollections.observableArrayList(especialidadeDao.buscar());
            listaEspecialidades.setItems(data);
        } catch (Exception ex){
        ex.printStackTrace();
        }
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exibirEspecialidades();
    }
    
}
