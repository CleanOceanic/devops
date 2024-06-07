package br.com.fiap.CleanOceanic.repositories;

import br.com.fiap.CleanOceanic.models.UsuarioEndereco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioEnderecoRepository extends JpaRepository<UsuarioEndereco, Long> {

    Page<UsuarioEndereco> findAll(Pageable pagination);

}
