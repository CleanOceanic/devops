package br.com.fiap.CleanOceanic.models;

import br.com.fiap.CleanOceanic.controllers.dtos.usuario.UsuarioRegisterDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.usuario.UsuarioUpdateDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_USUARIO")
@SequenceGenerator(name = "SEQ_T_USUARIO", sequenceName = "SEQ_T_USUARIO", allocationSize = 1)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_T_USUARIO")
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "ds_nome", nullable = false)
    private String nome;

    @Column(name = "dt_data_nascimento", nullable = false)
    private String dataNascimento;

    @Column(name = "ds_genero", nullable = false)
    private String genero;

    @Column(name = "ds_telefone", nullable = false)
    private String telefone;

    @Column(name = "ds_email", nullable = false, unique = true)
    private String email;

    @Column(name = "ds_senha", nullable = false)
    private String senha;

    @Column(name = "dt_created_at", nullable = false)
    private LocalDateTime createdAt;

    public Usuario(UsuarioRegisterDTO usuarioRegisterDTO) {
        this.nome = usuarioRegisterDTO.nome();
        this.dataNascimento = usuarioRegisterDTO.dataNascimento();
        this.genero = usuarioRegisterDTO.genero();
        this.telefone = usuarioRegisterDTO.telefone();
        this.email = usuarioRegisterDTO.email();
        this.senha = usuarioRegisterDTO.senha();
        this.createdAt = LocalDateTime.now();
    }

    public void updateInformation(UsuarioUpdateDTO usuarioUpdateDTO) {
        if (usuarioUpdateDTO.nome() != null) {
            this.nome = usuarioUpdateDTO.nome();
        }

        if (usuarioUpdateDTO.dataNascimento() != null) {
            this.dataNascimento = usuarioUpdateDTO.dataNascimento();
        }

        if (usuarioUpdateDTO.genero() != null) {
            this.genero = usuarioUpdateDTO.genero();
        }

        if (usuarioUpdateDTO.telefone() != null) {
            this.telefone = usuarioUpdateDTO.telefone();
        }

        if (usuarioUpdateDTO.email() != null) {
            this.email = usuarioUpdateDTO.email();
        }

        if (usuarioUpdateDTO.senha() != null) {
            this.senha = usuarioUpdateDTO.senha();
        }
    }
}
