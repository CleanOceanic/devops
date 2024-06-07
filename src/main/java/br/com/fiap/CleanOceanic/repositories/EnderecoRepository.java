package br.com.fiap.CleanOceanic.repositories;

import br.com.fiap.CleanOceanic.models.Endereco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    Page<Endereco> findAll(Pageable pagination);

    Endereco findOneByIdEndereco(Long idEndereco);

}
