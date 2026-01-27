package amsw.tesisHub.Repository;

import amsw.tesisHub.Domain.Universidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UniversidadRepository extends JpaRepository<Universidad, Long> {
    @Query("SELECT u FROM Universidad u WHERE u.fechaHBajaUniversidad IS NULL")
    List<Universidad> findUniversidadesByVigencia();

    @Query("SELECT u FROM Universidad u WHERE u.fechaHBajaUniversidad IS NULL AND u.idUniversidad = :id")
    Optional<Universidad> findUniversidadByIdAndVigencia(@Param("id") Long id);

    @Query("SELECT u FROM Universidad u WHERE u.nombreUniversidad = :nombre")
    Optional<Universidad> findUniversidadByNombre(@Param("nombre") String nombre);
}
