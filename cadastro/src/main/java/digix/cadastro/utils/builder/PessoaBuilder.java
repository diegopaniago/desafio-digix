package digix.cadastro.utils.builder;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.github.javafaker.Faker;

import br.com.caelum.stella.validation.CPFValidator;
import digix.cadastro.domain.Pessoa;

public class PessoaBuilder {
    private String nome;
    private String cpf;
    private LocalDate dataDeNascimento;
    private BigDecimal renda;

    public PessoaBuilder() {
        Faker faker = new Faker();
        this.nome = faker.name().fullName();
        this.cpf = new CPFValidator().generateRandomValid();
        int anoAleatorio = faker.number().numberBetween(1900, 2023);
        int mesAleatorio = faker.number().numberBetween(1, 12);
        int diaAleatorio = faker.number().numberBetween(1, 20);
        this.dataDeNascimento = LocalDate.of(anoAleatorio, mesAleatorio, diaAleatorio);
        this.renda = BigDecimal.valueOf(faker.number().numberBetween(0, 1000));
    }

    public Pessoa criar() {
        return new Pessoa(this.nome, this.cpf, this.dataDeNascimento, this.renda);
    }
}
