package amsw.tesisHub.Enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum RolUsuario {
    @JsonProperty("interesado")
    INTERESADO,
    @JsonProperty("admin")
    ADMIN,
    @JsonProperty("estudiante")
    ESTUDIANTE
}
