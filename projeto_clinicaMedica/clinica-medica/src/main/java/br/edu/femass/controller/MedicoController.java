package br.edu.femass.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import com.fasterxml.jackson.core.exc.StreamWriteException;

import br.edu.femass.Diversos.DiversosJavaFx;
import br.edu.femass.dao.EspecialidadeDao;
import br.edu.femass.dao.MedicoDao;
import br.edu.femass.model.Especialidade;
import br.edu.femass.model.Medico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class MedicoController implements Initializable {

    MedicoDao medicoDao = new MedicoDao();
    EspecialidadeDao especialidadeDao = new EspecialidadeDao();

    @FXML
    private TextField txt_nome;

    @FXML
    private TextField txt_cpf;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_especialidade;

    @FXML
    private TextField txt_novaEspecialidade;

    @FXML
    private ListView<Medico> listaMedicos;

    @FXML
    private void btn_gravarMedico(ActionEvent action)
    {
        try{
            String nomeEspecialidade = txt_especialidade.getText();
            Set<Especialidade> especialidades = especialidadeDao.buscar();

            Especialidade especialidade = especialidades.stream()
            .filter(e -> e.getNomeEspecialidade()
            .equals(nomeEspecialidade))
            .findFirst()
            .orElse(null);

            if(especialidade == null)
            {
                DiversosJavaFx.exibirMensagem(
                    "O sistema não suporta esta especialidade");
                    return;
            }

            Medico medico = new Medico(
                txt_nome.getText(),
                txt_cpf.getText(),
                txt_email.getText(),
                new Especialidade(txt_especialidade.getText())
                );

            if(medicoDao.gravar(medico) == false){
                DiversosJavaFx.exibirMensagem("Não foi possível cadastrar o médico");
                return;
            }
            Medico.ultimoId++;
            exibirMedicos();
            resetarCamposTexto();
        } catch(Exception ex){
            DiversosJavaFx.exibirMensagem(ex.getMessage());
        }
    }

    @FXML
    private void btn_adicionarEspecialidade(ActionEvent action) throws StreamWriteException, IOException
    {
        System.out.println("adicionar especialidade");
        /*//1 - verifico se a especialidade digitada existe no banco de dados
        String nomeEspecialidade = txt_novaEspecialidade.getText();
            Set<Especialidade> especialidades = especialidadeDao.buscar();

            Especialidade especialidade = especialidades.stream()
            .filter(e -> e.getNomeEspecialidade()
            .equals(nomeEspecialidade))
            .findFirst()
            .orElse(null);
        //Se não existe, dou erro
        if(especialidade == null)
        {
            DiversosJavaFx.exibirMensagem("O sistema não suporta esta especialidade");
            return;
        }
        //Adiciono nova especialidade ao médico selecionado
        Medico medico = listaMedicos.getSelectionModel().getSelectedItem();
        medico.especialidades.add(especialidade);

        //puxo o set de médicos do json
        Set<Medico> medicos = medicoDao.buscar();
        //removo o médico selecionado do json
        medicos.removeIf(m -> m.getId() == medico.getId());
        //adiciono o médico atualizado e salvo no json
        medicos.add(medico);

        

        exibirMedicos();
        */
    } 

    @FXML
    private void btn_excluirMedico(ActionEvent action)
    {
        Medico medico = listaMedicos.getSelectionModel().getSelectedItem();
        if (medico==null) return;

        try {
            if (medicoDao.excluir(medico)==false) {
                DiversosJavaFx.exibirMensagem("Não foi possível excluir o médico selecionado");
            }
        exibirMedicos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 

    @FXML
    private void exibirMedicos()
    {
        try{
            ObservableList<Medico> data = FXCollections.observableArrayList(medicoDao.buscarAtivos());
            listaMedicos.setItems(data);
        } catch (Exception ex){
        ex.printStackTrace();
        }
    }

    @FXML
    private void resetarCamposTexto()
    {
        txt_nome.setText("");
        txt_cpf.setText("");
        txt_email.setText("");
        txt_especialidade.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exibirMedicos();
    }
    
}
