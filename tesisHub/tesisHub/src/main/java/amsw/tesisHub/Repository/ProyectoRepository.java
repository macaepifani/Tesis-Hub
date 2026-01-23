package amsw.tesisHub.Repository;

import amsw.tesisHub.Domain.Proyecto;
import amsw.tesisHub.Enums.EstadoProyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
    @Query("SELECT p FROM Proyecto p WHERE p.estadoProyecto = :estado")
    List<Proyecto> findByEstadoProyecto(@Param("estado") EstadoProyecto estado);

}
