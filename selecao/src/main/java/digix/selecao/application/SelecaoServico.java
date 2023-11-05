package digix.selecao.application;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import digix.selecao.domain.CriterioSelecionado;
import digix.selecao.domain.Selecao;
import digix.selecao.domain.SelecaoRepositorio;
import digix.selecao.domain.Selecionador;
import digix.selecao.domain.criterios.Criterio;

@Service
public class SelecaoServico {

    private List<Criterio> criterios;
    private SelecaoRepositorio selecaoRepositorio;

    public SelecaoServico(List<Criterio> criterios, SelecaoRepositorio selecaoRepositorio) {
        this.criterios = criterios;
        this.selecaoRepositorio = selecaoRepositorio;
    }

    public void selecionar(ParticipanteDto participanteDto) {
        Selecionador selecionador = new Selecionador(criterios);
        Selecao selecao = selecionador.selecionar(participanteDto);
        selecaoRepositorio.save(selecao);
    }

    public List<SelecaoDto> obterRanking() {
        List<Selecao> selecionados = selecaoRepositorio.findAll();
        List<SelecaoDto> selecionadosDtos = criarSelecionadosDtos(selecionados);
        ordenarSelecionados(selecionadosDtos);
        return selecionadosDtos;
    }

    private List<SelecaoDto> criarSelecionadosDtos(List<Selecao> selecionados) {
        List<SelecaoDto> selecionadosDtos = selecionados
                .stream()
                .map(selecionado -> {
                    List<CriterioSelecionadoDto> criterios = selecionado
                            .getCriteriosSelecionados()
                            .stream()
                            .map(criterio -> new CriterioSelecionadoDto(criterio.getNome(),
                                    criterio.getPontosObtidos()))
                            .collect(Collectors.toList());
                    Integer totalDePontos = selecionado
                            .getCriteriosSelecionados()
                            .stream()
                            .map(CriterioSelecionado::getPontosObtidos)
                            .reduce(0, (subtotal, elemento) -> subtotal + elemento);
                    return new SelecaoDto(selecionado.getParticipanteId(), 0, totalDePontos, criterios);
                })
                .collect(Collectors.toList());

        return selecionadosDtos;
    }

    private void ordenarSelecionados(List<SelecaoDto> selecionadosDtos) {
        selecionadosDtos.sort(Comparator.comparing(SelecaoDto::getTotalDePontos).reversed());
        for (int i = 0; i < selecionadosDtos.size(); i++) {
            selecionadosDtos.get(i).posicao = i + 1;
        }
    }

}
