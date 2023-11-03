package digix.cadastro.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ValidadorDeCamposObrigatorios {
    
    private List<String> erros = new ArrayList<String>();

    public ValidadorDeCamposObrigatorios ehNulo(Object objeto, String mensagemDeErro) {
        if(objeto == null) {
            erros.add(mensagemDeErro);
        } 
        return this;
    }

    public ValidadorDeCamposObrigatorios textoEhVazio(String texto, String mensagemDeErro) {
        if(texto == null || texto.trim().length() == 0) {
            erros.add(mensagemDeErro);
        } 
        return this;
    }

    public ValidadorDeCamposObrigatorios dataEhFuturaOuInvalida(LocalDate data, String mensagemDeErro) {
        if(data == null || data.isAfter(LocalDate.now())) {
            erros.add(mensagemDeErro);
        } 
        return this;
    }

    public ValidadorDeCamposObrigatorios valorEhNegativo(BigDecimal valor, String mensagemDeErro) {
        if(valor == null || valor.compareTo(BigDecimal.ZERO) < 0) {
            erros.add(mensagemDeErro);
        }
        return this;
    }

    public void entaoDispara() {
        if(!erros.isEmpty()) {
            StringBuilder mensagemFormatada = new StringBuilder();
            erros.forEach(erro -> mensagemFormatada.append(erro + "\n"));
            throw new CampoObrigatorioViolado(mensagemFormatada.toString().trim());
        }
    }
}
