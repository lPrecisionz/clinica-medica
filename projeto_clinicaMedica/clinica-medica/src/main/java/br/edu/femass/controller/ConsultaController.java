package br.edu.femass.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.Diversos.DiversosJavaFx;
import br.edu.femass.dao.ConsultaDao;
import br.edu.femass.dao.EspecialidadeDao;
import br.edu.femass.dao.MedicoDao;
import br.edu.femass.dao.PacienteDao;
import br.edu.femass.model.Consulta;
import br.edu.femass.model.Especialidade;
import br.edu.femass.model.Medico;
import br.edu.femass.model.Paciente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ConsultaController implements Initializable {
    //Vou precisar ir nos json pra popular os comboBox, por isso instancio estes Daos
    ConsultaDao consultaDao = new ConsultaDao();
    EspecialidadeDao especialidadeDao = new EspecialidadeDao();
    MedicoDao medicoDao = new MedicoDao();
    PacienteDao pacienteDao = new PacienteDao();

    @FXML
    private TextField txt_data;

    @FXML
    private ComboBox<Especialidade> combo_especialidade;

    @FXML
    private ComboBox<Medico> combo_medico;

    @FXML
    private ComboBox<Medico> combo_medico2;

    @FXML
    private ComboBox<Paciente> combo_paciente;

    @FXML
    private TextField txt_hora;

    @FXML
    private ListView<Consulta> listaConsultas; 

    @FXML
    private void btn_agendar(ActionEvent action) throws StreamWriteException, IOException
    {
        Consulta consulta = new Consulta(
            combo_paciente.getSelectionModel().getSelectedItem(),
            combo_medico.getSelectionModel().getSelectedItem(),
            combo_especialidade.getSelectionModel().getSelectedItem(),
            txt_data.getText(),
            txt_hora.getText()
        );
        
        if(consultaDao.gravar(consulta) == false)
        {
            DiversosJavaFx.exibirMensagem("Não foi possível agendar a consulta");
            return;
        }


    }

    @FXML
    private void btn_comboEspecialidade(MouseEvent event) throws DatabindException{
        Set<Especialidade> especialidades = especialidadeDao.buscar();
        
        ObservableList<Especialidade> especialidadesList = FXCollections
        .observableArrayList(especialidades);
        combo_especialidade.setItems(especialidadesList);

    }

    @FXML
    private void btn_comboMedico(MouseEvent event) throws DatabindException{
            //seto a especialidade selecionada numa variável
            Especialidade especialidadeSelecionada = combo_especialidade.getSelectionModel().getSelectedItem();
            if (especialidadeSelecionada==null) return;

            //agora que tenho a especialidade, populo o comboBox com os médicos
            ObservableList<Medico> medicosEspecialistas = FXCollections
            .observableArrayList(medicoDao.buscaEspecialistas(especialidadeSelecionada));
            combo_medico.setItems(medicosEspecialistas);
        
    }

    @FXML
    private void btn_comboMedico2(MouseEvent event) throws DatabindException
    {
        List<Medico> medicos = medicoDao.buscarAtivos();
        ObservableList<Medico> listaMedicos = FXCollections
        .observableArrayList(medicos);
        combo_medico2.setItems(listaMedicos);

    }

    @FXML
    private void btn_comboPaciente(MouseEvent event) throws DatabindException{
        List<Paciente> pacientes = pacienteDao.buscarAtivos();
        ObservableList<Paciente> pacientesList = FXCollections
        .observableArrayList(pacientes);

        combo_paciente.setItems(pacientesList);
    }

    @FXML
    private void exibeConsultas() throws DatabindException
    {
        Medico medico = combo_medico2.getSelectionModel().getSelectedItem();
        Set<Consulta> consultas = consultaDao.buscar();

        List<Consulta> consultasMedico = consultas
        .stream()
        .filter(consulta -> consulta.getMedico().equals(medico))
        .collect(Collectors.toList());

        ObservableList<Consulta> data = FXCollections.observableArrayList(consultasMedico);
            listaConsultas.setItems(data);

    }

    @FXML
    private void btn_exibir(ActionEvent action) throws DatabindException{
        exibeConsultas();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
}
