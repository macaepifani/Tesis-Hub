package amsw.tesisHub.Service;

import amsw.tesisHub.DTO.DTOProyectoCompleto;
import amsw.tesisHub.DTO.DTOProyectoSimple;
import amsw.tesisHub.Domain.Proyecto;
import amsw.tesisHub.Enums.EstadoProyecto;
import amsw.tesisHub.Repository.ProyectoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CUVisualizarProyectos {
    @Autowired
    ProyectoRepository proyectoRepository;

    //Buscar todos los proyectos en estado "Aprobado"
    public List<DTOProyectoSimple> obtenerProyectosAprobados() throws Exception {
        try {
            List<Proyecto> proyectos = proyectoRepository.findByEstadoProyecto(EstadoProyecto.APROBADO);
            if(proyectos.isEmpty()){
                throw new Exception("No hay proyectos aprobados disponibles.");
            }
            List<DTOProyectoSimple> dtoProyectoSimples = new ArrayList<>();
            ModelMapper modelMapper = new ModelMapper();
            for (Proyecto proyecto : proyectos) {
                dtoProyectoSimples.add(modelMapper.map(proyecto, DTOProyectoSimple.class));
            }
            return dtoProyectoSimples;
        } catch (Exception e) {
            throw new Exception("Error al obtener proyectos aprobados: " + e.getMessage());
        }
    }

    //Buscar un proyecto determinado
    public DTOProyectoCompleto obtenerProyectoPorID(Long id) throws Exception{
        try{
            Optional<Proyecto> proyectoOptional = proyectoRepository.findById(id);
            if(proyectoOptional.isEmpty() || proyectoOptional.get().getEstadoProyecto() != EstadoProyecto.APROBADO){
                throw new Exception("El proyecto no existe o no está aprobado.");
            }
            return new ModelMapper().map(proyectoOptional.get(), DTOProyectoCompleto.class);
        } catch (Exception e) {
            throw new Exception("Error al obtener proyecto por ID: " + e.getMessage());
        }
    }
}