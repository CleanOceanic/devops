package br.com.fiap.CleanOceanic.controllers.dtos.empresaEndereco;

import br.com.fiap.CleanOceanic.controllers.dtos.empresa.EmpresaDetailedDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.endereco.EnderecoDetailedDTO;
import br.com.fiap.CleanOceanic.models.EmpresaEndereco;

import java.time.LocalDateTime;

public record EmpresaEnderecoDetailedDTO(

        Long idEmpresaEndereco,
        LocalDateTime createdAt,
        EmpresaDetailedDTO empresa,

        EnderecoDetailedDTO endereco

) {

    public EmpresaEnderecoDetailedDTO(EmpresaEndereco empresaEndereco) {
        this(empresaEndereco.getIdEmpresaEndereco(), empresaEndereco.getCreatedAt(),
                new EmpresaDetailedDTO(empresaEndereco.getEmpresa()),
                new EnderecoDetailedDTO(empresaEndereco.getEndereco())
        );
    }

}
