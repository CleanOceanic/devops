package br.com.fiap.CleanOceanic.services;

import br.com.fiap.CleanOceanic.controllers.dtos.empresaEndereco.EmpresaEnderecoDetailedDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.empresaEndereco.EmpresaEnderecoRegisterDTO;
import br.com.fiap.CleanOceanic.models.Empresa;
import br.com.fiap.CleanOceanic.models.EmpresaEndereco;
import br.com.fiap.CleanOceanic.models.Endereco;
import br.com.fiap.CleanOceanic.repositories.EmpresaEnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmpresaEnderecoService {

    private final EmpresaEnderecoRepository empresaEnderecoRepository;

    private final EmpresaService empresaService;

    private final EnderecoService enderecoService;

    @Autowired
    public EmpresaEnderecoService(EmpresaEnderecoRepository empresaEnderecoRepository, EmpresaService empresaService,
                                  EnderecoService enderecoService) {
        this.empresaEnderecoRepository = empresaEnderecoRepository;
        this.empresaService = empresaService;
        this.enderecoService = enderecoService;
    }

    public EmpresaEndereco create(EmpresaEnderecoRegisterDTO empresaEnderecoRegisterDTO) {

        EmpresaEndereco empresaEndereco = new EmpresaEndereco(empresaEnderecoRegisterDTO);

        Empresa empresa = empresaService.find(empresaEnderecoRegisterDTO.idEmpresa());

        Endereco endereco = enderecoService.find(empresaEnderecoRegisterDTO.idEndereco());

        empresaEndereco.setEmpresa(empresa);

        empresaEndereco.setEndereco(endereco);

        return empresaEnderecoRepository.save(empresaEndereco);
    }

    public Page<EmpresaEnderecoDetailedDTO> list(Pageable pagination) {
        return empresaEnderecoRepository.findAll(pagination).map(EmpresaEnderecoDetailedDTO::new);
    }

    public EmpresaEnderecoDetailedDTO get(Long id) {
        EmpresaEndereco empresaEndereco = empresaEnderecoRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Empresa Endereco com o ID: {"+ id +"} Não encontrado na base de dados."));

        return new EmpresaEnderecoDetailedDTO(empresaEndereco);
    }

    public void delete(Long id) {
        empresaEnderecoRepository.deleteById(id);
    }

}
