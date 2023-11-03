package digix.cadastro.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.annotations.GenericGenerator;

import digix.cadastro.utils.ValidadorDeCamposObrigatorios;
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
public class Pessoa {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @Column
    private String nome;

    @Column
    private String cpf;

    @Column
    private LocalDate dataDeNascimento;

    @Column
    private BigDecimal renda;

    public Pessoa(String nome, String cpf, LocalDate dataDeNascimento, BigDecimal renda) {
        this.validarCamposObrigatorios(nome, cpf, dataDeNascimento, renda);
        this.nome = nome;
        this.cpf = cpf;
        this.dataDeNascimento = dataDeNascimento;
        this.renda = renda;
    }

    private void validarCamposObrigatorios(String nome, String cpf, LocalDate dataDeNascimento, BigDecimal renda) {
        ValidadorDeCamposObrigatorios validador = new ValidadorDeCamposObrigatorios();
        validador
            .textoEhVazio(nome, "É obrigatório informar o nome de uma pessoa.")
            .textoEhVazio(cpf, "É obrigatório informar o cpf de uma pessoa.")
            .dataEhFuturaOuInvalida(dataDeNascimento, "É obrigatório informar uma data de nascimento válida.")
            .valorEhNegativo(renda, "É obrigatório informar uma renda maior ou igual a zero.")
            .entaoDispara();
    }
}
