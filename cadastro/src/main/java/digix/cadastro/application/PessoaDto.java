package digix.cadastro.application;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PessoaDto {
    public String nome;
    public String cpf;
    public LocalDate dataDeNascimento;
    public BigDecimal renda;
}
