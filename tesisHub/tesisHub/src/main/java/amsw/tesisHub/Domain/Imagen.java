package amsw.tesisHub.Domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

//Lombok
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "proyecto")
//JPA
@Entity
@Table(
        name = "Imagen",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_imagen_proyecto_orden", columnNames = {"FK_Proyecto", "Orden"})
        },
        indexes = {
                @Index(name = "IDX_imagen_proyecto", columnList = "FK_Proyecto"),
                @Index(name = "IDX_imagen_proyecto_orden", columnList = "FK_Proyecto, Orden")
        }
)
public class Imagen implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImagen;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "FK_Proyecto", nullable = false)
    private Proyecto proyecto;

    @Column(name = "Url", nullable = false, length = 500)
    private String url;

    // Para ordenar imágenes en el front (0..9, por ejemplo)
    @Column(name = "Orden", nullable = false)
    private Integer orden;

    // "image/jpeg", "image/png" (opcional)
    @Column(name = "MimeType", length = 50)
    private String mimeType;
}
