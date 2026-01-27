package amsw.tesisHub.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DTOUniversidad {
    private Long id;
    private String nombre;
    private LocalDateTime fechaHBaja;
}
