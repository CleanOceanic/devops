package br.com.fiap.CleanOceanic.repositories;

import br.com.fiap.CleanOceanic.models.EmpresaEndereco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaEnderecoRepository extends JpaRepository<EmpresaEndereco, Long> {

    Page<EmpresaEndereco> findAll(Pageable pagination);

}
