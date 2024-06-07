package br.com.fiap.CleanOceanic.controllers.dtos.usuarioEndereco;

import javax.validation.constraints.NotNull;

public record UsuarioEnderecoRegisterDTO(

        @NotNull
        Long idUsuario,

        @NotNull
        Long idEndereco

) {
}
