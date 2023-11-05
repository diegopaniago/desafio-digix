package digix.selecao.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.github.javafaker.Faker;

import digix.selecao.application.ParticipanteDto;
import digix.selecao.application.PessoaDto;

public class ParticipanteDtoBuilder {
    
    private String id; 
    private PessoaDto titular;
    private List<PessoaDto> familia;

    public ParticipanteDtoBuilder() {
        Faker faker = new Faker();
        id = UUID.randomUUID().toString();
        titular = new PessoaDtoBuilder().criar();
        familia = new ArrayList<PessoaDto>();
        int quantidadeDeDepentes = faker.number().numberBetween(1, 5);
        for(int i = 0; i < quantidadeDeDepentes; i++) {
            familia.add(new PessoaDtoBuilder().criar());
        }
    }

    public ParticipanteDtoBuilder comTitular(PessoaDto titular) {
        this.titular = titular;
        return this;
    }

    public ParticipanteDtoBuilder comFamilia(List<PessoaDto> familia) {
        this.familia = familia;
        return this;
    }

    public ParticipanteDto criar() {
        return new ParticipanteDto(id, titular, familia);
    }
}
