package br.com.fiap.CleanOceanic.controllers.pontoColetaEndereco;

import br.com.fiap.CleanOceanic.controllers.dtos.pontoColetaEndereco.PontoColetaEnderecoDetailedDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.pontoColetaEndereco.PontoColetaEnderecoRegisterDTO;
import br.com.fiap.CleanOceanic.models.PontoColetaEndereco;
import br.com.fiap.CleanOceanic.services.PontoColetaEnderecoService;
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
@RequestMapping("/api/public/pontoColetaEnderecos")
public class PontoColetaEnderecoController {

    private final PontoColetaEnderecoService pontoColetaEnderecoService;

    @Autowired
    public PontoColetaEnderecoController(PontoColetaEnderecoService pontoColetaEnderecoService) {
        this.pontoColetaEnderecoService = pontoColetaEnderecoService;
    }

    @PostMapping
    public ResponseEntity<EntityModel<PontoColetaEndereco>> create(@RequestBody @Valid PontoColetaEnderecoRegisterDTO pontoColetaEnderecoRegisterDTO,
                                                                   UriComponentsBuilder uriBuilder) {
        PontoColetaEndereco pontoColetaEndereco = pontoColetaEnderecoService.create(pontoColetaEnderecoRegisterDTO);

        EntityModel<PontoColetaEndereco> pontoColetaEnderecoModel = EntityModel.of(pontoColetaEndereco);

        Link selfLink = linkTo(methodOn(PontoColetaEnderecoController.class).find(pontoColetaEndereco.getIdPontoColetaEndereco())).withSelfRel();
        Link pontoColetaEnderecoLink = linkTo(methodOn(PontoColetaEnderecoController.class).list(Pageable.unpaged())).withRel("all-ponto-coleta-endereco");
        pontoColetaEnderecoModel.add(selfLink, pontoColetaEnderecoLink);

        URI location = uriBuilder.path("/api/public/pontoColetaEnderecos/{id}").buildAndExpand(pontoColetaEndereco.getIdPontoColetaEndereco()).toUri();
        return ResponseEntity.created(location).body(pontoColetaEnderecoModel);
    }

    @GetMapping
    public ResponseEntity<Page<EntityModel<PontoColetaEnderecoDetailedDTO>>> list(@PageableDefault(size = 10, page = 0) Pageable pagination) {
        Page<PontoColetaEnderecoDetailedDTO> page = pontoColetaEnderecoService.list(pagination);

        Page<EntityModel<PontoColetaEnderecoDetailedDTO>> pontoColetaEnderecoModels = page.map(pontoColetaEnderecoDetailedDTO -> {
            EntityModel<PontoColetaEnderecoDetailedDTO> pontoColetaEnderecoModel = EntityModel.of(pontoColetaEnderecoDetailedDTO);
            Link selfLink = linkTo(methodOn(PontoColetaEnderecoController.class).find(pontoColetaEnderecoDetailedDTO.idPontoColetaEndereco())).withSelfRel();
            pontoColetaEnderecoModel.add(selfLink);
            return pontoColetaEnderecoModel;
        });

        return ResponseEntity.ok(pontoColetaEnderecoModels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<PontoColetaEnderecoDetailedDTO>> find(@PathVariable("id") Long id) {
        PontoColetaEnderecoDetailedDTO pontoColetaEnderecoDetailedDTO = pontoColetaEnderecoService.get(id);

        EntityModel<PontoColetaEnderecoDetailedDTO> destinoResiduoPontoColetaModel = EntityModel.of(pontoColetaEnderecoDetailedDTO);
        Link selfLink = linkTo(methodOn(PontoColetaEnderecoController.class).find(id)).withSelfRel();
        destinoResiduoPontoColetaModel.add(selfLink);

        return ResponseEntity.ok(destinoResiduoPontoColetaModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        pontoColetaEnderecoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
