package br.com.fiap.CleanOceanic.services;

import br.com.fiap.CleanOceanic.controllers.dtos.usuario.UsuarioDetailedDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.usuario.UsuarioRegisterDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.usuario.UsuarioUpdateDTO;
import br.com.fiap.CleanOceanic.models.Usuario;
import br.com.fiap.CleanOceanic.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario find(Long id) {
        return usuarioRepository.findOneByIdUsuario(id);
    }

    public UsuarioDetailedDTO create(UsuarioRegisterDTO usuarioRegisterDTO) {
        Usuario usuario = new Usuario(usuarioRegisterDTO);
        return new UsuarioDetailedDTO(usuarioRepository.save(usuario));
    }

    public Page<UsuarioDetailedDTO> list(Pageable pagination) {
        return usuarioRepository.findAll(pagination).map(UsuarioDetailedDTO::new);
    }

    public UsuarioDetailedDTO get(Long id) {

        return new UsuarioDetailedDTO(usuarioRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Usuario com o ID: {"+ id +"} Não encontrado na base de dados.")));
    }

    public UsuarioDetailedDTO update(Long id, UsuarioUpdateDTO usuarioUpdateDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Usuario com o ID: {"+ id +"} Não encontrado na base de dados."));

        usuario.updateInformation(usuarioUpdateDTO);

        usuarioRepository.save(usuario);

        return new UsuarioDetailedDTO(usuario);
    }

    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

}
