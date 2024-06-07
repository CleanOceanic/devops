package br.com.fiap.CleanOceanic.controllers.dtos.pontoColetaEndereco;

import br.com.fiap.CleanOceanic.controllers.dtos.endereco.EnderecoDetailedDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.pontoColeta.PontoColetaDetailedDTO;
import br.com.fiap.CleanOceanic.models.PontoColetaEndereco;

import java.time.LocalDateTime;

public record PontoColetaEnderecoDetailedDTO(

        Long idPontoColetaEndereco,
        LocalDateTime createdAt,
        PontoColetaDetailedDTO pontoColeta,

        EnderecoDetailedDTO endereco

) {

    public PontoColetaEnderecoDetailedDTO(PontoColetaEndereco pontoColetaEndereco) {
        this(pontoColetaEndereco.getIdPontoColetaEndereco(),
                pontoColetaEndereco.getCreatedAt(),
                new PontoColetaDetailedDTO(pontoColetaEndereco.getPontoColeta()),
                new EnderecoDetailedDTO(pontoColetaEndereco.getEndereco())
        );
    }

}
