package digix.selecao.domain;

import org.hibernate.annotations.GenericGenerator;

import digix.selecao.utils.ValidadorDeCamposObrigatorios;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CriterioSelecionado {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column
    private String nome;

    @Column
    private Integer pontosObtidos;

    public CriterioSelecionado(String nome, Integer pontosObtidos) {
        validarCamposObrigatorios(nome, pontosObtidos);
        this.nome = nome;
        this.pontosObtidos = pontosObtidos;
    }

    private void validarCamposObrigatorios(String nome, Integer pontosObtidos) {
        ValidadorDeCamposObrigatorios validadorDeCamposObrigatorios = new ValidadorDeCamposObrigatorios();
        validadorDeCamposObrigatorios
                .textoEhVazio(nome, "O nome do criterio selecionado é obrigatório.")
                .valorEhNegativo(pontosObtidos, "A quantidade de pontos obtidos precisa ser maior ou igual a zero.")
                .entaoDispara();
    }
}
