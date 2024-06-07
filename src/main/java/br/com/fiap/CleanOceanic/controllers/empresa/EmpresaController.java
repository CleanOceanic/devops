package br.com.fiap.CleanOceanic.controllers.empresa;

import br.com.fiap.CleanOceanic.controllers.dtos.empresa.EmpresaDetailedDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.empresa.EmpresaRegisterDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.empresa.EmpresaUpdateDTO;
import br.com.fiap.CleanOceanic.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("api/public/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    @Autowired
    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @PostMapping
    public ResponseEntity<EntityModel<EmpresaDetailedDTO>> create(@RequestBody @Valid EmpresaRegisterDTO empresaRegisterDTO,
                                                                  UriComponentsBuilder uriBuilder) {
        EmpresaDetailedDTO empresaDetailedDTO = empresaService.create(empresaRegisterDTO);

        EntityModel<EmpresaDetailedDTO> empresaModel = EntityModel.of(empresaDetailedDTO);

        Link selfLink = linkTo(methodOn(EmpresaController.class).find(empresaDetailedDTO.id())).withSelfRel();
        Link empresaLink = linkTo(methodOn(EmpresaController.class).list(Pageable.unpaged())).withRel("all-empresas");
        empresaModel.add(selfLink, empresaLink);

        URI location = uriBuilder.path("/api/public/empresas/{id}").buildAndExpand(empresaDetailedDTO.id()).toUri();
        return ResponseEntity.created(location).body(empresaModel);
    }

    @GetMapping
    public ResponseEntity<Page<EntityModel<EmpresaDetailedDTO>>> list(@PageableDefault(size = 10, page = 0) Pageable pagination) {
        Page<EmpresaDetailedDTO> page = empresaService.list(pagination);

        Page<EntityModel<EmpresaDetailedDTO>> empresaModels = page.map(empresaDetailedDTO -> {
            EntityModel<EmpresaDetailedDTO> empresaModel = EntityModel.of(empresaDetailedDTO);
            Link selfLink = WebMvcLinkBuilder.linkTo(methodOn(EmpresaController.class).find(empresaDetailedDTO.id())).withSelfRel();
            empresaModel.add(selfLink);
            return empresaModel;
        });

        return ResponseEntity.ok(empresaModels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<EmpresaDetailedDTO>> find(@PathVariable("id") Long id) {
        EmpresaDetailedDTO empresaDetailedDTO = empresaService.get(id);

        EntityModel<EmpresaDetailedDTO> empresaModel = EntityModel.of(empresaDetailedDTO);
        Link selfLink = linkTo(methodOn(EmpresaController.class).find(id)).withSelfRel();
        empresaModel.add(selfLink);

        return ResponseEntity.ok(empresaModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<EmpresaDetailedDTO>> update(@PathVariable("id") Long id, @RequestBody @Valid EmpresaUpdateDTO empresaUpdateDTO) {
        EmpresaDetailedDTO empresaDetailedDTO = empresaService.update(id, empresaUpdateDTO);

        EntityModel<EmpresaDetailedDTO> empresaModel = EntityModel.of(empresaDetailedDTO);
        Link selfLink = linkTo(methodOn(EmpresaController.class).find(id)).withSelfRel();
        empresaModel.add(selfLink);

        return ResponseEntity.ok(empresaModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        empresaService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
