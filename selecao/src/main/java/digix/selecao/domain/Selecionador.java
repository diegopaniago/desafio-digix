package digix.selecao.domain;


import java.util.ArrayList;
import java.util.List;

import digix.selecao.application.ParticipanteDto;
import digix.selecao.domain.criterios.Criterio;

public class Selecionador {

    private List<Criterio> criterios;
    
    public Selecionador(List<Criterio> criterios) {
        this.criterios = criterios;
    }

    public Selecao selecionar(ParticipanteDto participanteDto) {
        List<CriterioSelecionado> criteriosSelecionados = new ArrayList<CriterioSelecionado>();
        criterios.forEach(criterio -> {
            criteriosSelecionados.add(criterio.avaliar(participanteDto));
        });
        return new Selecao(participanteDto.id, criteriosSelecionados);
    }
}
