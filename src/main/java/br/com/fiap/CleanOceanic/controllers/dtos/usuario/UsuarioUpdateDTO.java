package br.com.fiap.CleanOceanic.controllers.dtos.usuario;

public record UsuarioUpdateDTO(

        String nome,

        String dataNascimento,

        String genero,

        String email,

        String senha,

        String telefone

) {
}
