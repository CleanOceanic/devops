package br.com.fiap.CleanOceanic.controllers.dtos.usuario;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record UsuarioRegisterDTO(
        @NotNull
        Long idUsuario,

        @NotBlank
        String nome,

        @NotBlank
        String dataNascimento,

        @NotBlank
        String genero,

        @NotBlank
        String email,

        @NotBlank
        String senha,

        @NotBlank
        String telefone
) {
}
