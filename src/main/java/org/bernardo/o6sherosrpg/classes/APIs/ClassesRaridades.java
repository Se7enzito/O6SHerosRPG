package org.bernardo.o6sherosrpg.classes.APIs;

public enum ClassesRaridades {

    COMUM("Comum"),
    RARA("Rara"),
    EPICA("Épica"),
    LENDARIA("Lendária"),
    MITICA("Mítica"),
    MUNDIAL("Mundial");

    private final String displayName;

    ClassesRaridades(String displayName) {
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
