package br.com.fiap.CleanOceanic.controllers.dtos.empresaEndereco;

import javax.validation.constraints.NotNull;

public record EmpresaEnderecoRegisterDTO(

        @NotNull
        Long idEmpresa,

        @NotNull
        Long idEndereco

) {
}
