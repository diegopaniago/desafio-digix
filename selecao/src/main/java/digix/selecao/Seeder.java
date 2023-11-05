package digix.selecao;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import digix.selecao.domain.SelecaoRepositorio;
import jakarta.transaction.Transactional;

@Component
public class Seeder {

    @Autowired
    private SelecaoRepositorio selecaoRepositorio;


    @EventListener(ContextClosedEvent.class)
    @Transactional
    public void limparBase() {
        selecaoRepositorio.deleteAll();
        System.out.println("Seed removido com sucesso.");
    }
}
