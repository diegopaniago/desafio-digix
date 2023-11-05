package digix.selecao.utils;

public class CampoObrigatorioViolado extends RuntimeException {
    public CampoObrigatorioViolado(String mensagemDeErro) {
        super(mensagemDeErro);
    }

    public String getMensagemDeErro() {
        return this.getMessage();
    }
}
