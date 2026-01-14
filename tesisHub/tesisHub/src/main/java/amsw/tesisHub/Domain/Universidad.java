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
@ToString
//JPA
@Entity
@Table(name = "Universidad")

public class Universidad  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUniversidad;

    @Column(name = "Nombre", nullable = false)
    private String nombreUniversidad;

}
