package digix.selecao.domain.criterios;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import digix.selecao.application.ParticipanteDto;
import digix.selecao.domain.CriterioSelecionado;

@Component
public class CriterioFamiliaComTresOuMaisDependentes implements Criterio {
    private static final String NOME = "Familia com 3 ou mais dependentes.";
    private static final Integer PONTOS = 3;
    private static final long MINIMO_DE_DEPENDENTES = 3;

    @Override
    public CriterioSelecionado avaliar(ParticipanteDto participanteDto) {
        long quantidadeDeDependetesMenores = participanteDto.familia
                .stream()
                .filter(dependente -> dependente.dataDeNascimento.plusYears(18).isAfter(LocalDate.now()))
                .count();
        if (quantidadeDeDependetesMenores >= MINIMO_DE_DEPENDENTES) {
            return new CriterioSelecionado(NOME, PONTOS);
        } else {
            return new CriterioSelecionado(NOME, 0);
        }
    }

    @Override
    public String getNome() {
        return NOME;
    }

    @Override
    public Integer getPontos() {
        return PONTOS;
    }

}
