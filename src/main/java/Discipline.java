public enum Discipline {
    FREESTYLE("Freestyle"),
    BACKSTROKE("Backstroke"),
    BREASTSTROKE("Breaststroke"),
    BUTTERFLY("Butterfly"),
    MEDLEY("Medley"),
    OPEN_WATER("Open Water"),
    ;

    private final String prettyName;

    Discipline(String prettyName){
        this.prettyName = prettyName;
    }

    public String toString() {
        return prettyName;
    }
}
