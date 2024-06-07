package br.com.fiap.CleanOceanic.controllers.empresaEndereco;

import br.com.fiap.CleanOceanic.controllers.dtos.empresaEndereco.EmpresaEnderecoDetailedDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.empresaEndereco.EmpresaEnderecoRegisterDTO;
import br.com.fiap.CleanOceanic.models.EmpresaEndereco;
import br.com.fiap.CleanOceanic.services.EmpresaEnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/public/empresaEnderecos")
public class EmpresaEnderecoController {

    private final EmpresaEnderecoService empresaEnderecoService;

    @Autowired
    public EmpresaEnderecoController(EmpresaEnderecoService empresaEnderecoService) {
        this.empresaEnderecoService = empresaEnderecoService;
    }

    @PostMapping
    public ResponseEntity<EntityModel<EmpresaEndereco>> create(@RequestBody @Valid EmpresaEnderecoRegisterDTO empresaEnderecoRegisterDTO,
                                                               UriComponentsBuilder uriBuilder) {
        EmpresaEndereco empresaEndereco = empresaEnderecoService.create(empresaEnderecoRegisterDTO);

        EntityModel<EmpresaEndereco> empresaEnderecoModel = EntityModel.of(empresaEndereco);

        Link selfLink = linkTo(methodOn(EmpresaEnderecoController.class).find(empresaEndereco.getIdEmpresaEndereco())).withSelfRel();
        Link empresaEnderecoLink = linkTo(methodOn(EmpresaEnderecoController.class).list(Pageable.unpaged())).withRel("all-empresas-enderecos");
        empresaEnderecoModel.add(selfLink, empresaEnderecoLink);

        URI location = uriBuilder.path("/api/public/empresaEnderecos/{id}").buildAndExpand(empresaEndereco.getIdEmpresaEndereco()).toUri();
        return ResponseEntity.created(location).body(empresaEnderecoModel);
    }

    @GetMapping
    public ResponseEntity<Page<EntityModel<EmpresaEnderecoDetailedDTO>>> list(@PageableDefault(size = 10, page = 0) Pageable pagination) {
        Page<EmpresaEnderecoDetailedDTO> page = empresaEnderecoService.list(pagination);

        Page<EntityModel<EmpresaEnderecoDetailedDTO>> empresaEnderecoModels = page.map(empresaEnderecoDetailedDTO -> {
            EntityModel<EmpresaEnderecoDetailedDTO> empresaEnderecoModel = EntityModel.of(empresaEnderecoDetailedDTO);
            Link selfLink = linkTo(methodOn(EmpresaEnderecoController.class).find(empresaEnderecoDetailedDTO.idEmpresaEndereco())).withSelfRel();
            empresaEnderecoModel.add(selfLink);
            return empresaEnderecoModel;
        });

        return ResponseEntity.ok(empresaEnderecoModels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<EmpresaEnderecoDetailedDTO>> find(@PathVariable("id") Long id) {
        EmpresaEnderecoDetailedDTO empresaEnderecoDetailedDTO = empresaEnderecoService.get(id);

        EntityModel<EmpresaEnderecoDetailedDTO> empresaEnderecoModel = EntityModel.of(empresaEnderecoDetailedDTO);
        Link selfLink = linkTo(methodOn(EmpresaEnderecoController.class).find(id)).withSelfRel();
        empresaEnderecoModel.add(selfLink);

        return ResponseEntity.ok(empresaEnderecoModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        empresaEnderecoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
