package amsw.tesisHub.Service;

import amsw.tesisHub.DTO.DTOProyectoSimple;
import amsw.tesisHub.Domain.Proyecto;
import amsw.tesisHub.Repository.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CUVisualizarProyectos {
    @Autowired
    ProyectoRepository proyectoRepository;

    public List<DTOProyectoSimple> obtenerProyectos(){
        return proyectoRepository.findAll()
                .stream()
                .map(this::toDTOProyectoSimple)
                .toList();
    }

    private DTOProyectoSimple toDTOProyectoSimple(Proyecto p){
        return new DTOProyectoSimple(
                p.getId(),
                p.getNombreProyecto(),
                p.getResumenProyecto()
        );
    }
}
