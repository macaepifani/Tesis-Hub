package amsw.tesisHub.Enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum EstadoUsuario {
    @JsonProperty("activo")
    ACTIVO,
    @JsonProperty("inactivo")
    INACTIVO
}
