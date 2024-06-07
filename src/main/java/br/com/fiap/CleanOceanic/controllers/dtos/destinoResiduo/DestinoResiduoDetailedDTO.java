package br.com.fiap.CleanOceanic.controllers.dtos.destinoResiduo;

import br.com.fiap.CleanOceanic.controllers.dtos.residuoColetado.ResiduoColetadoDetailedDTO;
import br.com.fiap.CleanOceanic.models.DestinoResiduo;

public record DestinoResiduoDetailedDTO(

        Long id,
        String nomeLocal,
        String tipoDestino,

        double quantidadeEnviado,

        ResiduoColetadoDetailedDTO residuoColetado

) {

    public DestinoResiduoDetailedDTO(DestinoResiduo destinoResiduo) {
        this(destinoResiduo.getIdDestinoResiduo(), destinoResiduo.getNomeLocal(), destinoResiduo.getTipoDestino(), destinoResiduo.getQuantidadeEnviado(),
                new ResiduoColetadoDetailedDTO(destinoResiduo.getResiduoColetado()));
    }

}
