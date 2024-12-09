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

    public static Gender fromString(String prettyName){
        for (Gender gender: values()) {
            if (gender.prettyName.equalsIgnoreCase(prettyName)){
                return gender;
            }
        }
        return null;
    }
}