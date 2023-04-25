package br.edu.femass.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.model.Especialidade;

public class EspecialidadeDao extends Persist implements Dao<Especialidade>{

    public EspecialidadeDao() {
        super("especialidades.json");
    }

    @Override
    public boolean gravar(Especialidade objeto) throws StreamWriteException, IOException {
        Set<Especialidade> especialidades = buscar();
        boolean gravou = especialidades.add(objeto);

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, especialidades);
        return gravou;
    }

    @Override
    public boolean excluir(Especialidade objeto) throws StreamWriteException, IOException {
        Set<Especialidade> especialidades = buscar();
        boolean removeu = especialidades.remove(objeto);

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, especialidades);

        return removeu;
    }

    @Override
    public Set<Especialidade> buscar() {
        try{
            Set<Especialidade> especialidades = objectMapper.readValue(arquivo, new TypeReference<Set<Especialidade>>(){});
            return especialidades; 
        }
        catch(IOException ex)
        {
            return new HashSet<Especialidade>(); // caso não haja pacientes ao abrir o arquivo, crio novo set
        }
    }

    @Override
    public List<Especialidade> buscarAtivos() throws DatabindException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarAtivos'");
    }

    
    
}
