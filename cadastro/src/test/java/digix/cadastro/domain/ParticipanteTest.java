package digix.cadastro.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import digix.cadastro.utils.CampoObrigatorioViolado;
import digix.cadastro.utils.builder.PessoaBuilder;

public class ParticipanteTest {

    @Test
    public void deve_criar_um_participante() {
        Pessoa titutar = new PessoaBuilder().criar();
        List<Pessoa> familia = Arrays.asList(new PessoaBuilder().criar());

        Participante participante = new Participante(titutar, familia);

        assertEquals(titutar, participante.getTitular());
        assertEquals(familia, participante.getFamilia());
    }

    @Test
    public void nao_deve_criar_um_participante_sem_titular() {
        String erroEsperado = "É obrigatório informar os dados do participante titular.";
        Pessoa titutar = null;
        List<Pessoa> familia = Arrays.asList(new PessoaBuilder().criar());

        CampoObrigatorioViolado erro = assertThrows(CampoObrigatorioViolado.class, () -> {
            new Participante(titutar, familia);
        });

        assertEquals(erroEsperado, erro.getMensagemDeErro());
    }
}
