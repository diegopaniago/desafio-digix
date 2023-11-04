package digix.selecao.application;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PessoaDto implements Serializable{
    public String nome;
    public String cpf;
    public LocalDate dataDeNascimento;
    public BigDecimal renda;
}
