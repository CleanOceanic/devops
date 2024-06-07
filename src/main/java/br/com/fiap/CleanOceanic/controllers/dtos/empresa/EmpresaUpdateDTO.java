package br.com.fiap.CleanOceanic.controllers.dtos.empresa;

public record EmpresaUpdateDTO(

        String razaoSocial,
        String cnpj,
        String telefone,
        String email,
        int quantidadeFuncionario,
        String setor

) {
}
