package br.com.fiap.CleanOceanic.controllers.dtos.residuoColetado;

import br.com.fiap.CleanOceanic.models.Usuario;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record ResiduoColetadoRegisterDTO(
        @NotBlank
        String tipo,
        @NotNull
        double peso,
        @NotBlank
        String dataColeta,
        @NotBlank
        String localColeta,
        @NotBlank
        String origemResiduo,
        @NotBlank
        String observacaoColeta,

        @NotNull
        Long idUsuario

) {

}
