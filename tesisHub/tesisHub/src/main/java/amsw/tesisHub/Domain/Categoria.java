package amsw.tesisHub.Domain;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;

//Lombok
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
//JPA
@Entity
@Table(
        name="Categoria",
        uniqueConstraints = @UniqueConstraint(name="UK_categoria_nombre", columnNames="Nombre")
)

public class Categoria implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoria;

    @Column(name = "Nombre", nullable = false)
    private String nombreCategoria;

    @Column(name = "Descripcion", nullable = false)
    private String descripcionCategoria;

    @Column(name = "Fecha_Baja", nullable = true)
    private LocalDateTime fechaHBajaCategoria;

}
