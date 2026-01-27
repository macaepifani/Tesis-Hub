package amsw.tesisHub.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
//Este DTO es el que llega desde el front, solo estos campos son necesarios porque el ID se autogenera.
public class DTOCategoriaRequest {
    private String nombre;
    private String descripcion;

}
