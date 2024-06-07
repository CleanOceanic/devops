package br.com.fiap.CleanOceanic.controllers.dtos.destinoResiduoPontoColeta;

import javax.validation.constraints.NotNull;

public record DestinoResiduoPontoColetaRegisterDTO(

        @NotNull
        Long idDestinoResiduo,

        @NotNull
        Long idPontoColeta

) {
}
