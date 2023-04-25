package br.edu.femass.model;

import java.util.HashSet;
import java.util.Set;

public class Medico {
    private String nome;
    private String cpf;
    private String email;
    private boolean ativo;
    public Set<Especialidade> especialidades; //usando set pra evitar repetição
    private Long id; 
    public static Long ultimoId = 0L;

    public Medico(){}


    public Medico(String nome, String cpf, String email, Especialidade especialidade)
    {  
        this.nome = nome;
        this.cpf = cpf; 
        this.email = email;
        this.especialidades = new HashSet<>();
        this.especialidades.add(especialidade);
        this.id = ultimoId + 1; 
        this.ativo = true; 
    }

    //Getters
    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public Set<Especialidade> getEspecialidades() {
        return especialidades;
    }

    public Long getId() {
        return id;
    }
    

    public boolean isAtivo() {
        return ativo;
    }

    //Setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setEspecialidades(Set<Especialidade> especialidades) {
        this.especialidades = especialidades;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    public static void atualizarUltimoId(Set<Medico> medicos) {
        for (Medico medico: medicos) {
            if (medico.getId().longValue()>ultimoId)
                ultimoId = medico.getId();
        }
    }


    @Override
    public String toString() {
        return ""+id+": "+nome+", "+getEspecialidades();
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Medico other = (Medico) obj;
        if (cpf == null) {
            if (other.cpf != null)
                return false;
        } else if (!cpf.equals(other.cpf))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        return true;
    }

    


}
