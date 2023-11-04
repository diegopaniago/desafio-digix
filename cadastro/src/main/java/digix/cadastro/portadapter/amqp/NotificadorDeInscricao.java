package digix.cadastro.portadapter.amqp;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import digix.cadastro.domain.ParticipanteInscrito;

@Component
public class NotificadorDeInscricao {

    private RabbitTemplate rabbitTemplate;

    public NotificadorDeInscricao(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @EventListener(ParticipanteInscrito.class)
    public void notificar(ParticipanteInscrito participanteInscrito) {
        rabbitTemplate.convertAndSend(participanteInscrito.getParticipanteDto());
    }
    
}
