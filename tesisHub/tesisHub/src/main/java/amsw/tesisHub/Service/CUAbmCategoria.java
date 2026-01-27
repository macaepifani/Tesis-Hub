package amsw.tesisHub.Service;

import amsw.tesisHub.DTO.DTOCategoria;
import amsw.tesisHub.DTO.DTOCategoriaRequest;
import amsw.tesisHub.Domain.Categoria;
import amsw.tesisHub.Exception.BadRequestException;
import amsw.tesisHub.Exception.ConflictException;
import amsw.tesisHub.Exception.NotFoundException;
import amsw.tesisHub.Repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CUAbmCategoria {

    @Autowired
    CategoriaRepository categoriaRepository;

    //Obtener todas las categorías vigentes (fecha de baja = null)
    @PreAuthorize("hasRole('ADMIN')")
    public List<DTOCategoria> obtenerCategorias() {
        try {
            List<Categoria> categorias = categoriaRepository.findCategoriasByVigencia();
            List<DTOCategoria> dtoCategorias = new ArrayList<>();
            for (Categoria categoria : categorias) {
                DTOCategoria dtoCategoria = new DTOCategoria();
                dtoCategoria.setId(categoria.getIdCategoria());
                dtoCategoria.setNombre(categoria.getNombreCategoria());
                dtoCategoria.setFechaHBaja(categoria.getFechaHBajaCategoria());
                dtoCategoria.setDescripcion(categoria.getDescripcionCategoria());
                dtoCategorias.add(dtoCategoria);
            }
            return dtoCategorias;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las categorías: " + e.getMessage(), e);
        }
    }

    //Obtener una categoría predeterminada
    @PreAuthorize("hasRole('ADMIN')")
    public DTOCategoria obtenerCategoriaPorID(Long id) {
        try {
            Optional<Categoria> categoriaOptional = categoriaRepository.findCategoriaByIdAndVigencia(id);
            if (categoriaOptional.isEmpty()) {
                throw new NotFoundException("No existe una categoría con ese ID o existe pero está dada de baja.");
            }
            Categoria categoria = categoriaOptional.get();
            DTOCategoria dtoCategoria = new DTOCategoria();
            dtoCategoria.setId(categoria.getIdCategoria());
            dtoCategoria.setNombre(categoria.getNombreCategoria());
            dtoCategoria.setFechaHBaja(categoria.getFechaHBajaCategoria());
            dtoCategoria.setDescripcion(categoria.getDescripcionCategoria());
            return dtoCategoria;
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener categoría por ID: " + e.getMessage(), e);
        }
    }

    //Guardar categorías
    @PreAuthorize("hasRole('ADMIN')")
    public DTOCategoria crearCategoria(DTOCategoriaRequest request) {
        try {
            if (request == null) {
                throw new BadRequestException("El request no puede ser null.");
            }
            if (request.getNombre() == null || request.getNombre().trim().isEmpty()) {
                throw new BadRequestException("El nombre no puede estar vacío.");
            }
            if (request.getDescripcion() == null || request.getDescripcion().trim().isEmpty()) {
                throw new BadRequestException("La descripción no puede estar vacía.");
            }
            Optional<Categoria> categoriaEncontrada = categoriaRepository.findCategoriaByNombre(request.getNombre());
            if (categoriaEncontrada.isPresent()) {
                throw new ConflictException("Ya existe una categoría con ese nombre.");
            }
            //Desarmamos el DTO
            Categoria categoriaNueva = Categoria.builder()
                    .nombreCategoria(request.getNombre())
                    .descripcionCategoria(request.getDescripcion())
                    .fechaHBajaCategoria(null)
                    .build();
            Categoria categoriaGuardada = categoriaRepository.save(categoriaNueva);
            //Armamos un nuevo DTO
            DTOCategoria dtoCategoria = new DTOCategoria();
            dtoCategoria.setId(categoriaGuardada.getIdCategoria());
            dtoCategoria.setNombre(categoriaGuardada.getNombreCategoria());
            dtoCategoria.setDescripcion(categoriaGuardada.getDescripcionCategoria());
            dtoCategoria.setFechaHBaja(categoriaGuardada.getFechaHBajaCategoria());
            return dtoCategoria;
        } catch (BadRequestException | ConflictException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al crear la categoría: " + e.getMessage(), e);
        }
    }

    //Actualizar categoría
    @PreAuthorize("hasRole('ADMIN')")
    public DTOCategoria actualizarCategoria(Long id, DTOCategoriaRequest request) {
        try {
            if (request == null) {
                throw new BadRequestException("El request no puede ser null.");
            }
            if (request.getNombre() == null || request.getNombre().trim().isEmpty()) {
                throw new BadRequestException("El nombre no puede estar vacío.");
            }
            if (request.getDescripcion() == null || request.getDescripcion().trim().isEmpty()) {
                throw new BadRequestException("La descripción no puede estar vacía.");
            }
            Optional<Categoria> categoriaOptional = categoriaRepository.findCategoriaByIdAndVigencia(id);
            if (categoriaOptional.isEmpty()) {
                throw new NotFoundException("No existe una categoría con ese ID o existe pero está dada de baja.");
            }
            //Guardamos la categoría actualizada
            Categoria categoria = categoriaOptional.get();
            categoria.setNombreCategoria(request.getNombre());
            categoria.setDescripcionCategoria(request.getDescripcion());
            Categoria categoriaActualizada = categoriaRepository.save(categoria);
            //Creamos el DTO
            DTOCategoria dtoCategoria = new DTOCategoria();
            dtoCategoria.setId(categoriaActualizada.getIdCategoria());
            dtoCategoria.setNombre(categoriaActualizada.getNombreCategoria());
            dtoCategoria.setDescripcion(categoriaActualizada.getDescripcionCategoria());
            dtoCategoria.setFechaHBaja(categoriaActualizada.getFechaHBajaCategoria());
            return dtoCategoria;
        } catch (BadRequestException | NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la categoría: " + e.getMessage(), e);
        }
    }

    //Eliminar una categoría (baja lógica)
    @PreAuthorize("hasRole('ADMIN')")
    public boolean bajaCategoria(Long id) {
        try {
            Optional<Categoria> categoriaOptional = categoriaRepository.findCategoriaByIdAndVigencia(id);
            if (categoriaOptional.isEmpty()) {
                throw new NotFoundException("No existe una categoría con ese ID o existe pero está dada de baja.");
            }
            Categoria categoriaEncontrada = categoriaOptional.get();
            categoriaEncontrada.setFechaHBajaCategoria(LocalDateTime.now());
            categoriaRepository.save(categoriaEncontrada);
            return true;
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al dar de baja la categoría: " + e.getMessage(), e);
        }
    }
}