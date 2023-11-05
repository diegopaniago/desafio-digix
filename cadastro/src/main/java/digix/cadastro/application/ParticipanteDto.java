package digix.cadastro.application;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ParticipanteDto implements Serializable {
    public String id;
    public PessoaDto titular;
    public List<PessoaDto> familia;

    public ParticipanteDto(PessoaDto titular, List<PessoaDto> familia) {
        this.titular = titular;
        this.familia = familia;
    }
}
