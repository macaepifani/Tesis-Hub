package amsw.tesisHub.Enums;


import com.fasterxml.jackson.annotation.JsonProperty;

public enum EstadoProyecto {
    @JsonProperty("en_revision")
    EN_REVISION,
    @JsonProperty("aprobado")
    APROBADO
}
