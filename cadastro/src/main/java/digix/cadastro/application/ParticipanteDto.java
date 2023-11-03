package digix.cadastro.application;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ParticipanteDto {
    public PessoaDto titular;
    public List<PessoaDto> familia;
}
