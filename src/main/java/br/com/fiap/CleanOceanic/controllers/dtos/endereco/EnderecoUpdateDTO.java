package br.com.fiap.CleanOceanic.controllers.dtos.endereco;

public record EnderecoUpdateDTO(
        String logradouro,
        String numero,
        String bairro,
        String cidade,
        String sigla,
        String cep
) {
}
