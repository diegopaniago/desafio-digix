package digix.cadastro.utils;

public class CpfInvalido extends RuntimeException {

    public CpfInvalido(String cpf) {
        super("O Cpf informado é invalido. Cpf: " + cpf);
    }

    public String getMensagemDeErro() {
        return this.getMessage();
    }
}
