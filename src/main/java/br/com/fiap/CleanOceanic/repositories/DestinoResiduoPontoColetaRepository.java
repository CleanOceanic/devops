package br.com.fiap.CleanOceanic.repositories;

import br.com.fiap.CleanOceanic.models.DestinoResiduoPontoColeta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinoResiduoPontoColetaRepository extends JpaRepository<DestinoResiduoPontoColeta, Long> {

    Page<DestinoResiduoPontoColeta> findAll(Pageable pagination);

}
