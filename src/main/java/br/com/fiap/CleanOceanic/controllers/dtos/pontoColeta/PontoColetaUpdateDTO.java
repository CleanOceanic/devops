package br.com.fiap.CleanOceanic.controllers.dtos.pontoColeta;

public record PontoColetaUpdateDTO(

        String nome,

        String telefone,

        String email,

        String horarioFuncionamento,

        double capacidadeMaxima


) {
}
