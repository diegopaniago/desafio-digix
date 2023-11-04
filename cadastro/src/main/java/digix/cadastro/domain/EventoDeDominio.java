package digix.cadastro.domain;

import java.time.LocalDateTime;

import org.springframework.context.ApplicationEvent;

public class EventoDeDominio extends ApplicationEvent {

    private LocalDateTime data;

    public EventoDeDominio(Object source) {
        super(source);
        this.data = LocalDateTime.now();
    }

    public LocalDateTime getData() {
        return data;
    }
    
}
