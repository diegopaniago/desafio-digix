package digix.selecao.application;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SelecaoDto {
    public String participanteId;
    public Integer posicao;
    public Integer totalDePontos;
    public List<CriterioSelecionadoDto> criterios; 
}
