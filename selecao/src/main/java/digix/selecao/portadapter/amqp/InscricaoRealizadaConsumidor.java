package digix.selecao.portadapter.amqp;

import java.io.UnsupportedEncodingException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import digix.selecao.application.ParticipanteDto;
import digix.selecao.utils.GsonUtil;

@Component
public class InscricaoRealizadaConsumidor {
    
    @RabbitListener(queues = "inscricao")
    public void receber(Message message) throws UnsupportedEncodingException {
        Gson gson = GsonUtil.getGson();
        String json = new String(message.getBody(), "UTF-8");
        ParticipanteDto participanteDto = gson.fromJson(json, ParticipanteDto.class);
        System.out.println(participanteDto.titular.nome);
    }
}
