package digix.selecao.utils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.javafaker.Faker;

public class ValidadorDeCamposObrigatoriosTest {

    private ValidadorDeCamposObrigatorios validadorDeCamposObrigatorios = new ValidadorDeCamposObrigatorios();
    private Faker faker = new Faker();
    
    @Test
    public void deve_validar_campos_nulos() {
        String erroEsperado = "Campo nulo não permitido.";
        Object valorNulo = null;

        CampoObrigatorioViolado erro = assertThrows(CampoObrigatorioViolado.class, () -> {
            validadorDeCamposObrigatorios
                .ehNulo(valorNulo, erroEsperado)
                .entaoDispara();
        });

        assertEquals(erroEsperado, erro.getMensagemDeErro());
    }

    @Test
    public void nao_deve_validar_campos_nulos_que_nao_estejam_nulos() {
        String erroEsperado = "Campo nulo não permitido.";
        Object valorNulo = new Object();

        assertDoesNotThrow(() -> {
            validadorDeCamposObrigatorios
                .ehNulo(valorNulo, erroEsperado)
                .entaoDispara();
        });
    }

    @ParameterizedTest
    @MethodSource("gerarDatasInvalidas")
    public void deve_validar_data_futura_ou_vazia(LocalDate dataInvalida) {
        String erroEsperado = "Data invalida.";

        CampoObrigatorioViolado erro = assertThrows(CampoObrigatorioViolado.class, () -> {
            validadorDeCamposObrigatorios
                .dataEhFuturaOuInvalida(dataInvalida, erroEsperado)
                .entaoDispara();
        });

        assertEquals(erroEsperado, erro.getMensagemDeErro());
    }

    @ParameterizedTest
    @MethodSource("gerarDatasValidas")
    public void nao_deve_validar_data_futura_ou_vazia_quando_nao_for_invalida(LocalDate dataInvalida) {
        String erroEsperado = "Data invalida.";

        assertDoesNotThrow(() -> {
            validadorDeCamposObrigatorios
                .dataEhFuturaOuInvalida(dataInvalida, erroEsperado)
                .entaoDispara();
        });
    }

    @ParameterizedTest
    @MethodSource("gerarTextosVazios")
    public void deve_validar_texto_vazio(String textoVazio) {
        String erroEsperado = "Texto vazio não é permitido.";

        CampoObrigatorioViolado erro = assertThrows(CampoObrigatorioViolado.class, () -> {
            validadorDeCamposObrigatorios
                .textoEhVazio(textoVazio, erroEsperado)
                .entaoDispara();
        });

        assertEquals(erroEsperado, erro.getMensagemDeErro());
    }
    

    @Test
    public void nao_deve_validar__texto_vazio_quando_nao_for_vazio() {
        String erroEsperado = "Campo nulo não permitido.";
        String texto = faker.lorem().paragraph();

        assertDoesNotThrow(() -> {
            validadorDeCamposObrigatorios
                .textoEhVazio(texto, erroEsperado)
                .entaoDispara();
        });
    }

    @Test
    public void deve_validar_valor_negativo() {
        String erroEsperado = "Valor negativo não é permitido.";
        BigDecimal valorNegativo = BigDecimal.valueOf(-10.22);

        CampoObrigatorioViolado erro = assertThrows(CampoObrigatorioViolado.class, () -> {
            validadorDeCamposObrigatorios
                .valorEhNegativo(valorNegativo, erroEsperado)
                .entaoDispara();
        });

        assertEquals(erroEsperado, erro.getMensagemDeErro());
    }
    

    @Test
    public void nao_deve_validar_valor_negativo_quando_for_positivo() {
        String erroEsperado = "Valor negativo não é permitido.";
        BigDecimal valorPositivo = BigDecimal.ONE;

        assertDoesNotThrow(() -> {
            validadorDeCamposObrigatorios
                .valorEhNegativo(valorPositivo, erroEsperado)
                .entaoDispara();
        });
    }

    @Test
    public void deve_validar_valor_inteiro_negativo() {
        String erroEsperado = "Valor negativo não é permitido.";
        Integer valorNegativo = -2;

        CampoObrigatorioViolado erro = assertThrows(CampoObrigatorioViolado.class, () -> {
            validadorDeCamposObrigatorios
                .valorEhNegativo(valorNegativo, erroEsperado)
                .entaoDispara();
        });

        assertEquals(erroEsperado, erro.getMensagemDeErro());
    }
    

    @Test
    public void nao_deve_validar_valor_inteiro_negativo_quando_for_positivo() {
        String erroEsperado = "Valor negativo não é permitido.";
        Integer valorPositivo = 1;

        assertDoesNotThrow(() -> {
            validadorDeCamposObrigatorios
                .valorEhNegativo(valorPositivo, erroEsperado)
                .entaoDispara();
        });
    }

    @Test
    public void deve_validar_lista_vazia() {
        String erroEsperado = "Lista vazia não é permitido.";
        List<Object> listaVazia = Collections.emptyList();

        CampoObrigatorioViolado erro = assertThrows(CampoObrigatorioViolado.class, () -> {
            validadorDeCamposObrigatorios
                .listaEhVazia(listaVazia, erroEsperado)
                .entaoDispara();
        });

        assertEquals(erroEsperado, erro.getMensagemDeErro());
    }

    @Test
    public void nao_deve_validar_lista_vazia_quando_nao_for_vazia() {
        String erroEsperado = "Lista vazia não é permitido.";
        List<String> lista = Arrays.asList("Teste 1", "Teste 2");

        assertDoesNotThrow(() -> {
            validadorDeCamposObrigatorios
                .listaEhVazia(lista, erroEsperado)
                .entaoDispara();
        });
    }

    static Stream<Arguments> gerarDatasInvalidas() {
        return Stream.of(
            Arguments.of(
                LocalDate.now().plusYears(10),
                LocalDate.now().plusDays(10),
                null
            )
        );
    }

    static Stream<Arguments> gerarDatasValidas() {
        return Stream.of(
            Arguments.of(
                LocalDate.now(),
                LocalDate.now().minusDays(10)
            )
        );
    }

    static Stream<Arguments> gerarTextosVazios() {
        return Stream.of(
            Arguments.of(
                "",
                null,
                " ",
                "   ",
                "     "
            )
        );
    }
}
