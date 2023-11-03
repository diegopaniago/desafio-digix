package digix.cadastro.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipanteRepositorio extends JpaRepository<Participante, String>{
    
}
