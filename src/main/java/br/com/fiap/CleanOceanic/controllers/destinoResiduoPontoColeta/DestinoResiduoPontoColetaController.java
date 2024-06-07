package br.com.fiap.CleanOceanic.controllers.destinoResiduoPontoColeta;
import br.com.fiap.CleanOceanic.controllers.dtos.destinoResiduoPontoColeta.DestinoResiduoPontoColetaDetailedDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.destinoResiduoPontoColeta.DestinoResiduoPontoColetaRegisterDTO;
import br.com.fiap.CleanOceanic.models.DestinoResiduoPontoColeta;
import br.com.fiap.CleanOceanic.services.DestinoResiduoPontoColetaService;
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
@RequestMapping("/api/public/destinoResiduoPontoColetas")
public class DestinoResiduoPontoColetaController {

    private final DestinoResiduoPontoColetaService destinoResiduoPontoColetaService;

    @Autowired
    public DestinoResiduoPontoColetaController(DestinoResiduoPontoColetaService destinoResiduoPontoColetaService) {
        this.destinoResiduoPontoColetaService = destinoResiduoPontoColetaService;
    }

    @PostMapping
    public ResponseEntity<EntityModel<DestinoResiduoPontoColeta>> create(@RequestBody @Valid DestinoResiduoPontoColetaRegisterDTO destinoResiduoPontoColetaRegisterDTO,
                                                                         UriComponentsBuilder uriBuilder) {
        DestinoResiduoPontoColeta destinoResiduoPontoColeta = destinoResiduoPontoColetaService.create(destinoResiduoPontoColetaRegisterDTO);

        EntityModel<DestinoResiduoPontoColeta> destinoResiduoPontoColetaModel = EntityModel.of(destinoResiduoPontoColeta);

        Link selfLink = linkTo(methodOn(DestinoResiduoPontoColetaController.class).find(destinoResiduoPontoColeta.getIdDestinoResiduoPontoColeta())).withSelfRel();
        Link destinoResiduoPontoColetaLink = linkTo(methodOn(DestinoResiduoPontoColetaController.class).list(Pageable.unpaged())).withRel("all-destino-residuo-ponto-coleta");
        destinoResiduoPontoColetaModel.add(selfLink, destinoResiduoPontoColetaLink);

        URI location = uriBuilder.path("/api/public/destinoResiduoPontoColetas/{id}").buildAndExpand(destinoResiduoPontoColeta.getIdDestinoResiduoPontoColeta()).toUri();
        return ResponseEntity.created(location).body(destinoResiduoPontoColetaModel);
    }

    @GetMapping
    public ResponseEntity<Page<EntityModel<DestinoResiduoPontoColetaDetailedDTO>>> list(@PageableDefault(size = 10, page = 0) Pageable pagination) {
        Page<DestinoResiduoPontoColetaDetailedDTO> page = destinoResiduoPontoColetaService.list(pagination);

        Page<EntityModel<DestinoResiduoPontoColetaDetailedDTO>> destinoResiduoPontoColetaModels = page.map(destinoResiduoPontoColetaDetailedDTO -> {
            EntityModel<DestinoResiduoPontoColetaDetailedDTO> destinoResiduoPontoColetaModel = EntityModel.of(destinoResiduoPontoColetaDetailedDTO);
            Link selfLink = linkTo(methodOn(DestinoResiduoPontoColetaController.class).find(destinoResiduoPontoColetaDetailedDTO.idDestinoResiduoPontoColeta())).withSelfRel();
            destinoResiduoPontoColetaModel.add(selfLink);
            return destinoResiduoPontoColetaModel;
        });

        return ResponseEntity.ok(destinoResiduoPontoColetaModels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<DestinoResiduoPontoColetaDetailedDTO>> find(@PathVariable("id") Long id) {
        DestinoResiduoPontoColetaDetailedDTO destinoResiduoPontoColetaDetailedDTO = destinoResiduoPontoColetaService.get(id);

        EntityModel<DestinoResiduoPontoColetaDetailedDTO> destinoResiduoPontoColetaModel = EntityModel.of(destinoResiduoPontoColetaDetailedDTO);
        Link selfLink = linkTo(methodOn(DestinoResiduoPontoColetaController.class).find(id)).withSelfRel();
        destinoResiduoPontoColetaModel.add(selfLink);

        return ResponseEntity.ok(destinoResiduoPontoColetaModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        destinoResiduoPontoColetaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
