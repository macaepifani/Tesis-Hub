package amsw.tesisHub.Repository;

import amsw.tesisHub.Domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    @Query("SELECT c FROM Categoria c WHERE c.fechaHBajaCategoria IS NULL")
    List<Categoria> findCategoriasByVigencia();

    @Query("SELECT c FROM Categoria c WHERE c.fechaHBajaCategoria IS NULL AND c.idCategoria = :id")
    Optional<Categoria> findCategoriaByIdAndVigencia(@Param("id") Long id);

    @Query("SELECT c FROM Categoria c WHERE c.nombreCategoria = :nombre")
    Optional<Categoria> findCategoriaByNombre(@Param("nombre") String nombre);

}
