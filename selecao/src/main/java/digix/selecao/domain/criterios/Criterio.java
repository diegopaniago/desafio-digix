package digix.selecao.domain.criterios;

import digix.selecao.application.ParticipanteDto;
import digix.selecao.domain.CriterioSelecionado;

public interface Criterio {
    public abstract CriterioSelecionado avaliar(ParticipanteDto participanteDto);
    public abstract String getNome();
    public abstract Integer getPontos();
}
