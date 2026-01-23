package amsw.tesisHub.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DTOProyectoAlta {
    private String nombreProyecto;
    private String resumenProyecto;
    private String descripcionProyecto;
    private String infoContactoProyecto;
    private LocalDate fechaRealizacion;
    private Long universidadId;
}
