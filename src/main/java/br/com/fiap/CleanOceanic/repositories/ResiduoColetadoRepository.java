package br.com.fiap.CleanOceanic.repositories;

import br.com.fiap.CleanOceanic.models.ResiduoColetado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResiduoColetadoRepository extends JpaRepository<ResiduoColetado, Long> {

    Page<ResiduoColetado> findAll (Pageable pagination);

    ResiduoColetado findOneByIdResiduoColetado(Long idResiduoColetado);

}
