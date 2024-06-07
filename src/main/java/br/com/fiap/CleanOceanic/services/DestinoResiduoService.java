package br.com.fiap.CleanOceanic.services;

import br.com.fiap.CleanOceanic.controllers.dtos.destinoResiduo.DestinoResiduoDetailedDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.destinoResiduo.DestinoResiduoRegisterDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.destinoResiduo.DestinoResiduoUpdateDTO;
import br.com.fiap.CleanOceanic.models.DestinoResiduo;
import br.com.fiap.CleanOceanic.models.ResiduoColetado;
import br.com.fiap.CleanOceanic.repositories.DestinoResiduoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DestinoResiduoService {

    private final DestinoResiduoRepository destinoResiduoRepository;

    private final ResiduoColetadoService residuoColetadoService;

    @Autowired
    public DestinoResiduoService(DestinoResiduoRepository destinoResiduoRepository, ResiduoColetadoService residuoColetadoService) {
        this.destinoResiduoRepository = destinoResiduoRepository;
        this.residuoColetadoService = residuoColetadoService;
    }

    public DestinoResiduo find (Long id){
        return destinoResiduoRepository.findOneByIdDestinoResiduo(id);
    }

    public DestinoResiduoDetailedDTO create(DestinoResiduoRegisterDTO destinoResiduoRegisterDTO) {
        DestinoResiduo destinoResiduo = new DestinoResiduo(destinoResiduoRegisterDTO);

        ResiduoColetado residuoColetado = residuoColetadoService.find(destinoResiduoRegisterDTO.idResiduoColetado());

        destinoResiduo.setResiduoColetado(residuoColetado);

        return new DestinoResiduoDetailedDTO(destinoResiduoRepository.save(destinoResiduo));
    }


    public Page<DestinoResiduoDetailedDTO> list(Pageable pagination) {
        return destinoResiduoRepository.findAll(pagination).map(DestinoResiduoDetailedDTO::new);
    }

    public DestinoResiduoDetailedDTO get(Long id) {
        DestinoResiduo destinoResiduo = destinoResiduoRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Destino Residuo com o ID: {"+ id +"} Não encontrado na base de dados."));

        return new DestinoResiduoDetailedDTO(destinoResiduo);
    }

    public DestinoResiduoDetailedDTO update(Long id, DestinoResiduoUpdateDTO destinoResiduoUpdateDTO) {
        DestinoResiduo destinoResiduo = destinoResiduoRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Destino Residuo com o ID: {"+ id +"} Não encontrado na base de dados."));

        destinoResiduo.updateInformation(destinoResiduoUpdateDTO);

        destinoResiduoRepository.save(destinoResiduo);

        return new DestinoResiduoDetailedDTO(destinoResiduo);
    }

    public void delete(Long id) {
        destinoResiduoRepository.deleteById(id);
    }

}
