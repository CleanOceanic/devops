package br.com.fiap.CleanOceanic.controllers.dtos.pontoColeta;

import br.com.fiap.CleanOceanic.models.PontoColeta;

public record PontoColetaDetailedDTO(

        Long id,
        String nome,

        String telefone,

        String email,

        String horarioFuncionamento,

        double capacidadeMaxima

) {

    public PontoColetaDetailedDTO(PontoColeta pontoColeta) {
        this(
                pontoColeta.getIdPontoColeta(), pontoColeta.getNome(),
                pontoColeta.getTelefone(), pontoColeta.getEmail(),
                pontoColeta.getHorarioFuncionamento(), pontoColeta.getCapacidadeMaxima()
        );
    }

}
