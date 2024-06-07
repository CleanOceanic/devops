package br.com.fiap.CleanOceanic.controllers.dtos.destinoResiduo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record DestinoResiduoRegisterDTO(

        @NotBlank
        String nomeLocal,
        @NotBlank
        String tipoDestino,

        @NotNull
        double quantidadeEnviado,

        @NotNull
        Long idResiduoColetado

) {
}
