package br.com.fiap.CleanOceanic.controllers.destinoResiduo;

import br.com.fiap.CleanOceanic.controllers.dtos.destinoResiduo.DestinoResiduoDetailedDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.destinoResiduo.DestinoResiduoRegisterDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.destinoResiduo.DestinoResiduoUpdateDTO;
import br.com.fiap.CleanOceanic.services.DestinoResiduoService;
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
@RequestMapping("api/public/destinoResiduos")
public class DestinoResiduoController {

    private final DestinoResiduoService destinoResiduoService;

    @Autowired
    public DestinoResiduoController(DestinoResiduoService destinoResiduoService) {
        this.destinoResiduoService = destinoResiduoService;
    }

    @PostMapping
    public ResponseEntity<EntityModel<DestinoResiduoDetailedDTO>> create(@RequestBody @Valid DestinoResiduoRegisterDTO destinoResiduoRegisterDTO,
                                                                         UriComponentsBuilder uriBuilder) {
        DestinoResiduoDetailedDTO destinoResiduoDetailedDTO = destinoResiduoService.create(destinoResiduoRegisterDTO);

        EntityModel<DestinoResiduoDetailedDTO> destinoResiduoModel = EntityModel.of(destinoResiduoDetailedDTO);

        Link selfLink = linkTo(methodOn(DestinoResiduoController.class).find(destinoResiduoDetailedDTO.id())).withSelfRel();
        Link destinoResiduoLink = linkTo(methodOn(DestinoResiduoController.class).list(Pageable.unpaged())).withRel("all-destinoResiduos");
        destinoResiduoModel.add(selfLink, destinoResiduoLink);

        URI location = uriBuilder.path("/api/public/destinoResiduos/{id}").buildAndExpand(destinoResiduoDetailedDTO.id()).toUri();
        return ResponseEntity.created(location).body(destinoResiduoModel);
    }

    @GetMapping
    public ResponseEntity<Page<EntityModel<DestinoResiduoDetailedDTO>>> list(@PageableDefault(size = 10, page = 0) Pageable pagination) {
        Page<DestinoResiduoDetailedDTO> page = destinoResiduoService.list(pagination);

        Page<EntityModel<DestinoResiduoDetailedDTO>> destinoResiduoModels = page.map(destinoResiduoDetailedDTO -> {
            EntityModel<DestinoResiduoDetailedDTO> destinoResiduoModel = EntityModel.of(destinoResiduoDetailedDTO);
            Link selfLink = WebMvcLinkBuilder.linkTo(methodOn(DestinoResiduoController.class).find(destinoResiduoDetailedDTO.id())).withSelfRel();
            destinoResiduoModel.add(selfLink);
            return destinoResiduoModel;
        });

        return ResponseEntity.ok(destinoResiduoModels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<DestinoResiduoDetailedDTO>> find(@PathVariable("id") Long id) {
        DestinoResiduoDetailedDTO destinoResiduoDetailedDTO = destinoResiduoService.get(id);

        EntityModel<DestinoResiduoDetailedDTO> destinoResiduoModel = EntityModel.of(destinoResiduoDetailedDTO);
        Link selfLink = linkTo(methodOn(DestinoResiduoController.class).find(id)).withSelfRel();
        destinoResiduoModel.add(selfLink);

        return ResponseEntity.ok(destinoResiduoModel);
    }
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<DestinoResiduoDetailedDTO>> update(@PathVariable("id") Long id, @RequestBody @Valid DestinoResiduoUpdateDTO destinoResiduoUpdateDTO) {
        DestinoResiduoDetailedDTO destinoResiduoDetailedDTO = destinoResiduoService.update(id, destinoResiduoUpdateDTO);

        EntityModel<DestinoResiduoDetailedDTO> destinoResiduoModel = EntityModel.of(destinoResiduoDetailedDTO);
        Link selfLink = linkTo(methodOn(DestinoResiduoController.class).find(id)).withSelfRel();
        destinoResiduoModel.add(selfLink);

        return ResponseEntity.ok(destinoResiduoModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        destinoResiduoService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
