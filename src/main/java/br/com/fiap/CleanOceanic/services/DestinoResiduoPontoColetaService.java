package br.com.fiap.CleanOceanic.services;

import br.com.fiap.CleanOceanic.controllers.dtos.destinoResiduoPontoColeta.DestinoResiduoPontoColetaDetailedDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.destinoResiduoPontoColeta.DestinoResiduoPontoColetaRegisterDTO;
import br.com.fiap.CleanOceanic.models.DestinoResiduo;
import br.com.fiap.CleanOceanic.models.DestinoResiduoPontoColeta;
import br.com.fiap.CleanOceanic.models.PontoColeta;
import br.com.fiap.CleanOceanic.repositories.DestinoResiduoPontoColetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DestinoResiduoPontoColetaService {

    private final DestinoResiduoPontoColetaRepository destinoResiduoPontoColetaRepository;

    private final DestinoResiduoService destinoResiduoService;

    private final PontoColetaService pontoColetaService;

    @Autowired
    public DestinoResiduoPontoColetaService(DestinoResiduoPontoColetaRepository destinoResiduoPontoColetaRepository,
                                            DestinoResiduoService destinoResiduoService,
                                            PontoColetaService pontoColetaService) {
        this.destinoResiduoPontoColetaRepository = destinoResiduoPontoColetaRepository;
        this.destinoResiduoService = destinoResiduoService;
        this.pontoColetaService = pontoColetaService;
    }

    public DestinoResiduoPontoColeta create(DestinoResiduoPontoColetaRegisterDTO destinoResiduoPontoColetaRegisterDTO) {

        DestinoResiduoPontoColeta destinoResiduoPontoColeta = new DestinoResiduoPontoColeta(destinoResiduoPontoColetaRegisterDTO);

        DestinoResiduo destinoResiduo = destinoResiduoService.find(destinoResiduoPontoColetaRegisterDTO.idDestinoResiduo());

        PontoColeta pontoColeta = pontoColetaService.find(destinoResiduoPontoColetaRegisterDTO.idPontoColeta());

        destinoResiduoPontoColeta.setDestinoResiduo(destinoResiduo);

        destinoResiduoPontoColeta.setPontoColeta(pontoColeta);

        return destinoResiduoPontoColetaRepository.save(destinoResiduoPontoColeta);
    }

    public Page<DestinoResiduoPontoColetaDetailedDTO> list(Pageable pagination) {
        return destinoResiduoPontoColetaRepository.findAll(pagination).map(DestinoResiduoPontoColetaDetailedDTO::new);
    }

    public DestinoResiduoPontoColetaDetailedDTO get(Long id) {
        DestinoResiduoPontoColeta destinoResiduoPontoColeta = destinoResiduoPontoColetaRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Destino Residuo Ponto Coleta com o ID: {"+ id +"} Não encontrado na base de dados."));

        return new DestinoResiduoPontoColetaDetailedDTO(destinoResiduoPontoColeta);
    }

    public void delete(Long id) {
        destinoResiduoPontoColetaRepository.deleteById(id);
    }

}
