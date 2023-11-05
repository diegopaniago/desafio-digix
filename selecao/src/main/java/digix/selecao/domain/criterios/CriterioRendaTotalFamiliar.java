package digix.selecao.domain.criterios;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import digix.selecao.application.ParticipanteDto;
import digix.selecao.domain.CriterioSelecionado;

@Component
public class CriterioRendaTotalFamiliar implements Criterio {
    private static final String NOME = "Renda total familiar.";
    private static final Integer PONTOS = 5;
    private static final BigDecimal RENDA_FAMILIAR_MAXIMA_PERMITIDA = BigDecimal.valueOf(900);

    @Override
    public CriterioSelecionado avaliar(ParticipanteDto participanteDto) {
        BigDecimal rendaTotalDosDepentes = participanteDto
            .familia
            .stream()
            .map(dependente -> dependente.renda)
            .reduce(BigDecimal.ZERO, (total, valor) -> total.add(valor));
        BigDecimal rendaTotal = participanteDto.titular.renda.add(rendaTotalDosDepentes);
        if(rendaTotal.compareTo(RENDA_FAMILIAR_MAXIMA_PERMITIDA) <= 0) {
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
