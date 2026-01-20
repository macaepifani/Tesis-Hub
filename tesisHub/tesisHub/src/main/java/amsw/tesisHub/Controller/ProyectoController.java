package amsw.tesisHub.Controller;

import amsw.tesisHub.DTO.DTOProyectoSimple;
import amsw.tesisHub.Service.CUVisualizarProyectos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("tesisHub")
@CrossOrigin("*")
public class ProyectoController {
    @Autowired
    CUVisualizarProyectos visualizarProyectos;

    @GetMapping("/proyectos")
    public List<DTOProyectoSimple> listarProyectos(){
        return visualizarProyectos.obtenerProyectos();
    }
}
