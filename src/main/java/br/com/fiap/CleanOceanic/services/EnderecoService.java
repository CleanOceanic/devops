package br.com.fiap.CleanOceanic.services;

import br.com.fiap.CleanOceanic.controllers.dtos.endereco.EnderecoDetailedDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.endereco.EnderecoRegisterDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.endereco.EnderecoUpdateDTO;
import br.com.fiap.CleanOceanic.models.Endereco;
import br.com.fiap.CleanOceanic.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    @Autowired
    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public Endereco find (Long id){
        return enderecoRepository.findOneByIdEndereco(id);
    }

    public EnderecoDetailedDTO create(EnderecoRegisterDTO enderecoRegisterDTO) {
        Endereco endereco = new Endereco(enderecoRegisterDTO);
        return new EnderecoDetailedDTO(enderecoRepository.save(endereco));
    }

    public Page<EnderecoDetailedDTO> list(Pageable pagination) {
        return enderecoRepository.findAll(pagination).map(EnderecoDetailedDTO::new);
    }

    public EnderecoDetailedDTO get(Long id) {
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Endereço com o ID: {"+ id +"} Não encontrado na base de dados."));

        return new EnderecoDetailedDTO(endereco);
    }

    public EnderecoDetailedDTO update(Long id, EnderecoUpdateDTO enderecoUpdateDTO) {
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Endereço com o ID: {"+ id +"} Não encontrado na base de dados."));

        endereco.updateInformation(enderecoUpdateDTO);

        enderecoRepository.save(endereco);

        return new EnderecoDetailedDTO(endereco);
    }

    public void delete(Long id) {
        enderecoRepository.deleteById(id);
    }

}
