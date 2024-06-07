package br.com.fiap.CleanOceanic.repositories;

import br.com.fiap.CleanOceanic.models.DestinoResiduo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinoResiduoRepository extends JpaRepository<DestinoResiduo, Long> {

    Page<DestinoResiduo> findAll (Pageable pagination);

    DestinoResiduo findOneByIdDestinoResiduo(Long idDestinoResiduo);

}
