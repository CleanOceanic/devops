package br.com.fiap.CleanOceanic.repositories;

import br.com.fiap.CleanOceanic.models.Empresa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    Page<Empresa> findAll(Pageable pagination);

    Empresa findOneByIdEmpresa(Long idEmpresa);

}
