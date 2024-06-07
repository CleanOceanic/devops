package br.com.fiap.CleanOceanic.controllers.usuario;

import br.com.fiap.CleanOceanic.controllers.dtos.usuario.UsuarioDetailedDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.usuario.UsuarioRegisterDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.usuario.UsuarioUpdateDTO;
import br.com.fiap.CleanOceanic.services.UsuarioService;
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
@RequestMapping("api/public/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;


    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<EntityModel<UsuarioDetailedDTO>> create(@RequestBody @Valid UsuarioRegisterDTO usuarioRegisterDTO,
                                                                 UriComponentsBuilder uriBuilder) {
        UsuarioDetailedDTO usuarioDetailedDTO = usuarioService.create(usuarioRegisterDTO);

        EntityModel<UsuarioDetailedDTO> usuarioModel = EntityModel.of(usuarioDetailedDTO);

        Link selfLink = linkTo(methodOn(UsuarioController.class).find(usuarioDetailedDTO.id())).withSelfRel();
        Link doctorsLink = linkTo(methodOn(UsuarioController.class).list(Pageable.unpaged())).withRel("all-usuarios");
        usuarioModel.add(selfLink, doctorsLink);

        URI location = uriBuilder.path("/api/public/usuarios/{id}").buildAndExpand(usuarioDetailedDTO.id()).toUri();
        return ResponseEntity.created(location).body(usuarioModel);
    }

    @GetMapping
    public ResponseEntity<Page<EntityModel<UsuarioDetailedDTO>>> list(@PageableDefault(size = 10, page = 0) Pageable pagination) {
        Page<UsuarioDetailedDTO> page = usuarioService.list(pagination);

        Page<EntityModel<UsuarioDetailedDTO>> usuarioModels = page.map(usuarioDetailedDTO -> {
            EntityModel<UsuarioDetailedDTO> usuarioModel = EntityModel.of(usuarioDetailedDTO);
            Link selfLink = linkTo(methodOn(UsuarioController.class).find(usuarioDetailedDTO.id())).withSelfRel();
            usuarioModel.add(selfLink);
            return usuarioModel;
        });

        return ResponseEntity.ok(usuarioModels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<UsuarioDetailedDTO>> find(@PathVariable("id") Long id) {
        UsuarioDetailedDTO usuarioDetailedDTO = usuarioService.get(id);

        EntityModel<UsuarioDetailedDTO> usuarioModel = EntityModel.of(usuarioDetailedDTO);
        Link selfLink = linkTo(methodOn(UsuarioController.class).find(id)).withSelfRel();
        usuarioModel.add(selfLink);

        return ResponseEntity.ok(usuarioModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<UsuarioDetailedDTO>> update(@PathVariable("id") Long id, @RequestBody @Valid UsuarioUpdateDTO usuarioUpdateDTO) {
        UsuarioDetailedDTO usuarioDetailedDTO = usuarioService.update(id, usuarioUpdateDTO);

        EntityModel<UsuarioDetailedDTO> usuarioModel = EntityModel.of(usuarioDetailedDTO);
        Link selfLink = linkTo(methodOn(UsuarioController.class).find(id)).withSelfRel();
        usuarioModel.add(selfLink);

        return ResponseEntity.ok(usuarioModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
