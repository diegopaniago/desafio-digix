package digix.cadastro.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import digix.cadastro.utils.ValidadorDeCamposObrigatorios;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Participante {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @OneToOne(cascade = CascadeType.ALL)
    private Pessoa titular;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Pessoa> familia;

    @Column
    private LocalDateTime cadastradoEm;

    public Participante(Pessoa titular, List<Pessoa> familia) {
        validarCamposObrigatorios(titular);
        this.titular = titular;
        this.familia = familia;
        this.cadastradoEm = LocalDateTime.now();
    }

    private void validarCamposObrigatorios(Pessoa titular) {
        ValidadorDeCamposObrigatorios validadorDeCamposObrigatorios = new ValidadorDeCamposObrigatorios();
        validadorDeCamposObrigatorios
            .ehNulo(titular, "É obrigatório informar os dados do participante titular.")
            .entaoDispara();
    }
}
