public enum Gender {
    MALE("Mand"),
    FEMALE("Kvinde"),
    OTHER("Andet"),
    ;

    private final String prettyName;

    Gender(String prettyName){
        this.prettyName=prettyName;
    }

    public String toString() {
        return prettyName;
    }
}