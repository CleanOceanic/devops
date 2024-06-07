package br.com.fiap.CleanOceanic.controllers.usuarioEndereco;

import br.com.fiap.CleanOceanic.controllers.dtos.usuarioEndereco.UsuarioEnderecoDetailedDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.usuarioEndereco.UsuarioEnderecoRegisterDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.usuarioEndereco.UsuarioEnderecoUpdateDTO;
import br.com.fiap.CleanOceanic.controllers.usuario.UsuarioController;
import br.com.fiap.CleanOceanic.models.UsuarioEndereco;
import br.com.fiap.CleanOceanic.services.UsuarioEnderecoService;
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
@RequestMapping("/api/public/usuarioEnderecos")
public class UsuarioEnderecoController {

    private final UsuarioEnderecoService usuarioEnderecoService;

    @Autowired
    public UsuarioEnderecoController(UsuarioEnderecoService usuarioEnderecoService) {
        this.usuarioEnderecoService = usuarioEnderecoService;
    }

    @PostMapping
    public ResponseEntity<EntityModel<UsuarioEndereco>> create(@RequestBody @Valid UsuarioEnderecoRegisterDTO usuarioEnderecoRegisterDTO,
                                                               UriComponentsBuilder uriBuilder) {
        UsuarioEndereco usuarioEnderecoDetailedDTO = usuarioEnderecoService.create(usuarioEnderecoRegisterDTO);

        EntityModel<UsuarioEndereco> usuarioEnderecoModel = EntityModel.of(usuarioEnderecoDetailedDTO);

        Link selfLink = linkTo(methodOn(UsuarioController.class).find(usuarioEnderecoDetailedDTO.getIdUsuarioEndereco())).withSelfRel();
        Link doctorsLink = linkTo(methodOn(UsuarioController.class).list(Pageable.unpaged())).withRel("all-usuarios-enderecos");
        usuarioEnderecoModel.add(selfLink, doctorsLink);

        URI location = uriBuilder.path("/api/public/usuarioEnderecos/{id}").buildAndExpand(usuarioEnderecoDetailedDTO.getIdUsuarioEndereco()).toUri();
        return ResponseEntity.created(location).body(usuarioEnderecoModel);
    }

    @GetMapping
    public ResponseEntity<Page<EntityModel<UsuarioEnderecoDetailedDTO>>> list(@PageableDefault(size = 10, page = 0) Pageable pagination) {
        Page<UsuarioEnderecoDetailedDTO> page = usuarioEnderecoService.list(pagination);

        Page<EntityModel<UsuarioEnderecoDetailedDTO>> usuarioEnderecoModels = page.map(usuarioEnderecoDetailedDTO -> {
            EntityModel<UsuarioEnderecoDetailedDTO> usuarioEnderecoModel = EntityModel.of(usuarioEnderecoDetailedDTO);
            Link selfLink = linkTo(methodOn(UsuarioEnderecoController.class).find(usuarioEnderecoDetailedDTO.idUsuarioEndereco())).withSelfRel();
            usuarioEnderecoModel.add(selfLink);
            return usuarioEnderecoModel;
        });

        return ResponseEntity.ok(usuarioEnderecoModels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<UsuarioEnderecoDetailedDTO>> find(@PathVariable("id") Long id) {
        UsuarioEnderecoDetailedDTO usuarioEnderecoDetailedDTO = usuarioEnderecoService.get(id);

        EntityModel<UsuarioEnderecoDetailedDTO> usuarioEnderecoModel = EntityModel.of(usuarioEnderecoDetailedDTO);
        Link selfLink = linkTo(methodOn(UsuarioEnderecoController.class).find(id)).withSelfRel();
        usuarioEnderecoModel.add(selfLink);

        return ResponseEntity.ok(usuarioEnderecoModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        usuarioEnderecoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
