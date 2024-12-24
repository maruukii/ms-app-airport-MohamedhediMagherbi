package com.mohamedhedimagherbi.passengerservice.entities;

public enum Ticket_Types {
    ECONOMY("Economy class ticket"),
    BUSINESS("Business class ticket"),
    FIRST_CLASS("First class ticket"),
    PREMIUM_ECONOMY("Premium economy class ticket");

    private final String description;

    Ticket_Types(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
