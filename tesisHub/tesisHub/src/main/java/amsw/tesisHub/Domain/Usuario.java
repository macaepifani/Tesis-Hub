package amsw.tesisHub.Domain;

import amsw.tesisHub.Enums.EstadoUsuario;
import amsw.tesisHub.Enums.RolUsuario;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(
        name = "Usuario",
        uniqueConstraints = {
                @UniqueConstraint(name="UK_usuario_email", columnNames="Email")
        }
)
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(name = "Nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "Email", nullable = false, length = 150)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "Rol", nullable = false, length = 20)
    private RolUsuario rol;

    @Enumerated(EnumType.STRING)
    @Column(name = "Estado", nullable = false, length = 20)
    private EstadoUsuario estado;
}
