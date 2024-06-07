package br.com.fiap.CleanOceanic.controllers.dtos.residuoColetado;

public record ResiduoColetadoUpdateDTO(

        String tipo,
        double peso,
        String dataColeta,
        String localColeta,
        String origemResiduo,
        String observacaoColeta

) {
}
