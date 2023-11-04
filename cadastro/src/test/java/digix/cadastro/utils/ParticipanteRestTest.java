package digix.cadastro.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import digix.cadastro.application.ParticipanteDto;
import digix.cadastro.application.PessoaDto;
import digix.cadastro.domain.Participante;
import digix.cadastro.domain.ParticipanteRepositorio;
import digix.cadastro.domain.Pessoa;
import digix.cadastro.portadapter.rest.ParticipanteRest;
import digix.cadastro.utils.builder.ParticipanteBuilder;

@SpringBootTest
public class ParticipanteRestTest {
    @Autowired
    private ParticipanteRest participanteRest;

    @Autowired
    private ParticipanteRepositorio participanteRepositorio;

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

        ResponseEntity<Object> response = participanteRest.adicionar(participanteDto);
        HttpStatusCode responseStatus = response.getStatusCode();
        String participanteId = (String) participanteRest.adicionar(participanteDto).getBody();
        Participante participanteSalvo = participanteRepositorio.findById(participanteId).get();

        assertEquals(participanteSalvo.getId(), participanteId);
        assertEquals(responseStatus, HttpStatus.OK);
    }

    private PessoaDto criarPessoaDto(Pessoa pessoa) {
        return new PessoaDto(
                pessoa.getNome(),
                pessoa.getCpf(),
                pessoa.getDataDeNascimento(),
                pessoa.getRenda());
    }
}
