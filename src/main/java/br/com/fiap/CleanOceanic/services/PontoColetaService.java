package br.com.fiap.CleanOceanic.services;

import br.com.fiap.CleanOceanic.controllers.dtos.pontoColeta.PontoColetaDetailedDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.pontoColeta.PontoColetaRegisterDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.pontoColeta.PontoColetaUpdateDTO;
import br.com.fiap.CleanOceanic.models.PontoColeta;
import br.com.fiap.CleanOceanic.repositories.PontoColetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PontoColetaService {

    private final PontoColetaRepository pontoColetaRepository;

    @Autowired
    public PontoColetaService(PontoColetaRepository pontoColetaRepository) {
        this.pontoColetaRepository = pontoColetaRepository;
    }

    public PontoColeta find(Long id) {
        return pontoColetaRepository.findOneByIdPontoColeta(id);
    }

    public PontoColetaDetailedDTO create(PontoColetaRegisterDTO pontoColetaRegisterDTO) {
        PontoColeta pontoColeta = new PontoColeta(pontoColetaRegisterDTO);
        return new PontoColetaDetailedDTO(pontoColetaRepository.save(pontoColeta));
    }

    public Page<PontoColetaDetailedDTO> list(Pageable pagination) {
        return pontoColetaRepository.findAll(pagination).map(PontoColetaDetailedDTO::new);
    }

    public PontoColetaDetailedDTO get(Long id) {

        return new PontoColetaDetailedDTO(pontoColetaRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Ponto Coleta com o ID: {"+ id +"} Não encontrado na base de dados.")));
    }

    public PontoColetaDetailedDTO update(Long id, PontoColetaUpdateDTO pontoColetaUpdateDTO) {
        PontoColeta pontoColeta = pontoColetaRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Ponto Coleta com o ID: {"+ id +"} Não encontrado na base de dados."));

        pontoColeta.updateInformation(pontoColetaUpdateDTO);

        pontoColetaRepository.save(pontoColeta);

        return new PontoColetaDetailedDTO(pontoColeta);
    }

    public void delete(Long id) {
        pontoColetaRepository.deleteById(id);
    }


}
