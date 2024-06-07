package br.com.fiap.CleanOceanic.controllers.dtos.endereco;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record EnderecoRegisterDTO(
        @NotNull
        Long idEndereco,
        @NotBlank
        String logradouro,
        @NotBlank
        String numero,
        @NotBlank
        String bairro,
        @NotBlank
        String cidade,
        @NotBlank
        String sigla,
        @NotBlank
        String cep
) {
}
