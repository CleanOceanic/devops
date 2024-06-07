package br.com.fiap.CleanOceanic.controllers.dtos.destinoResiduoPontoColeta;

import br.com.fiap.CleanOceanic.controllers.dtos.destinoResiduo.DestinoResiduoDetailedDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.pontoColeta.PontoColetaDetailedDTO;
import br.com.fiap.CleanOceanic.models.DestinoResiduoPontoColeta;

import java.time.LocalDateTime;

public record DestinoResiduoPontoColetaDetailedDTO(


        Long idDestinoResiduoPontoColeta,
        LocalDateTime createdAt,
        DestinoResiduoDetailedDTO destinoResiduo,

        PontoColetaDetailedDTO PontoColeta

) {

    public DestinoResiduoPontoColetaDetailedDTO(DestinoResiduoPontoColeta destinoResiduoPontoColeta) {
        this(destinoResiduoPontoColeta.getIdDestinoResiduoPontoColeta(),
                destinoResiduoPontoColeta.getCreatedAt(),
                new DestinoResiduoDetailedDTO(destinoResiduoPontoColeta.getDestinoResiduo()),
                new PontoColetaDetailedDTO(destinoResiduoPontoColeta.getPontoColeta())
        );
    }

}
