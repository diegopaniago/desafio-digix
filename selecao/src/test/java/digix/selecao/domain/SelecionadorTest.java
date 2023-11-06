package digix.selecao.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.github.javafaker.Faker;

import digix.selecao.application.ParticipanteDto;
import digix.selecao.builder.ParticipanteDtoBuilder;
import digix.selecao.domain.criterios.Criterio;

public class SelecionadorTest {

    private Faker faker = new Faker();

    @Test
    public void deve_selecionar_com_base_nos_criterios() {
        ParticipanteDto participanteDto = new ParticipanteDtoBuilder().criar();
        Criterio criterio1 = Mockito.mock(Criterio.class);
        CriterioSelecionado criterioSelecionado1 = new CriterioSelecionado(
                faker.lordOfTheRings().character(),
                faker.number().numberBetween(0, 5));
        Mockito.when(criterio1.avaliar(participanteDto)).thenReturn(criterioSelecionado1);
        Criterio criterio2 = Mockito.mock(Criterio.class);
        CriterioSelecionado criterioSelecionado2 = new CriterioSelecionado(
                faker.lordOfTheRings().character(),
                faker.number().numberBetween(0, 5));
        Mockito.when(criterio2.avaliar(participanteDto)).thenReturn(criterioSelecionado2);
        List<CriterioSelecionado> criteriosSelecionadosEsperados = Arrays.asList(criterioSelecionado1, criterioSelecionado2);
        Selecionador selecionador = new Selecionador(Arrays.asList(criterio1, criterio2));

        Selecao selecao = selecionador.selecionar(participanteDto);
        
        assertEquals(participanteDto.id, selecao.getParticipanteId());
        assertTrue(criteriosSelecionadosEsperados.containsAll(selecao.getCriteriosSelecionados()));
    }

}
