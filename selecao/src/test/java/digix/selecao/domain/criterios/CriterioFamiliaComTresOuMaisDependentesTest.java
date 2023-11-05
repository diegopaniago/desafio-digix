package digix.selecao.domain.criterios;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import digix.selecao.application.ParticipanteDto;
import digix.selecao.application.PessoaDto;
import digix.selecao.builder.ParticipanteDtoBuilder;
import digix.selecao.builder.PessoaDtoBuilder;
import digix.selecao.domain.CriterioSelecionado;

public class CriterioFamiliaComTresOuMaisDependentesTest {
    private CriterioFamiliaComTresOuMaisDependentes criterio = new CriterioFamiliaComTresOuMaisDependentes();

    @Test
    public void deve_avaliar_com_o_maximo_de_pontos() {
        Faker faker = new Faker();
        Integer pontosEsperados = 3;
        List<PessoaDto> familia = new ArrayList<PessoaDto>();
        int quantidadeDeDependentes = faker.number().numberBetween(3, 6);
        for (int i = 0; i < quantidadeDeDependentes; i++) {
            familia.add(new PessoaDtoBuilder()
                    .comDataDeNascimento(LocalDate.now())
                    .criar());
        }
        ParticipanteDto participanteDto = new ParticipanteDtoBuilder()
                .comFamilia(familia)
                .criar();

        CriterioSelecionado criterioSelecionado = criterio.avaliar(participanteDto);

        assertEquals(pontosEsperados, criterioSelecionado.getPontosObtidos());
    }

    @Test
    public void deve_avaliar_com_o_minimo_de_pontos() {
        Integer pontosEsperados = 0;
        PessoaDto dependente = new PessoaDtoBuilder()
                .comDataDeNascimento(LocalDate.now().minusYears(18))
                .criar();
        List<PessoaDto> familia = Arrays.asList(dependente);
        ParticipanteDto participanteDto = new ParticipanteDtoBuilder()
                .comFamilia(familia)
                .criar();

        CriterioSelecionado criterioSelecionado = criterio.avaliar(participanteDto);

        assertEquals(pontosEsperados, criterioSelecionado.getPontosObtidos());
    }
}
