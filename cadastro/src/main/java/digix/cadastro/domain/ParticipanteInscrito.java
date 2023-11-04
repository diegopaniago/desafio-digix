package digix.cadastro.domain;

import digix.cadastro.application.ParticipanteDto;
import lombok.Getter;

@Getter
public class ParticipanteInscrito extends EventoDeDominio {
    private ParticipanteDto participanteDto;

    public ParticipanteInscrito(Object source, ParticipanteDto participanteDto) {
        super(source);
        this.participanteDto = participanteDto;
    }
}
