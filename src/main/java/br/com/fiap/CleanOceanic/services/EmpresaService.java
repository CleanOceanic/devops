package br.com.fiap.CleanOceanic.services;

import br.com.fiap.CleanOceanic.controllers.dtos.empresa.EmpresaDetailedDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.empresa.EmpresaRegisterDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.empresa.EmpresaUpdateDTO;
import br.com.fiap.CleanOceanic.models.Empresa;
import br.com.fiap.CleanOceanic.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    @Autowired
    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public Empresa find (Long id){
        return empresaRepository.findOneByIdEmpresa(id);
    }

    public EmpresaDetailedDTO create(EmpresaRegisterDTO empresaRegisterDTO) {
        Empresa empresa = new Empresa(empresaRegisterDTO);
        return new EmpresaDetailedDTO(empresaRepository.save(empresa));
    }

    public Page<EmpresaDetailedDTO> list(Pageable pagination) {
        return empresaRepository.findAll(pagination).map(EmpresaDetailedDTO::new);
    }

    public EmpresaDetailedDTO get(Long id) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Empresa com o ID: {"+ id +"} Não encontrado na base de dados."));

        return new EmpresaDetailedDTO(empresa);
    }

    public EmpresaDetailedDTO update(Long id, EmpresaUpdateDTO empresaUpdateDTO) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Empresa com o ID: {"+ id +"} Não encontrado na base de dados."));

        empresa.updateInformation(empresaUpdateDTO);

        empresaRepository.save(empresa);

        return new EmpresaDetailedDTO(empresa);
    }

    public void delete(Long id) {
        empresaRepository.deleteById(id);
    }

}
