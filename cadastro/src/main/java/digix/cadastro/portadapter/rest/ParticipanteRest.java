package digix.cadastro.portadapter.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import digix.cadastro.application.ParticipanteDto;
import digix.cadastro.application.ParticipanteServico;
import digix.cadastro.utils.CampoObrigatorioViolado;
import digix.cadastro.utils.CpfInvalido;

@RestController
@RequestMapping("/participante")
public class ParticipanteRest {

    @Autowired
    private ParticipanteServico participanteServico;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> adicionar(ParticipanteDto participanteDto) {
        try {
            String id = participanteServico.adicionar(participanteDto);
            return ResponseEntity.ok().body(id);
        } catch (CampoObrigatorioViolado campoObrigatorioViolado) {
            return ResponseEntity.badRequest().body(campoObrigatorioViolado.getMensagemDeErro());
        } catch (CpfInvalido cpfInvalido) {
            return ResponseEntity.unprocessableEntity().body(cpfInvalido.getMensagemDeErro());
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().body(exception.getMessage());
        }
    }
}
