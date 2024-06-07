package br.com.fiap.CleanOceanic.controllers.dtos.empresa;

import br.com.fiap.CleanOceanic.models.Empresa;

public record EmpresaDetailedDTO(

        Long id,
        String razaoSocial,
        String cnpj,
        String telefone,
        String email,
        int quantidadeFuncionario,
        String setor

) {

    public EmpresaDetailedDTO(Empresa empresa) {
        this(
                empresa.getIdEmpresa(), empresa.getRazaoSocial(),
                empresa.getCnpj(), empresa.getTelefone(),
                empresa.getEmail(), empresa.getQuantidadeFuncionario(), empresa.getSetor()
        );
    }

}
