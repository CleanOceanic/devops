package br.com.fiap.CleanOceanic.controllers.dtos.empresa;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record EmpresaRegisterDTO(

        @NotNull
        Long idEmpresa,
        @NotBlank
        String razaoSocial,
        @NotBlank
        String cnpj,
        @NotBlank
        String telefone,
        @NotBlank
        String email,
        @NotNull
        int quantidadeFuncionario,
        @NotBlank
        String setor

) {
}
