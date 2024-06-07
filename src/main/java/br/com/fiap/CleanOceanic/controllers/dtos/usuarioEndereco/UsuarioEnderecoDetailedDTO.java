package br.com.fiap.CleanOceanic.controllers.dtos.usuarioEndereco;

import br.com.fiap.CleanOceanic.controllers.dtos.endereco.EnderecoDetailedDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.usuario.UsuarioDetailedDTO;
import br.com.fiap.CleanOceanic.models.UsuarioEndereco;

import java.time.LocalDateTime;

public record UsuarioEnderecoDetailedDTO(

        Long idUsuarioEndereco,
        LocalDateTime createdAt,
        UsuarioDetailedDTO usuario,

        EnderecoDetailedDTO endereco

) {

    public UsuarioEnderecoDetailedDTO(UsuarioEndereco usuarioEndereco) {
        this(usuarioEndereco.getIdUsuarioEndereco(), usuarioEndereco.getCreatedAt(),
                new UsuarioDetailedDTO(usuarioEndereco.getUsuario()),
                new EnderecoDetailedDTO(usuarioEndereco.getEndereco())
        );
    }

}
