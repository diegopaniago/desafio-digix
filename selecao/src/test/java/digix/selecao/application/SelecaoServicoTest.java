package digix.selecao.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.github.javafaker.Faker;

import digix.selecao.builder.ParticipanteDtoBuilder;
import digix.selecao.domain.CriterioSelecionado;
import digix.selecao.domain.Selecao;
import digix.selecao.domain.SelecaoRepositorio;
import digix.selecao.domain.criterios.Criterio;

public class SelecaoServicoTest {

    private SelecaoServico selecaoServico;
    private SelecaoRepositorio selecaoRepositorio;
    private Faker faker = new Faker();

    @BeforeEach
    public void init() {
        Criterio criterio = Mockito.mock(Criterio.class);
        selecaoRepositorio = Mockito.mock(SelecaoRepositorio.class);
        selecaoServico = new SelecaoServico(Arrays.asList(criterio), selecaoRepositorio);
    }

    @Test
    public void deve_selecionar_um_participante() {
        ParticipanteDto participanteDto = new ParticipanteDtoBuilder().criar();
        ArgumentCaptor<Selecao> selecaoCaptor = ArgumentCaptor.forClass(Selecao.class);

        selecaoServico.selecionar(participanteDto);

        verify(selecaoRepositorio).save(selecaoCaptor.capture());
        Selecao selecaoSalva = selecaoCaptor.getValue();
        assertEquals(participanteDto.id, selecaoSalva.getParticipanteId());
    }

    @Test
    public void deve_obter_um_ranking_ordenado() {
        final Integer POSICAO_UM = 1;
        final Integer POSICAO_DOIS = 2;
        final Integer POSICAO_TRES = 3;
        List<Selecao> selecoes = criarSelecoes();
        Mockito.when(selecaoRepositorio.findAll()).thenReturn(selecoes);

        List<SelecaoDto> selecionados = selecaoServico.obterRanking();
        SelecaoDto primeiroColocado = selecionados.get(0);
        SelecaoDto segundoColocado = selecionados.get(1);
        SelecaoDto terceiroColocado = selecionados.get(2);

        assertEquals(POSICAO_UM, primeiroColocado.posicao);
        assertEquals(POSICAO_DOIS, segundoColocado.posicao);
        assertEquals(POSICAO_TRES, terceiroColocado.posicao);
        assertTrue(primeiroColocado.totalDePontos > segundoColocado.totalDePontos);
        assertTrue(segundoColocado.totalDePontos > terceiroColocado.totalDePontos);
    }

    private List<Selecao> criarSelecoes() {
        List<Selecao> selecoes = new ArrayList<Selecao>();
        for (int i = 0; i < 3; i++) {
            String participanteId = UUID.randomUUID().toString();
            CriterioSelecionado criterioSelecionado = new CriterioSelecionado(faker.harryPotter().book(),
                    faker.number().numberBetween(0, 10));
            Selecao selecao = new Selecao(participanteId, Arrays.asList(criterioSelecionado));
            selecoes.add(selecao);
        }
        return selecoes;
    }
}
