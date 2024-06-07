package br.com.fiap.CleanOceanic.controllers.dtos.usuario;

import br.com.fiap.CleanOceanic.models.Usuario;

public record UsuarioDetailedDTO(

        Long id,
        String nome,
        String dataNascimento,
        String genero,
        String email,

        String senha,

        String telefone

) {

    public UsuarioDetailedDTO(Usuario usuario) {
        this(
                usuario.getIdUsuario(), usuario.getNome(), usuario.getDataNascimento(), usuario.getGenero(),
                usuario.getEmail(), usuario.getSenha(), usuario.getTelefone()
        );
    }

}
