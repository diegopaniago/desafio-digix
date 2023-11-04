package digix.cadastro.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import digix.cadastro.domain.Participante;
import digix.cadastro.domain.ParticipanteRepositorio;
import digix.cadastro.domain.Pessoa;

@Service
public class ParticipanteServico {

    private ParticipanteRepositorio participanteRepositorio;

    ParticipanteServico(ParticipanteRepositorio participanteRepositorio) {
        this.participanteRepositorio = participanteRepositorio;
    }

    public String adicionar(ParticipanteDto participanteDto) {
        Pessoa titular = new Pessoa(participanteDto.titular.nome,
                participanteDto.titular.cpf,
                participanteDto.titular.dataDeNascimento,
                participanteDto.titular.renda);
        List<Pessoa> familia = participanteDto
            .familia
            .stream()
            .map(pessoaDto -> criarPessoa(pessoaDto))
            .collect(Collectors.toList());
        Participante participante = new Participante(titular, familia);
        participanteRepositorio.save(participante);
        return participante.getId();
    }

    private Pessoa criarPessoa(PessoaDto pessoaDto) {
        return new Pessoa(pessoaDto.nome,
                pessoaDto.cpf,
                pessoaDto.dataDeNascimento,
                pessoaDto.renda);
    }
}
