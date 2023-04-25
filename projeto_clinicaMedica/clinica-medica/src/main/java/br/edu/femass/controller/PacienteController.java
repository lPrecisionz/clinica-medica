package br.edu.femass.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import br.edu.femass.Diversos.DiversosJavaFx;
import br.edu.femass.dao.PacienteDao;
import br.edu.femass.dao.PlanoSaudeDao;
import br.edu.femass.model.Paciente;
import br.edu.femass.model.PlanoSaude;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class PacienteController implements Initializable {

    private PacienteDao pacienteDao = new PacienteDao();
    private PlanoSaudeDao planoDao = new PlanoSaudeDao();

    @FXML
    private TextField txt_nome;

    @FXML
    private TextField txt_cpf;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_plano;

    @FXML
    private ListView<Paciente> listaPacientes;

    @FXML
    public void btn_cadastrar(ActionEvent event)
    {
        try{

            //Verifico se tem o plano selecionado no banco de dados
            String nomePlano = txt_plano.getText();
            Set<PlanoSaude> planos = planoDao.buscar();

            PlanoSaude plano = planos.stream()
            .filter(e -> e.getNomePlano()
            .equals(nomePlano))
            .findFirst()
            .orElse(null);

            //caso não encontrou retorno erro
            if(plano == null)
            {
                DiversosJavaFx.exibirMensagem(
                    "O sistema não suporta esta especialidade");
                    return;
            }

            Paciente paciente = new Paciente(
                txt_nome.getText(),
                txt_cpf.getText(),
                txt_email.getText(),
                new PlanoSaude(nomePlano)
            );

            if(pacienteDao.gravar(paciente) == false){
                DiversosJavaFx.exibirMensagem("Não foi possível cadastrar o paciente");
                return;
            }
            Paciente.ultimoId++;
            exibirPacientes();
            resetarCamposTexto();
        } catch(Exception ex){
            DiversosJavaFx.exibirMensagem(ex.getMessage());
        }
    }

    @FXML
    public void btn_excluir(ActionEvent event)
    {
        Paciente paciente = listaPacientes.getSelectionModel().getSelectedItem();
        if (paciente==null) return;

        try {
            if (pacienteDao.excluir(paciente)==false) {
                DiversosJavaFx.exibirMensagem("Não foi possível excluir o cliente selecionado");
            }
        exibirPacientes();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void exibirPacientes(){
        try{
            ObservableList<Paciente> data = FXCollections.observableArrayList(pacienteDao.buscarAtivos());
            listaPacientes.setItems(data);
        } catch (Exception ex){
        ex.printStackTrace();
        }
    }

    @FXML
    public void resetarCamposTexto()
    {
        txt_cpf.setText("");
        txt_email.setText("");
        txt_nome.setText("");
        txt_plano.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exibirPacientes();
    }
    
}
