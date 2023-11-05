package digix.selecao.portadapter.amqp;

import java.io.UnsupportedEncodingException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import digix.selecao.application.ParticipanteDto;
import digix.selecao.application.SelecaoServico;
import digix.selecao.utils.GsonUtil;
import jakarta.transaction.Transactional;

@Component
public class InscricaoRealizadaConsumidor {

    @Autowired
    private SelecaoServico selecaoServico;
    
    @RabbitListener(queues = "inscricao")
    @Transactional
    public void receber(Message message) throws UnsupportedEncodingException {
        Gson gson = GsonUtil.getGson();
        String json = new String(message.getBody(), "UTF-8");
        ParticipanteDto participanteDto = gson.fromJson(json, ParticipanteDto.class);
        selecaoServico.selecionar(participanteDto);
    }
}
