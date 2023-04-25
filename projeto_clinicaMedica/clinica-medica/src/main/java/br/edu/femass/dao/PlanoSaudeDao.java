package br.edu.femass.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.model.PlanoSaude;

public class PlanoSaudeDao extends Persist implements Dao<PlanoSaude> {
    
    public PlanoSaudeDao()
    {
        super("planosSaude.json");
    }

    @Override
    public boolean gravar(PlanoSaude objeto) throws StreamWriteException, IOException {
        Set<PlanoSaude> planos = buscar();
        boolean gravou = planos.add(objeto);

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, planos);
        return gravou;
        
    }

    @Override
    public boolean excluir(PlanoSaude objeto) throws StreamWriteException, IOException {
        Set<PlanoSaude> planos = buscar();
        boolean removeu = planos.remove(objeto);

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, planos);

        return removeu;
    }

    @Override
    public Set<PlanoSaude> buscar() throws DatabindException {
        try{
            Set<PlanoSaude> planos = objectMapper.readValue(arquivo, new TypeReference<Set<PlanoSaude>>(){});
            return planos; 
        }
        catch(IOException ex)
        {
            return new HashSet<PlanoSaude>(); // caso não haja pacientes ao abrir o arquivo, crio novo set
        }
    }

    @Override
    public List<PlanoSaude> buscarAtivos() throws DatabindException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarAtivos'");
    }
}
