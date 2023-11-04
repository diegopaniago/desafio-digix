package digix.cadastro.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.context.ApplicationEventPublisher;

import digix.cadastro.domain.Participante;
import digix.cadastro.domain.ParticipanteInscrito;
import digix.cadastro.domain.ParticipanteRepositorio;
import digix.cadastro.domain.Pessoa;
import digix.cadastro.utils.builder.ParticipanteBuilder;

public class ParticipanteServicoTest {
    private ParticipanteRepositorio participanteRepositorio;
    private ParticipanteServico participanteServico;
    private ApplicationEventPublisher applicationEventPublisher;

    @BeforeEach
    public void init() {
        participanteRepositorio = Mockito.mock(ParticipanteRepositorio.class);
        applicationEventPublisher = Mockito.mock(ApplicationEventPublisher.class);
        participanteServico = new ParticipanteServico(participanteRepositorio, applicationEventPublisher);
    }

    @Test
    public void deve_salvar_um_participante() {
        ParticipanteDto participanteDto = criarParticipanteDto();
        String cpfEsperado = participanteDto.titular.cpf;
        ArgumentCaptor<Participante> participanteCaptor = ArgumentCaptor.forClass(Participante.class);

        participanteServico.adicionar(participanteDto);

        verify(participanteRepositorio).save(participanteCaptor.capture());
        String cpfDoTitularSalvo = participanteCaptor.getValue().getTitular().getCpf();
        assertEquals(cpfEsperado, cpfDoTitularSalvo);
    }

    @Test
    public void deve_notificar_que_um_participante_foi_inscrito() {
        ParticipanteDto participanteDto = criarParticipanteDto();
        ArgumentCaptor<ParticipanteInscrito> participanteInscritoCaptor = ArgumentCaptor.forClass(ParticipanteInscrito.class);

        participanteServico.adicionar(participanteDto);

        verify(applicationEventPublisher).publishEvent(participanteInscritoCaptor.capture());
        ParticipanteDto participanteNotificado = participanteInscritoCaptor.getValue().getParticipanteDto();
        assertEquals(participanteDto, participanteNotificado);
    }

    private ParticipanteDto criarParticipanteDto() {
        Participante participante = new ParticipanteBuilder().criar();
        PessoaDto titularDto = criarPessoaDto(participante.getTitular());
        List<PessoaDto> familiaDto = participante
                .getFamilia()
                .stream()
                .map(dependente -> criarPessoaDto(dependente))
                .collect(Collectors.toList());
        return new ParticipanteDto(titularDto, familiaDto);
    }

    private PessoaDto criarPessoaDto(Pessoa pessoa) {
        return new PessoaDto(
                pessoa.getNome(),
                pessoa.getCpf(),
                pessoa.getDataDeNascimento(),
                pessoa.getRenda());
    }
}
