package digix.selecao;

import org.springframework.beans.factory.annotation.Autowired;
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
