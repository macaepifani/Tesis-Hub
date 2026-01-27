package amsw.tesisHub.Controller;

import amsw.tesisHub.DTO.DTOCategoria;
import amsw.tesisHub.DTO.DTOCategoriaRequest;
import amsw.tesisHub.Service.CUAbmCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    CUAbmCategoria abmCategoria;

    @GetMapping("")
    public ResponseEntity<List<DTOCategoria>> listarCategoriasVigentes() {
        List<DTOCategoria> dtoCategorias = abmCategoria.obtenerCategorias();
        return ResponseEntity.ok(dtoCategorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOCategoria> obtenerCategoria(@PathVariable Long id) {
        DTOCategoria dtoCategoria = abmCategoria.obtenerCategoriaPorID(id);
        return ResponseEntity.ok(dtoCategoria);
    }

    @PostMapping("")
    public ResponseEntity<DTOCategoria> guardarCategoria(@RequestBody DTOCategoriaRequest request) {
        DTOCategoria dtoCategoria = abmCategoria.crearCategoria(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoCategoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DTOCategoria> actualizarCategoria(@PathVariable Long id,
                                                            @RequestBody DTOCategoriaRequest request) {
        DTOCategoria dtoCategoria = abmCategoria.actualizarCategoria(id, request);
        return ResponseEntity.ok(dtoCategoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> bajaCategoria(@PathVariable Long id) {
        abmCategoria.bajaCategoria(id);
        return ResponseEntity.noContent().build(); // 204
    }
}

