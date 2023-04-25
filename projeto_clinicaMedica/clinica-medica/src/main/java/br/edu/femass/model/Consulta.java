package br.edu.femass.model;

import java.util.Set;

public class Consulta {
    private Paciente paciente;
    private Medico medico;
    private Especialidade especialidade;
    private String data;
    private String hora;
    private boolean concluida;
    public Long idConsulta; 

    private static Long ultimoId= 0L;
    
    public Consulta(){}

    public Consulta(Paciente p, Medico m, Especialidade e, String d, String h)
    {
        this.paciente = p;
        this.medico = m;
        this.especialidade = e;
        this.data = d;
        this.hora = h;
        this.concluida = false;
        this.idConsulta = ultimoId+1;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public Long getIdConsulta() {
        return idConsulta;
    }

    public String getHora() {
        return hora;
    }


    public String getData() {
        return data;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }
    
    public static void atualizarUltimoId(Set<Consulta> consultas) {
        for (Consulta consulta: consultas) {
            if (consulta.getIdConsulta().longValue()>ultimoId)
                ultimoId = consulta.getIdConsulta();
        }
    }
    
    @Override
    public String toString() {
        return "id: " +idConsulta
        +", Dr "+medico.getNome()
        +", Paciente "
        +paciente.getNome()
        +", Data: "
        +data+" "
        +hora;
    }

    
    
}
