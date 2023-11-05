package digix.selecao.domain.criterios;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import digix.selecao.application.ParticipanteDto;
import digix.selecao.application.PessoaDto;
import digix.selecao.builder.ParticipanteDtoBuilder;
import digix.selecao.builder.PessoaDtoBuilder;
import digix.selecao.domain.CriterioSelecionado;

public class CriterioRendaTotalFamiliarTest {

    private CriterioRendaTotalFamiliar criterio = new CriterioRendaTotalFamiliar();

    @Test
    public void deve_avaliar_com_o_maximo_de_pontos() {
        Integer pontosEsperados = 5;
        PessoaDto titular = new PessoaDtoBuilder()
            .comRenda(BigDecimal.valueOf(800))
            .criar();
        PessoaDto dependente = new PessoaDtoBuilder()
            .comRenda(BigDecimal.ZERO)
            .comDataDeNascimento(LocalDate.now())
            .criar();
        List<PessoaDto> familia = Arrays.asList(dependente);
        ParticipanteDto participanteDto = new ParticipanteDtoBuilder()
            .comTitular(titular)
            .comFamilia(familia)
            .criar();

        CriterioSelecionado criterioSelecionado = criterio.avaliar(participanteDto);

        assertEquals(pontosEsperados, criterioSelecionado.getPontosObtidos());
    }

    @Test
    public void deve_avaliar_com_o_minimo_de_pontos() {
        Integer pontosEsperados = 0;
        PessoaDto titular = new PessoaDtoBuilder()
            .comRenda(BigDecimal.valueOf(901))
            .criar();
        PessoaDto dependente = new PessoaDtoBuilder()
            .comRenda(BigDecimal.ZERO)
            .comDataDeNascimento(LocalDate.now())
            .criar();
        List<PessoaDto> familia = Arrays.asList(dependente);
        ParticipanteDto participanteDto = new ParticipanteDtoBuilder()
            .comTitular(titular)
            .comFamilia(familia)
            .criar();

        CriterioSelecionado criterioSelecionado = criterio.avaliar(participanteDto);

        assertEquals(pontosEsperados, criterioSelecionado.getPontosObtidos());
    }

}
