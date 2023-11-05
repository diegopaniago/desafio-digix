package digix.cadastro;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import digix.cadastro.application.ParticipanteDto;
import digix.cadastro.application.ParticipanteServico;
import digix.cadastro.application.PessoaDto;
import digix.cadastro.domain.Participante;
import digix.cadastro.utils.builder.ParticipanteBuilder;
import digix.cadastro.domain.ParticipanteRepositorio;
import digix.cadastro.domain.Pessoa;
import jakarta.transaction.Transactional;

@Component
public class Seeder {

    @Value("${seeder}")
    private Boolean deveRodarOSeeder;

    @Autowired
    private ParticipanteRepositorio participanteRepositorio;

    @Autowired
    ParticipanteServico participanteServico;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void executar() {
        if (!deveRodarOSeeder) {
            return;
        }
        for (int i = 0; i < 100; i++) {
            Participante participante = new ParticipanteBuilder().criar();
            PessoaDto titularDto = criarPessoaDto(participante.getTitular());
            List<PessoaDto> familiaDto = participante
                    .getFamilia()
                    .stream()
                    .map(dependente -> criarPessoaDto(dependente))
                    .collect(Collectors.toList());
            ParticipanteDto participanteDto = new ParticipanteDto(titularDto, familiaDto);
            participanteServico.adicionar(participanteDto);
        }
        System.out.println("Seed executado com sucesso.");
    }

    @EventListener(ContextClosedEvent.class)
    @Transactional
    public void limparBase() {
        participanteRepositorio.deleteAll();
        System.out.println("Seed removido com sucesso.");
    }

    private PessoaDto criarPessoaDto(Pessoa pessoa) {
        return new PessoaDto(
                pessoa.getNome(),
                pessoa.getCpf(),
                pessoa.getDataDeNascimento(),
                pessoa.getRenda());
    }
}
