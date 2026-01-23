package amsw.tesisHub.Controller;

import amsw.tesisHub.DTO.DTOProyectoCompleto;
import amsw.tesisHub.DTO.DTOProyectoSimple;
import amsw.tesisHub.Service.CUVisualizarProyectos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tesisHub")
@CrossOrigin("*")
public class ProyectoController {
    @Autowired
    CUVisualizarProyectos visualizarProyectos;

    @GetMapping("/proyectos")
    public ResponseEntity<List<DTOProyectoSimple>> listarProyectosAprobados() {
        try {
            List<DTOProyectoSimple> proyectos = visualizarProyectos.obtenerProyectosAprobados();
            return ResponseEntity.ok(proyectos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/proyectos/{id}")
    public ResponseEntity<DTOProyectoCompleto> verProyecto(@PathVariable Long id) {
        try {
            DTOProyectoCompleto proyecto = visualizarProyectos.obtenerProyectoPorID(id);
            return ResponseEntity.ok(proyecto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
