package br.com.fiap.CleanOceanic.controllers.pontoColeta;

import br.com.fiap.CleanOceanic.controllers.dtos.pontoColeta.PontoColetaDetailedDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.pontoColeta.PontoColetaRegisterDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.pontoColeta.PontoColetaUpdateDTO;
import br.com.fiap.CleanOceanic.services.PontoColetaService;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("api/public/pontoColetas")
public class PontoColetaController {

    private final PontoColetaService pontoColetaService;


    @Autowired
    public PontoColetaController(PontoColetaService pontoColetaService) {
        this.pontoColetaService = pontoColetaService;
    }

    @PostMapping
    public ResponseEntity<EntityModel<PontoColetaDetailedDTO>> create(@RequestBody @Valid PontoColetaRegisterDTO pontoColetaRegisterDTO,
                                                                      UriComponentsBuilder uriBuilder) {
        PontoColetaDetailedDTO pontoColetaDetailedDTO = pontoColetaService.create(pontoColetaRegisterDTO);

        EntityModel<PontoColetaDetailedDTO> pontoColetaModel = EntityModel.of(pontoColetaDetailedDTO);

        Link selfLink = linkTo(methodOn(PontoColetaController.class).find(pontoColetaDetailedDTO.id())).withSelfRel();
        Link pontoColetaLink = linkTo(methodOn(PontoColetaController.class).list(Pageable.unpaged())).withRel("all-ponto-coletas");
        pontoColetaModel.add(selfLink, pontoColetaLink);

        URI location = uriBuilder.path("/api/public/pontoColetas/{id}").buildAndExpand(pontoColetaDetailedDTO.id()).toUri();
        return ResponseEntity.created(location).body(pontoColetaModel);
    }

    @GetMapping
    public ResponseEntity<Page<EntityModel<PontoColetaDetailedDTO>>> list(@PageableDefault(size = 10, page = 0) Pageable pagination) {
        Page<PontoColetaDetailedDTO> page = pontoColetaService.list(pagination);

        Page<EntityModel<PontoColetaDetailedDTO>> pontoColetaModels = page.map(pontoColetaDetailedDTO -> {
            EntityModel<PontoColetaDetailedDTO> pontoColetaModel = EntityModel.of(pontoColetaDetailedDTO);
            Link selfLink = linkTo(methodOn(PontoColetaController.class).find(pontoColetaDetailedDTO.id())).withSelfRel();
            pontoColetaModel.add(selfLink);
            return pontoColetaModel;
        });

        return ResponseEntity.ok(pontoColetaModels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<PontoColetaDetailedDTO>> find(@PathVariable("id") Long id) {
        PontoColetaDetailedDTO pontoColetaDetailedDTO = pontoColetaService.get(id);

        EntityModel<PontoColetaDetailedDTO> pontoColetaModel = EntityModel.of(pontoColetaDetailedDTO);
        Link selfLink = linkTo(methodOn(PontoColetaController.class).find(id)).withSelfRel();
        pontoColetaModel.add(selfLink);

        return ResponseEntity.ok(pontoColetaModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<PontoColetaDetailedDTO>> update(@PathVariable("id") Long id, @RequestBody @Valid PontoColetaUpdateDTO pontoColetaUpdateDTO) {
        PontoColetaDetailedDTO pontoColetaDetailedDTO = pontoColetaService.update(id, pontoColetaUpdateDTO);

        EntityModel<PontoColetaDetailedDTO> pontoColetaModel = EntityModel.of(pontoColetaDetailedDTO);
        Link selfLink = linkTo(methodOn(PontoColetaController.class).find(id)).withSelfRel();
        pontoColetaModel.add(selfLink);

        return ResponseEntity.ok(pontoColetaModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        pontoColetaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
