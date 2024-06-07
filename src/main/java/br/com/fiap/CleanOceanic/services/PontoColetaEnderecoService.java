package br.com.fiap.CleanOceanic.services;

import br.com.fiap.CleanOceanic.controllers.dtos.pontoColetaEndereco.PontoColetaEnderecoDetailedDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.pontoColetaEndereco.PontoColetaEnderecoRegisterDTO;
import br.com.fiap.CleanOceanic.models.Endereco;
import br.com.fiap.CleanOceanic.models.PontoColeta;
import br.com.fiap.CleanOceanic.models.PontoColetaEndereco;
import br.com.fiap.CleanOceanic.repositories.PontoColetaEnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PontoColetaEnderecoService {

    private final PontoColetaEnderecoRepository destinoResiduoPontoColetaRepository;

    private final PontoColetaService pontoColetaService;

    private final EnderecoService enderecoService;

    @Autowired
    public PontoColetaEnderecoService(PontoColetaEnderecoRepository pontoColetaEnderecoRepository,
                                            PontoColetaService pontoColetaService,
                                            EnderecoService enderecoService) {
        this.destinoResiduoPontoColetaRepository = pontoColetaEnderecoRepository;
        this.pontoColetaService = pontoColetaService;
        this.enderecoService = enderecoService;
    }

    public PontoColetaEndereco create(PontoColetaEnderecoRegisterDTO pontoColetaEnderecoRegisterDTO) {

        PontoColetaEndereco pontoColetaEndereco = new PontoColetaEndereco(pontoColetaEnderecoRegisterDTO);

        PontoColeta pontoColeta = pontoColetaService.find(pontoColetaEnderecoRegisterDTO.idPontoColeta());

        Endereco endereco = enderecoService.find(pontoColetaEnderecoRegisterDTO.idEndereco());

        pontoColetaEndereco.setPontoColeta(pontoColeta);

        pontoColetaEndereco.setEndereco(endereco);

        return destinoResiduoPontoColetaRepository.save(pontoColetaEndereco);
    }

    public Page<PontoColetaEnderecoDetailedDTO> list(Pageable pagination) {
        return destinoResiduoPontoColetaRepository.findAll(pagination).map(PontoColetaEnderecoDetailedDTO::new);
    }

    public PontoColetaEnderecoDetailedDTO get(Long id) {
        PontoColetaEndereco pontoColetaEndereco = destinoResiduoPontoColetaRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Ponto Coleta Endereco com o ID: {"+ id +"} Não encontrado na base de dados."));

        return new PontoColetaEnderecoDetailedDTO(pontoColetaEndereco);
    }

    public void delete(Long id) {
        destinoResiduoPontoColetaRepository.deleteById(id);
    }


}
