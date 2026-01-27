package amsw.tesisHub.Controller;

import amsw.tesisHub.DTO.DTOUniversidad;
import amsw.tesisHub.DTO.DTOUniversidadRequest;
import amsw.tesisHub.Service.CUAbmUniversidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/universidades")
public class UniversidadController {

    @Autowired
    CUAbmUniversidad abmUniversidad;

    @GetMapping("")
    public ResponseEntity<List<DTOUniversidad>> listarUniversidadesVigentes() {
        return ResponseEntity.ok(abmUniversidad.obtenerUniversidades());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOUniversidad> obtenerUniversidad(@PathVariable Long id) {
        return ResponseEntity.ok(abmUniversidad.obtenerUniversidadPorID(id));
    }

    @PostMapping("")
    public ResponseEntity<DTOUniversidad> guardarUniversidad(@RequestBody DTOUniversidadRequest request) {
        DTOUniversidad dto = abmUniversidad.crearUniversidad(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DTOUniversidad> actualizarUniversidad(@PathVariable Long id,
                                                                @RequestBody DTOUniversidadRequest request) {
        return ResponseEntity.ok(abmUniversidad.actualizarUniversidad(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> bajaUniversidad(@PathVariable Long id) {
        abmUniversidad.bajaUniversidad(id);
        return ResponseEntity.noContent().build();
    }
}

