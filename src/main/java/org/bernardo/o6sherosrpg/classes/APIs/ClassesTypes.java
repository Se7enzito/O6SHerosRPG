package org.bernardo.o6sherosrpg.classes.APIs;

public enum ClassesTypes {

    ARCO("Arco"),
    ESCUDO("Escudo"),
    ESPADA("Espada"),
    LANCA("Lança"),
    ARQUEIRO("Arqueiro"),
    ESPADACHIM("Espadachim"),
    GUERREIRO("Guerreiro"),
    MAGO("Mago");

    private final String displayName;

    ClassesTypes(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

}
