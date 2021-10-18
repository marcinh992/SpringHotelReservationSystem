package pl.overlook.springhotelreservation.domain.room;

public enum BedType {
    NONE("Brak"),
    SINGLE("Pojedyńcze"),
    DOUBLE("Podwójne"),
    KING_SIZE("Królewskie");

    private final String displayPolishName;

    BedType (String displayPolishName){
        this.displayPolishName=displayPolishName;
    }

    public String getDisplayPolishName() {
        return displayPolishName;
    }
}
