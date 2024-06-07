package br.com.fiap.CleanOceanic.controllers.dtos.endereco;

import br.com.fiap.CleanOceanic.models.Endereco;
import br.com.fiap.CleanOceanic.models.Usuario;

public record EnderecoDetailedDTO(
        Long id,
        String logradouro,
        String numero,
        String bairro,
        String cidade,
        String sigla,
        String cep
) {
    public EnderecoDetailedDTO(Endereco endereco) {
        this(
                endereco.getIdEndereco(), endereco.getLogradouro(), endereco.getNumero(),
                endereco.getBairro(), endereco.getCidade(), endereco.getSigla(), endereco.getCep()
        );
    }
}
