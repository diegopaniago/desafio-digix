package digix.cadastro.application;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ParticipanteDto implements Serializable {
    public PessoaDto titular;
    public List<PessoaDto> familia;
}
