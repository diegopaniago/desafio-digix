package digix.cadastro.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import digix.cadastro.domain.Participante;
import digix.cadastro.domain.ParticipanteRepositorio;
import digix.cadastro.domain.Pessoa;
import digix.cadastro.utils.builder.ParticipanteBuilder;

public class ParticipanteServicoTest {
    private ParticipanteRepositorio participanteRepositorio;
    private ParticipanteServico participanteServico;

    @BeforeEach
    public void init() {
        participanteRepositorio = Mockito.mock(ParticipanteRepositorio.class);
        participanteServico = new ParticipanteServico(participanteRepositorio);
    }

    @Test
    public void deve_salvar_um_participante() {
        Participante participante = new ParticipanteBuilder().criar();
        PessoaDto titularDto = criarPessoaDto(participante.getTitular());
        List<PessoaDto> familiaDto = participante
                .getFamilia()
                .stream()
                .map(dependente -> criarPessoaDto(dependente))
                .collect(Collectors.toList());
        ParticipanteDto participanteDto = new ParticipanteDto(titularDto, familiaDto);
        String cpfEsperado = participanteDto.titular.cpf;
        ArgumentCaptor<Participante> participanteCaptor = ArgumentCaptor.forClass(Participante.class);

        participanteServico.adicionar(participanteDto);

        verify(participanteRepositorio).save(participanteCaptor.capture());
        String cpfDoTitularSalvo = participanteCaptor.getValue().getTitular().getCpf();
        assertEquals(cpfEsperado, cpfDoTitularSalvo);
    }

    private PessoaDto criarPessoaDto(Pessoa pessoa) {
        return new PessoaDto(
                pessoa.getNome(),
                pessoa.getCpf(),
                pessoa.getDataDeNascimento(),
                pessoa.getRenda());
    }
}
