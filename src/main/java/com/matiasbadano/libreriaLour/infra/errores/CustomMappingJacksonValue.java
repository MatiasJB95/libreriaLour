package com.matiasbadano.libreriaLour.infra.errores;

import org.springframework.http.converter.json.MappingJacksonValue;

public class CustomMappingJacksonValue extends MappingJacksonValue {
    public CustomMappingJacksonValue(Object value) {
        super(value);
    }
}