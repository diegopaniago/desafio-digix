package digix.selecao.builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;

import com.github.javafaker.Faker;

import br.com.caelum.stella.validation.CPFValidator;
import digix.selecao.application.PessoaDto;

public class PessoaDtoBuilder {

    private String nome;
    private String cpf;
    private LocalDate dataDeNascimento;
    private BigDecimal renda;
    
    public PessoaDtoBuilder() {
        Faker faker = new Faker();
        CPFValidator cpfValidator = new CPFValidator();
        nome = faker.name().fullName();
        cpf = cpfValidator.generateRandomValid();
        dataDeNascimento = faker
            .date()
            .birthday()
            .toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
        renda = BigDecimal.valueOf(faker.number().numberBetween(0, 1000));
    }

    public PessoaDtoBuilder comRenda(BigDecimal renda) {
        this.renda = renda;
        return this;
    }

    public PessoaDtoBuilder comDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
        return this;
    }

    public PessoaDto criar() {
        return new PessoaDto(nome, cpf, dataDeNascimento, renda);
    }
}
