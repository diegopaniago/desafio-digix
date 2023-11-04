package digix.selecao.portadapter.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthRest {

    @GetMapping
    public String check() {
        return "Estou vivo.";
    }
    
}
