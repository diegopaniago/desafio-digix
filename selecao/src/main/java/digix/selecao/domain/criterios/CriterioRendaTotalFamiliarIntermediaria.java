package digix.selecao.domain.criterios;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import digix.selecao.application.ParticipanteDto;
import digix.selecao.domain.CriterioSelecionado;

@Component
public class CriterioRendaTotalFamiliarIntermediaria implements Criterio {
    private static final String NOME = "Renda total familiar intermediaria.";
    private static final Integer PONTOS = 3;
    private static final BigDecimal RENDA_FAMILIAR_MINIMA_PERMITIDA = BigDecimal.valueOf(901);
    private static final BigDecimal RENDA_FAMILIAR_MAXIMA_PERMITIDA = BigDecimal.valueOf(1500);

    @Override
    public CriterioSelecionado avaliar(ParticipanteDto participanteDto) {
        BigDecimal rendaTotalDosDepentes = participanteDto
            .familia
            .stream()
            .map(dependente -> dependente.renda)
            .reduce(BigDecimal.ZERO, (total, valor) -> total.add(valor));
        BigDecimal rendaTotal = participanteDto.titular.renda.add(rendaTotalDosDepentes);
        int resultadoMinimo = rendaTotal.compareTo(RENDA_FAMILIAR_MINIMA_PERMITIDA);
        int resultadoMaximo = rendaTotal.compareTo(RENDA_FAMILIAR_MAXIMA_PERMITIDA);

        if (resultadoMinimo >= 0 && resultadoMaximo <= 0) {
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
