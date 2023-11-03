package digix.cadastro.utils.builder;

import java.util.ArrayList;
import java.util.List;

import com.github.javafaker.Faker;

import digix.cadastro.domain.Participante;
import digix.cadastro.domain.Pessoa;

public class ParticipanteBuilder {

    private Pessoa titular;
    private List<Pessoa> familia;

    public ParticipanteBuilder() {
        Faker faker = new Faker();
        titular = new PessoaBuilder().criar();
        familia = new ArrayList<Pessoa>();
        for(int i = 0; i < faker.number().numberBetween(0, 10); i++) {
            Pessoa dependente = new PessoaBuilder().criar();
            familia.add(dependente);
        }
    }

    public Participante criar() {
        return new Participante(this.titular, this.familia);
    }
    
}
