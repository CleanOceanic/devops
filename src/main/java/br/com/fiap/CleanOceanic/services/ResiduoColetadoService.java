package br.com.fiap.CleanOceanic.services;

import br.com.fiap.CleanOceanic.controllers.dtos.residuoColetado.ResiduoColetadoDetailedDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.residuoColetado.ResiduoColetadoRegisterDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.residuoColetado.ResiduoColetadoUpdateDTO;
import br.com.fiap.CleanOceanic.models.ResiduoColetado;
import br.com.fiap.CleanOceanic.models.Usuario;
import br.com.fiap.CleanOceanic.repositories.ResiduoColetadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ResiduoColetadoService {

    private final ResiduoColetadoRepository residuoColetadoRepository;

    private final UsuarioService usuarioService;

    @Autowired
    public ResiduoColetadoService(ResiduoColetadoRepository residuoColetadoRepository, UsuarioService usuarioService) {
        this.residuoColetadoRepository = residuoColetadoRepository;
        this.usuarioService = usuarioService;
    }

    public ResiduoColetado find (Long id){
        return residuoColetadoRepository.findOneByIdResiduoColetado(id);
    }

    public ResiduoColetadoDetailedDTO create(ResiduoColetadoRegisterDTO residuoColetadoRegisterDTO) {
        ResiduoColetado residuoColetado = new ResiduoColetado(residuoColetadoRegisterDTO);

        Usuario usuario = usuarioService.find(residuoColetadoRegisterDTO.idUsuario());

        residuoColetado.setUsuario(usuario);

        return new ResiduoColetadoDetailedDTO(residuoColetadoRepository.save(residuoColetado));
    }

    public Page<ResiduoColetadoDetailedDTO> list(Pageable pagination) {
        return residuoColetadoRepository.findAll(pagination).map(ResiduoColetadoDetailedDTO::new);
    }

    public ResiduoColetadoDetailedDTO get(Long id) {
        ResiduoColetado residuoColetado = residuoColetadoRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Residuo Coletado com o ID: {"+ id +"} Não encontrado na base de dados."));

        return new ResiduoColetadoDetailedDTO(residuoColetado);
    }

    public ResiduoColetadoDetailedDTO update(Long id, ResiduoColetadoUpdateDTO residuoColetadoUpdateDTO) {
        ResiduoColetado residuoColetado = residuoColetadoRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Residuo Coletado com o ID: {"+ id +"} Não encontrado na base de dados."));

        residuoColetado.updateInformation(residuoColetadoUpdateDTO);

        residuoColetadoRepository.save(residuoColetado);

        return new ResiduoColetadoDetailedDTO(residuoColetado);
    }

    public void delete(Long id) {
        residuoColetadoRepository.deleteById(id);
    }

}
