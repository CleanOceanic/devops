package br.com.fiap.CleanOceanic.repositories;

import br.com.fiap.CleanOceanic.models.PontoColetaEndereco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PontoColetaEnderecoRepository extends JpaRepository<PontoColetaEndereco, Long> {

    Page<PontoColetaEndereco> findAll(Pageable pagination);

    PontoColetaEndereco findOneByIdPontoColetaEndereco(Long idPontoColetaEndereco);
}
