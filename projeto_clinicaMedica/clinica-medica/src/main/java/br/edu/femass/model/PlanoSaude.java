package br.edu.femass.model;

public class PlanoSaude {
    private String nomePlano;

    public PlanoSaude(){}

    public PlanoSaude(String nome)
    {
        this.nomePlano = nome;
    }

    public String getNomePlano() {
        return nomePlano;
    }

    public void setNomePlano(String nomePlano) {
        this.nomePlano = nomePlano;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nomePlano == null) ? 0 : nomePlano.hashCode());
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
        PlanoSaude other = (PlanoSaude) obj;
        if (nomePlano == null) {
            if (other.nomePlano != null)
                return false;
        } else if (!nomePlano.equals(other.nomePlano))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Plano:" + nomePlano;
    }

    
}
