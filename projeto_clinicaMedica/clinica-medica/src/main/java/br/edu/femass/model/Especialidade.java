package br.edu.femass.model;

public class Especialidade {
    public String nomeEspecialidade;

    public Especialidade()
    {
        
    }

    public Especialidade(String nome)
    {
        this.nomeEspecialidade = nome;
    }

    public String getNomeEspecialidade() {
        return nomeEspecialidade;
    }

    @Override
    public String toString() {
        return "" + nomeEspecialidade;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nomeEspecialidade == null) ? 0 : nomeEspecialidade.hashCode());
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
        Especialidade other = (Especialidade) obj;
        if (nomeEspecialidade == null) {
            if (other.nomeEspecialidade != null)
                return false;
        } else if (!nomeEspecialidade.equals(other.nomeEspecialidade))
            return false;
        return true;
    }

    
}
