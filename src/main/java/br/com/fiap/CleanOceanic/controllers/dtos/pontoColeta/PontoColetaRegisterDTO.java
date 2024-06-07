package br.com.fiap.CleanOceanic.controllers.dtos.pontoColeta;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record PontoColetaRegisterDTO(

        @NotNull
        Long idPontoColeta,

        @NotBlank
        String nome,

        @NotBlank
        String telefone,

        @NotBlank
        String email,

        @NotBlank
        String horarioFuncionamento,

        @NotNull
        double capacidadeMaxima

) {
}
