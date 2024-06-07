package br.com.fiap.CleanOceanic.controllers.residuoColetado;

import br.com.fiap.CleanOceanic.controllers.dtos.residuoColetado.ResiduoColetadoDetailedDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.residuoColetado.ResiduoColetadoRegisterDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.residuoColetado.ResiduoColetadoUpdateDTO;
import br.com.fiap.CleanOceanic.services.ResiduoColetadoService;
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
@RequestMapping("api/public/residuoColetados")
public class ResiduoColetadoController {

    private final ResiduoColetadoService residuoColetadoService;

    @Autowired
    public ResiduoColetadoController(ResiduoColetadoService residuoColetadoService) {
        this.residuoColetadoService = residuoColetadoService;
    }

    @PostMapping
    public ResponseEntity<EntityModel<ResiduoColetadoDetailedDTO>> create(@RequestBody @Valid ResiduoColetadoRegisterDTO residuoColetadoRegisterDTO,
                                                               UriComponentsBuilder uriBuilder) {
        ResiduoColetadoDetailedDTO residuoColetadoDetailedDTO = residuoColetadoService.create(residuoColetadoRegisterDTO);

        EntityModel<ResiduoColetadoDetailedDTO> residuoColetadoModel = EntityModel.of(residuoColetadoDetailedDTO);

        Link selfLink = linkTo(methodOn(ResiduoColetadoController.class).find(residuoColetadoDetailedDTO.id())).withSelfRel();
        Link residuoColetadoLink = linkTo(methodOn(ResiduoColetadoController.class).list(Pageable.unpaged())).withRel("all-residuoColetados");
        residuoColetadoModel.add(selfLink, residuoColetadoLink);

        URI location = uriBuilder.path("/api/public/residuoColetados/{id}").buildAndExpand(residuoColetadoDetailedDTO.id()).toUri();
        return ResponseEntity.created(location).body(residuoColetadoModel);
    }

    @GetMapping
    public ResponseEntity<Page<EntityModel<ResiduoColetadoDetailedDTO>>> list(@PageableDefault(size = 10, page = 0) Pageable pagination) {
        Page<ResiduoColetadoDetailedDTO> page = residuoColetadoService.list(pagination);

        Page<EntityModel<ResiduoColetadoDetailedDTO>> residuoColetadoModels = page.map(residuoColetadoDetailedDTO -> {
            EntityModel<ResiduoColetadoDetailedDTO> residuoColetadoModel = EntityModel.of(residuoColetadoDetailedDTO);
            Link selfLink = WebMvcLinkBuilder.linkTo(methodOn(ResiduoColetadoController.class).find(residuoColetadoDetailedDTO.id())).withSelfRel();
            residuoColetadoModel.add(selfLink);
            return residuoColetadoModel;
        });

        return ResponseEntity.ok(residuoColetadoModels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ResiduoColetadoDetailedDTO>> find(@PathVariable("id") Long id) {
        ResiduoColetadoDetailedDTO residuoColetadoDetailedDTO = residuoColetadoService.get(id);

        EntityModel<ResiduoColetadoDetailedDTO> residuoColetadoModel = EntityModel.of(residuoColetadoDetailedDTO);
        Link selfLink = linkTo(methodOn(ResiduoColetadoController.class).find(id)).withSelfRel();
        residuoColetadoModel.add(selfLink);

        return ResponseEntity.ok(residuoColetadoModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<ResiduoColetadoDetailedDTO>> update(@PathVariable("id") Long id, @RequestBody @Valid ResiduoColetadoUpdateDTO residuoColetadoUpdateDTO) {
        ResiduoColetadoDetailedDTO residuoColetadoDetailedDTO = residuoColetadoService.update(id, residuoColetadoUpdateDTO);

        EntityModel<ResiduoColetadoDetailedDTO> residuoColetadoModel = EntityModel.of(residuoColetadoDetailedDTO);
        Link selfLink = linkTo(methodOn(ResiduoColetadoController.class).find(id)).withSelfRel();
        residuoColetadoModel.add(selfLink);

        return ResponseEntity.ok(residuoColetadoModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        residuoColetadoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
