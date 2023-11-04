package digix.cadastro.portadapter.amqp;

import java.io.UnsupportedEncodingException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import digix.cadastro.domain.ParticipanteInscrito;
import digix.cadastro.utils.GsonUtil;

@Component
public class NotificadorDeInscricao {

    private RabbitTemplate rabbitTemplate;

    public NotificadorDeInscricao(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @EventListener(ParticipanteInscrito.class)
    public void notificar(ParticipanteInscrito participanteInscrito) throws UnsupportedEncodingException {
        Gson gson = GsonUtil.getGson();
        String json = gson.toJson(participanteInscrito.getParticipanteDto());
        Message message = new Message(json.getBytes("UTF-8"));
        rabbitTemplate.send(message);
    }

}
