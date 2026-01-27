package amsw.tesisHub.Service;

import amsw.tesisHub.DTO.DTOUniversidad;
import amsw.tesisHub.DTO.DTOUniversidadRequest;
import amsw.tesisHub.Domain.Universidad;
import amsw.tesisHub.Exception.BadRequestException;
import amsw.tesisHub.Exception.ConflictException;
import amsw.tesisHub.Exception.NotFoundException;
import amsw.tesisHub.Repository.UniversidadRepository;
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
public class CUAbmUniversidad {

    @Autowired
    UniversidadRepository universidadRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public List<DTOUniversidad> obtenerUniversidades() {
        try {
            List<Universidad> universidades = universidadRepository.findUniversidadesByVigencia();
            List<DTOUniversidad> dtoUniversidades = new ArrayList<>();
            for (Universidad u : universidades) {
                DTOUniversidad dto = new DTOUniversidad();
                dto.setId(u.getIdUniversidad());
                dto.setNombre(u.getNombreUniversidad());
                dto.setFechaHBaja(u.getFechaHBajaUniversidad());
                dtoUniversidades.add(dto);
            }
            return dtoUniversidades;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las universidades: " + e.getMessage(), e);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    public DTOUniversidad obtenerUniversidadPorID(Long id) {
        try {
            Optional<Universidad> universidadOptional = universidadRepository.findUniversidadByIdAndVigencia(id);
            if (universidadOptional.isEmpty()) {
                throw new NotFoundException("No existe una universidad con ese ID o existe pero está dada de baja.");
            }
            Universidad u = universidadOptional.get();
            DTOUniversidad dto = new DTOUniversidad();
            dto.setId(u.getIdUniversidad());
            dto.setNombre(u.getNombreUniversidad());
            dto.setFechaHBaja(u.getFechaHBajaUniversidad());
            return dto;
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener universidad por ID: " + e.getMessage(), e);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    public DTOUniversidad crearUniversidad(DTOUniversidadRequest request) {
        try {
            if (request == null) throw new BadRequestException("El request no puede ser null.");
            if (request.getNombre() == null || request.getNombre().trim().isEmpty())
                throw new BadRequestException("El nombre no puede estar vacío.");
            if (universidadRepository.findUniversidadByNombre(request.getNombre()).isPresent()) {
                throw new ConflictException("Ya existe una universidad con ese nombre.");
            }
            Universidad nueva = Universidad.builder()
                    .nombreUniversidad(request.getNombre())
                    .fechaHBajaUniversidad(null)
                    .build();
            Universidad guardada = universidadRepository.save(nueva);
            DTOUniversidad dto = new DTOUniversidad();
            dto.setId(guardada.getIdUniversidad());
            dto.setNombre(guardada.getNombreUniversidad());
            dto.setFechaHBaja(guardada.getFechaHBajaUniversidad());
            return dto;
        } catch (BadRequestException | ConflictException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al crear la universidad: " + e.getMessage(), e);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    public DTOUniversidad actualizarUniversidad(Long id, DTOUniversidadRequest request) {
        try {
            if (request == null) throw new BadRequestException("El request no puede ser null.");
            if (request.getNombre() == null || request.getNombre().trim().isEmpty())
                throw new BadRequestException("El nombre no puede estar vacío.");
            Optional<Universidad> universidadOptional = universidadRepository.findUniversidadByIdAndVigencia(id);
            if (universidadOptional.isEmpty()) {
                throw new NotFoundException("No existe una universidad con ese ID o existe pero está dada de baja.");
            }
            Universidad u = universidadOptional.get();
            u.setNombreUniversidad(request.getNombre());
            Universidad actualizada = universidadRepository.save(u);
            DTOUniversidad dto = new DTOUniversidad();
            dto.setId(actualizada.getIdUniversidad());
            dto.setNombre(actualizada.getNombreUniversidad());
            dto.setFechaHBaja(actualizada.getFechaHBajaUniversidad());
            return dto;
        } catch (BadRequestException | NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la universidad: " + e.getMessage(), e);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    public boolean bajaUniversidad(Long id) {
        try {
            Optional<Universidad> universidadOptional = universidadRepository.findUniversidadByIdAndVigencia(id);
            if (universidadOptional.isEmpty()) {
                throw new NotFoundException("No existe una universidad con ese ID o existe pero está dada de baja.");
            }
            Universidad u = universidadOptional.get();
            u.setFechaHBajaUniversidad(LocalDateTime.now());
            universidadRepository.save(u);
            return true;
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al dar de baja la universidad: " + e.getMessage(), e);
        }
    }
}

