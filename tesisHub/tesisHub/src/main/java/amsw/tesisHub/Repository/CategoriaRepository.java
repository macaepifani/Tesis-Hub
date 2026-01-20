package amsw.tesisHub.Repository;

import amsw.tesisHub.Domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
