package br.com.fiap.CleanOceanic.controllers.dtos.residuoColetado;

import br.com.fiap.CleanOceanic.controllers.dtos.usuario.UsuarioDetailedDTO;
import br.com.fiap.CleanOceanic.models.ResiduoColetado;
import br.com.fiap.CleanOceanic.models.Usuario;

import java.util.List;

public record ResiduoColetadoDetailedDTO(

        Long id,

        String tipo,

        double peso,

        String dataColeta,

        String localColeta,

        String origemResiduo,

        String observacaoColeta,

        UsuarioDetailedDTO usuario
) {

    public ResiduoColetadoDetailedDTO(ResiduoColetado residuoColetado) {
        this(residuoColetado.getIdResiduoColetado(), residuoColetado.getTipo(), residuoColetado.getPeso(),
                residuoColetado.getDataColeta(), residuoColetado.getLocalColeta(), residuoColetado.getOrigemResiduo(),
                residuoColetado.getObservacaoColeta(),
                new UsuarioDetailedDTO(residuoColetado.getUsuario()));
    }

}
