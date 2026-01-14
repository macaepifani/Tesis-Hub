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
        name = "MeGusta",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_megusta_usuario_proyecto", columnNames = {"FK_Usuario", "FK_Proyecto"})
        },
        indexes = {
                @Index(name = "IDX_megusta_proyecto", columnList = "FK_Proyecto"),
                @Index(name = "IDX_megusta_usuario", columnList = "FK_Usuario")
        }
)

public class MeGusta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMeGusta;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "FK_Usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="FK_Proyecto", nullable=false)
    private Proyecto proyecto;

}
