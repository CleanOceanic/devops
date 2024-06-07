package br.com.fiap.CleanOceanic.controllers.endereco;

import br.com.fiap.CleanOceanic.controllers.dtos.endereco.EnderecoDetailedDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.endereco.EnderecoRegisterDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.endereco.EnderecoUpdateDTO;
import br.com.fiap.CleanOceanic.services.EnderecoService;
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
@RequestMapping("api/public/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    @Autowired
    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public ResponseEntity<EntityModel<EnderecoDetailedDTO>> create(@RequestBody @Valid EnderecoRegisterDTO enderecoRegisterDTO,
                                                                   UriComponentsBuilder uriBuilder) {
        EnderecoDetailedDTO enderecoDetailedDTO = enderecoService.create(enderecoRegisterDTO);

        EntityModel<EnderecoDetailedDTO> enderecoModel = EntityModel.of(enderecoDetailedDTO);

        Link selfLink = linkTo(methodOn(EnderecoController.class).find(enderecoDetailedDTO.id())).withSelfRel();
        Link enderecosLink = linkTo(methodOn(EnderecoController.class).list(Pageable.unpaged())).withRel("all-enderecos");
        enderecoModel.add(selfLink, enderecosLink);

        URI location = uriBuilder.path("/api/public/enderecos/{id}").buildAndExpand(enderecoDetailedDTO.id()).toUri();
        return ResponseEntity.created(location).body(enderecoModel);
    }

    @GetMapping
    public ResponseEntity<Page<EntityModel<EnderecoDetailedDTO>>> list(@PageableDefault(size = 10, page = 0) Pageable pagination) {
        Page<EnderecoDetailedDTO> page = enderecoService.list(pagination);

        Page<EntityModel<EnderecoDetailedDTO>> enderecoModels = page.map(enderecoDetailedDTO -> {
            EntityModel<EnderecoDetailedDTO> enderecoModel = EntityModel.of(enderecoDetailedDTO);
            Link selfLink = WebMvcLinkBuilder.linkTo(methodOn(EnderecoController.class).find(enderecoDetailedDTO.id())).withSelfRel();
            enderecoModel.add(selfLink);
            return enderecoModel;
        });

        return ResponseEntity.ok(enderecoModels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<EnderecoDetailedDTO>> find(@PathVariable("id") Long id) {
        EnderecoDetailedDTO enderecoDetailedDTO = enderecoService.get(id);

        EntityModel<EnderecoDetailedDTO> enderecoModel = EntityModel.of(enderecoDetailedDTO);
        Link selfLink = linkTo(methodOn(EnderecoController.class).find(id)).withSelfRel();
        enderecoModel.add(selfLink);

        return ResponseEntity.ok(enderecoModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<EnderecoDetailedDTO>> update(@PathVariable("id") Long id, @RequestBody @Valid EnderecoUpdateDTO enderecoUpdateDTO) {
        EnderecoDetailedDTO enderecoDetailedDTO = enderecoService.update(id, enderecoUpdateDTO);

        EntityModel<EnderecoDetailedDTO> enderecoModel = EntityModel.of(enderecoDetailedDTO);
        Link selfLink = linkTo(methodOn(EnderecoController.class).find(id)).withSelfRel();
        enderecoModel.add(selfLink);

        return ResponseEntity.ok(enderecoModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        enderecoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
