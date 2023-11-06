package digix.selecao.domain;

import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import digix.selecao.utils.ValidadorDeCamposObrigatorios;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Selecao {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column
    private String participanteId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CriterioSelecionado> criteriosSelecionados;

    public Selecao(String participanteId, List<CriterioSelecionado> criteriosSelecionados) {
        validarCamposObrigatorios(participanteId, criteriosSelecionados);
        this.participanteId = participanteId;
        this.criteriosSelecionados = criteriosSelecionados;
    }

    private void validarCamposObrigatorios(String participanteId, List<CriterioSelecionado> criteriosSelecionados) {
        ValidadorDeCamposObrigatorios validadorDeCamposObrigatorios = new ValidadorDeCamposObrigatorios();
        validadorDeCamposObrigatorios
                .textoEhVazio(participanteId, "É obrigatório informar o participante.")
                .listaEhVazia(criteriosSelecionados, "É obrigatório informar os criterios da seleção.")
                .entaoDispara();
    }

    public Integer obterTotalDePontos() {
        return criteriosSelecionados
                .stream()
                .map(CriterioSelecionado::getPontosObtidos)
                .reduce(0, (subtotal, elemento) -> subtotal + elemento);
    }

}
