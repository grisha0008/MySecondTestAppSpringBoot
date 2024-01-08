package ru.arkhipov.MySecondTestAppSpringBoot.model;

public enum Systems {
    ERP("Enterprise Resource Planning"),
    CRM("Customer Relationship Management"),
    WMS("Warehouse Management System");

    public final String systems;

    Systems(String systems) {
        this.systems = systems;
    }
}
