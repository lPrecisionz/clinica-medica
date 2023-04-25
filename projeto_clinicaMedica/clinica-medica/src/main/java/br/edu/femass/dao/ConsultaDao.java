package br.edu.femass.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.model.Consulta;

public class ConsultaDao extends Persist implements Dao<Consulta> {

    public ConsultaDao()
    {
        super("consultas.json");
    }

    @Override
    public boolean gravar(Consulta objeto) throws StreamWriteException, IOException {
        Set<Consulta> consultas = buscar();
        boolean gravou = consultas.add(objeto);

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, consultas);
        return gravou; 
    }

    @Override
    public boolean excluir(Consulta objeto) throws StreamWriteException, IOException {
        Set<Consulta> consultas = buscar();
        for (Consulta consultaSelecionada : consultas) {
            if (consultaSelecionada.equals(objeto)) 
                consultaSelecionada.setConcluida(true);
        }
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, consultas);
        return true;
    }


    @Override
    public Set<Consulta> buscar() throws DatabindException {
        try{
            Set<Consulta> consultas = objectMapper.readValue(arquivo, new TypeReference<Set<Consulta>>(){});
            return consultas;
        }
        catch(IOException ex)
        {
            return new HashSet<Consulta>(); // caso não haja pacientes ao abrir o arquivo, crio novo set
        }
    }

    @Override //buscar não concluidas
    public List<Consulta> buscarAtivos() throws DatabindException {
        Set<Consulta> consultas = buscar();
        List<Consulta> consultasAndamento = consultas
                .stream()
                .filter(consulta -> !consulta.isConcluida())
                .collect(Collectors.toList());

        return consultasAndamento;
    }
    
}
