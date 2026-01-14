package amsw.tesisHub.Domain;

import amsw.tesisHub.Enums.EstadoProyecto;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//Lombok
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"universidad", "categorias", "imagenes", "meGustas", "usuariosParticipantes" })
//JPA
@Entity
@Table(name = "Proyecto")
public class Proyecto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nombre", nullable = false, length = 150)
    private String nombreProyecto;

    @Column(name = "Resumen", nullable = false, length = 500)
    private String resumenProyecto;

    @Lob //Para mapear campos de texto grandes
    @Column(name = "Descripcion", nullable = false)
    private String descripcionProyecto;

    @Column(name = "Contacto", nullable = false, length = 200)
    private String infoContactoProyecto;

    @Column(name = "FechaRealizacion", nullable = false)
    private LocalDate fechaRealizacion;

    @Column(name = "Estado", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private EstadoProyecto estadoProyecto;

    //Podría producir errores en los DTO, atentis
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_Universidad", nullable = false)
    private Universidad universidad;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Proyecto_Categoria",
            joinColumns = @JoinColumn(name = "ProyectoID"),
            inverseJoinColumns = @JoinColumn(name = "CategoriaID")
    )
    @Builder.Default
    private List<Categoria> categorias = new ArrayList<Categoria>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Proyecto_Usuario",
            joinColumns = @JoinColumn(name = "ProyectoID"),
            inverseJoinColumns = @JoinColumn(name = "UsuarioID")
    )
    @Builder.Default
    private List<Usuario> usuariosParticipantes = new ArrayList<Usuario>();

    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Imagen> imagenes = new ArrayList<Imagen>();

    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<MeGusta> meGustas = new ArrayList<MeGusta>();

    //Métodos helpers: mantienen ambos lados sincronizados porque las relaciones son bidireccionales
    public void agregarImagen(Imagen img) {
        imagenes.add(img);
        img.setProyecto(this);
    }

    public void quitarImagen(Imagen img) {
        imagenes.remove(img);
        img.setProyecto(null);
    }

    public void agregarMeGusta(MeGusta mg) {
        meGustas.add(mg);
        mg.setProyecto(this);
    }

    public void quitarMeGusta(MeGusta mg) {
        meGustas.remove(mg);
        mg.setProyecto(null);
    }

}
