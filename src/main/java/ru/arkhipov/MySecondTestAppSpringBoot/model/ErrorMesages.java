package ru.arkhipov.MySecondTestAppSpringBoot.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorMesages {

    EMPTY(""),
    VALIDATION("Ошибка валидаций"),
    UNSUPPORTED("Произошла непредвиденная ошибка"),
    UNKNOWN("Неподдерживаемая ошибка");


    private final String description;

    ErrorMesages(String description) {
        this.description = description;
    }

    @JsonValue
    public String getName() {
        return description;
    }
}
