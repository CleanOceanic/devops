package br.com.fiap.CleanOceanic.controllers.dtos.destinoResiduo;

public record DestinoResiduoUpdateDTO(

        String nomeLocal,
        String tipoDestino,

        double quantidadeEnviado

) {
}
