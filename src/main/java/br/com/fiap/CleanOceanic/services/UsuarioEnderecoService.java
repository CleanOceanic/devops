package br.com.fiap.CleanOceanic.services;

import br.com.fiap.CleanOceanic.controllers.dtos.usuarioEndereco.UsuarioEnderecoDetailedDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.usuarioEndereco.UsuarioEnderecoRegisterDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.usuarioEndereco.UsuarioEnderecoUpdateDTO;
import br.com.fiap.CleanOceanic.models.Endereco;
import br.com.fiap.CleanOceanic.models.Usuario;
import br.com.fiap.CleanOceanic.models.UsuarioEndereco;
import br.com.fiap.CleanOceanic.repositories.UsuarioEnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioEnderecoService {

    private final UsuarioEnderecoRepository usuarioEnderecoRepository;

    private final UsuarioService usuarioService;

    private final EnderecoService enderecoService;

    @Autowired
    public UsuarioEnderecoService(UsuarioEnderecoRepository usuarioEnderecoRepository, UsuarioService usuarioService, EnderecoService enderecoService) {
        this.usuarioEnderecoRepository = usuarioEnderecoRepository;
        this.usuarioService = usuarioService;
        this.enderecoService = enderecoService;
    }

    public UsuarioEndereco create(UsuarioEnderecoRegisterDTO usuarioEnderecoRegisterDTO) {

        UsuarioEndereco usuarioEndereco = new UsuarioEndereco(usuarioEnderecoRegisterDTO);

        Usuario usuario = usuarioService.find(usuarioEnderecoRegisterDTO.idUsuario());

        Endereco endereco = enderecoService.find(usuarioEnderecoRegisterDTO.idEndereco());

        usuarioEndereco.setUsuario(usuario);

        usuarioEndereco.setEndereco(endereco);

        return usuarioEnderecoRepository.save(usuarioEndereco);
    }

    public Page<UsuarioEnderecoDetailedDTO> list(Pageable pagination) {
        return usuarioEnderecoRepository.findAll(pagination).map(UsuarioEnderecoDetailedDTO::new);
    }

    public UsuarioEnderecoDetailedDTO get(Long id) {
        UsuarioEndereco usuarioEndereco = usuarioEnderecoRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Usuario Endereço com o ID: {"+ id +"} Não encontrado na base de dados."));

        return new UsuarioEnderecoDetailedDTO(usuarioEndereco);
    }

    public void delete(Long id) {
        usuarioEnderecoRepository.deleteById(id);
    }
}
