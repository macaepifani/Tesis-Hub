package amsw.tesisHub.DTO;

import amsw.tesisHub.Domain.Categoria;
import amsw.tesisHub.Domain.Imagen;
import amsw.tesisHub.Domain.Universidad;
import amsw.tesisHub.Domain.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class DTOProyectoCompleto{
    private Long id;
    private String nombre;
    private String descripcion;
    private String infoContacto;
    private LocalDate fechaRealizacion;
    private Universidad universidad;
    private List<Long> categoriasID;
    private List<Long> usuariosParticipantes;
    private List<Imagen> imagenes;

}
