package ru.arkhipov.MySecondTestAppSpringBoot.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorMessages {

    EMPTY(""),
    VALIDATION("Ошибка валидаций"),
    UNSUPPORTED("Произошла непредвиденная ошибка"),
    UNKNOWN("Неподдерживаемая ошибка");


    private final String description;

    ErrorMessages(String description) {
        this.description = description;
    }

    @JsonValue
    public String getName() {
        return description;
    }
}
