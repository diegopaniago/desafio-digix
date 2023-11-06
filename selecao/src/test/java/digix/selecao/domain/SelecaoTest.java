package digix.selecao.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import digix.selecao.utils.CampoObrigatorioViolado;

public class SelecaoTest {

    private Faker faker = new Faker();

    @Test
    public void deve_criar_uma_selecao() {
        String participanteId = UUID.randomUUID().toString();
        String nomeDoCriterio = faker.harryPotter().spell();
        Integer pontosObtidos = faker.number().numberBetween(0, 10);
        CriterioSelecionado criterio = new CriterioSelecionado(nomeDoCriterio, pontosObtidos);
        List<CriterioSelecionado> criterios = Arrays.asList(criterio);

        Selecao selecao = new Selecao(participanteId, criterios);

        assertEquals(participanteId, selecao.getParticipanteId());
        assertEquals(criterios, selecao.getCriteriosSelecionados());
    }

    @Test
    public void nao_deve_criar_uma_selecao_sem_participante() {
        String erroEsperado = "É obrigatório informar o participante.";
        String participanteId = null;
        String nomeDoCriterio = faker.harryPotter().spell();
        Integer pontosObtidos = faker.number().numberBetween(0, 10);
        CriterioSelecionado criterio = new CriterioSelecionado(nomeDoCriterio, pontosObtidos);
        List<CriterioSelecionado> criterios = Arrays.asList(criterio);

        CampoObrigatorioViolado erro = assertThrows(CampoObrigatorioViolado.class, () -> {
            new Selecao(participanteId, criterios);
        });

        assertEquals(erroEsperado, erro.getMensagemDeErro());
    }

    @Test
    public void nao_deve_criar_uma_selecao_sem_criterios() {
        String erroEsperado = "É obrigatório informar os criterios da seleção.";
        String participanteId = UUID.randomUUID().toString();
        List<CriterioSelecionado> criterios = Collections.emptyList();

        CampoObrigatorioViolado erro = assertThrows(CampoObrigatorioViolado.class, () -> {
            new Selecao(participanteId, criterios);
        });

        assertEquals(erroEsperado, erro.getMensagemDeErro());
    }

    @Test
    public void deve_obter_total_de_pontos() {
        String participanteId = UUID.randomUUID().toString();
        String nomeDoCriterio = faker.harryPotter().spell();
        Integer pontosObtidos = faker.number().numberBetween(0, 10);
        CriterioSelecionado criterio = new CriterioSelecionado(nomeDoCriterio, pontosObtidos);
        List<CriterioSelecionado> criterios = Arrays.asList(criterio);

        Selecao selecao = new Selecao(participanteId, criterios);

        assertEquals(pontosObtidos, selecao.obterTotalDePontos());
    }
}
