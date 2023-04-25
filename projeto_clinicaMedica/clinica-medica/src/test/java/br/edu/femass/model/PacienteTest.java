package br.edu.femass.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class PacienteTest {
    
    Paciente paciente;

    @Test
    void construtorCpfIncorreto()
    {
        assertThrows(IllegalArgumentException.class, () -> new Paciente(
            "Pedro",
            "47440440851",
            "pedro@gmail.com",
            new PlanoSaude("Privado")
            )
        );
    }
}
