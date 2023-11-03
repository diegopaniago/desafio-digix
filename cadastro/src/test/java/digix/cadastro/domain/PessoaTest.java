package digix.cadastro.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import br.com.caelum.stella.validation.CPFValidator;
import digix.cadastro.utils.CampoObrigatorioViolado;
import digix.cadastro.utils.CpfInvalido;

public class PessoaTest {
    Faker faker = new Faker();
    String nome;
    String cpf;
    LocalDate dataDeNascimento;
    BigDecimal renda;

    @BeforeEach
    public void init() {
        nome = faker.name().fullName();
        cpf = new CPFValidator().generateRandomValid();
        dataDeNascimento = LocalDate.now();
        renda = BigDecimal.valueOf(faker.number().numberBetween(0, 1000));
    }

    @Test
    public void deve_criar_uma_pessoa() {

        Pessoa pessoa = new Pessoa(nome, cpf, dataDeNascimento, renda);

        assertEquals(nome, pessoa.getNome());
        assertEquals(cpf, pessoa.getCpf());
        assertEquals(dataDeNascimento, pessoa.getDataDeNascimento());
        assertEquals(renda, pessoa.getRenda());
    }

    @Test
    public void nao_deve_criar_uma_pessoa_sem_nome() {
        String erroEsperado = "É obrigatório informar o nome de uma pessoa.";
        String nome = null;

        CampoObrigatorioViolado erro = assertThrows(CampoObrigatorioViolado.class, () -> {
            new Pessoa(nome, cpf, dataDeNascimento, renda);
        });

        assertEquals(erroEsperado, erro.getMensagemDeErro());
    }

    @Test
    public void nao_deve_criar_uma_pessoa_sem_cpf() {
        String erroEsperado = "É obrigatório informar o cpf de uma pessoa.";
        String cpf = null;

        CampoObrigatorioViolado erro = assertThrows(CampoObrigatorioViolado.class, () -> {
            new Pessoa(nome, cpf, dataDeNascimento, renda);
        });

        assertEquals(erroEsperado, erro.getMensagemDeErro());
    }

    @Test
    public void nao_deve_criar_uma_pessoa_com_cpf_invalido() {
        String cpf = "00011122200";
        String erroEsperado = "O Cpf informado é invalido. Cpf: " + cpf;
        CpfInvalido erro = assertThrows(CpfInvalido.class, () -> {
            new Pessoa(nome, cpf, dataDeNascimento, renda);
        });

        assertEquals(erroEsperado, erro.getMensagemDeErro());
    }

    @Test
    public void nao_deve_criar_uma_pessoa_sem_data_de_nascimento() {
        String erroEsperado = "É obrigatório informar uma data de nascimento válida.";
        LocalDate dataDeNascimento = LocalDate.now().plusYears(1);

        CampoObrigatorioViolado erro = assertThrows(CampoObrigatorioViolado.class, () -> {
            new Pessoa(nome, cpf, dataDeNascimento, renda);
        });

        assertEquals(erroEsperado, erro.getMensagemDeErro());
    }

    @Test
    public void nao_deve_criar_uma_pessoa_sem_renda() {
        String erroEsperado = "É obrigatório informar uma renda maior ou igual a zero.";
        BigDecimal renda = null;

        CampoObrigatorioViolado erro = assertThrows(CampoObrigatorioViolado.class, () -> {
            new Pessoa(nome, cpf, dataDeNascimento, renda);
        });

        assertEquals(erroEsperado, erro.getMensagemDeErro());

    }

    @Test
    public void nao_deve_criar_uma_pessoa_com_renda_negativa() {
        String erroEsperado = "É obrigatório informar uma renda maior ou igual a zero.";
        BigDecimal renda = BigDecimal.valueOf(-1);

        CampoObrigatorioViolado erro = assertThrows(CampoObrigatorioViolado.class, () -> {
            new Pessoa(nome, cpf, dataDeNascimento, renda);
        });

        assertEquals(erroEsperado, erro.getMensagemDeErro());
    }
}
