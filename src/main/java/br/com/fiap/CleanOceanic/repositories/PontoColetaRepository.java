package br.com.fiap.CleanOceanic.repositories;

import br.com.fiap.CleanOceanic.models.PontoColeta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PontoColetaRepository extends JpaRepository<PontoColeta, Long> {

    Page<PontoColeta> findAll(Pageable pagination);

    PontoColeta findOneByIdPontoColeta(Long idPontoColeta);

}
