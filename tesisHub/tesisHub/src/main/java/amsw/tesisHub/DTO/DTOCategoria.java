package amsw.tesisHub.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class DTOCategoria {
    private Long id;
    private String nombre;
    private String descripcion;
    private LocalDateTime fechaHBaja;
}
