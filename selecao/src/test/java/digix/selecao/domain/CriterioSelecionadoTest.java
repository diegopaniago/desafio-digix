package digix.selecao.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import digix.selecao.utils.CampoObrigatorioViolado;

public class CriterioSelecionadoTest {
    
    private Faker faker = new Faker();

    @Test
    public void deve_criar_um_criterio_selecionado() {
        String nome = faker.harryPotter().spell();
        Integer pontosObtidos = faker.number().numberBetween(0, 10);

        CriterioSelecionado criterioSelecionado = new CriterioSelecionado(nome, pontosObtidos);

        assertEquals(nome, criterioSelecionado.getNome());
        assertEquals(pontosObtidos, criterioSelecionado.getPontosObtidos());
    }

    @Test
    public void nao_deve_criar_um_criterio_selecionado_sem_nome() {
        String erroEsperado = "O nome do criterio selecionado é obrigatório.";
        String nome = null;
        Integer pontosObtidos = faker.number().numberBetween(0, 10);

        CampoObrigatorioViolado erro = assertThrows(CampoObrigatorioViolado.class, () -> {
            new CriterioSelecionado(nome, pontosObtidos);
        });

        assertEquals(erroEsperado, erro.getMensagemDeErro());
    }

    @Test
    public void nao_deve_criar_um_criterio_selecionado_sem_pontos_obtidos() {
        String erroEsperado = "A quantidade de pontos obtidos precisa ser maior ou igual a zero.";
        String nome = faker.harryPotter().spell();
        Integer pontosObtidos = null;

        CampoObrigatorioViolado erro = assertThrows(CampoObrigatorioViolado.class, () -> {
            new CriterioSelecionado(nome, pontosObtidos);
        });

        assertEquals(erroEsperado, erro.getMensagemDeErro());
    }

    @Test
    public void nao_deve_criar_um_criterio_selecionado_com_pontos_obtidos_negativos() {
        String erroEsperado = "A quantidade de pontos obtidos precisa ser maior ou igual a zero.";
        String nome = faker.harryPotter().spell();
        Integer pontosObtidos = -1;

        CampoObrigatorioViolado erro = assertThrows(CampoObrigatorioViolado.class, () -> {
            new CriterioSelecionado(nome, pontosObtidos);
        });

        assertEquals(erroEsperado, erro.getMensagemDeErro());
    }
}
