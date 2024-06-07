package br.com.fiap.CleanOceanic.repositories;

import br.com.fiap.CleanOceanic.models.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Page<Usuario> findAll(Pageable pagination);

    Usuario findOneByIdUsuario(Long idUsuario);

}
