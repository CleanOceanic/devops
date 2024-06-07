package br.com.fiap.CleanOceanic.controllers.dtos.pontoColetaEndereco;

import javax.validation.constraints.NotNull;

public record PontoColetaEnderecoRegisterDTO(

        @NotNull
        Long idPontoColeta,

        @NotNull
        Long idEndereco

) {
}
