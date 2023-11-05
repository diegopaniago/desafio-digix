package digix.selecao.portadapter.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import digix.selecao.application.SelecaoDto;
import digix.selecao.application.SelecaoServico;

@RestController
@RequestMapping("/selecao")
public class SelecaoRest {

    @Autowired
    private SelecaoServico selecaoServico;

    @GetMapping("/ranking")
    @Transactional(readOnly = true)
    public ResponseEntity<Object> obterRanking() {
        try {
            List<SelecaoDto> selecionados = selecaoServico.obterRanking();
            return ResponseEntity.ok().body(selecionados);
        } catch(Exception exception) {
            return ResponseEntity.internalServerError().body(exception.getMessage());
        }
    }
    
}
