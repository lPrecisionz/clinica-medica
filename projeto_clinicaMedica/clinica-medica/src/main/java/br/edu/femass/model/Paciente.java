package br.edu.femass.model;

import java.util.Set;

import br.edu.femass.Diversos.ValidadorCPF;

public class Paciente {
    
    private String cpf;
    private String nome;
    private String email;
    private Long id;
    private PlanoSaude planoSaude; 
    private boolean ativo; 

    public static Long ultimoId = 0L; 

    public Paciente()
    {
    }


    /*OBS: Não criei uma classe Pessoa, pois por exigência do json do construtor vazio não sei como puxar um super sem 
     * sacrificar o json
     */
    public Paciente(String nome, String cpf, String email, PlanoSaude plano)
    {
        if(ValidadorCPF.validarCPF(cpf) == false) throw new IllegalArgumentException("CPF Inválido");
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.planoSaude = plano;
        this.ativo = true; 
        this.id = ultimoId+1;
    }

    @Override
    public String toString() {
        //return "nome=" + nome + ", id=" + id + ", planoSaude=" + planoSaude + "]";
        return ""+nome+"; id: "+id+"; Plano: "+planoSaude;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
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
        Paciente other = (Paciente) obj;
        if (cpf == null) {
            if (other.cpf != null)
                return false;
        } else if (!cpf.equals(other.cpf))
            return false;
        return true;
    }

    public static void atualizarUltimoId(Set<Paciente> pacientes) {
        for (Paciente paciente: pacientes) {
            if (paciente.getId().longValue()>ultimoId)
                ultimoId = paciente.getId();
        }
    }

    //Getters 
    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public PlanoSaude getPlanoSaude() {
        return planoSaude;
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

    public void setPlanoSaude(PlanoSaude ps) {
        this.planoSaude = ps;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
