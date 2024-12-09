import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class CompetitiveMember{
    public Coach coach;
    @JsonProperty("TimeHolder")
    ArrayList<TimeHolder> personalTimes = new ArrayList<>();

    CompetitiveMember(Coach coach){
        this.coach = coach;
    }

    // used when loading everything from .json
    CompetitiveMember(){}

    public String toString(){
        return "Træner: "+coach+"\tTider: "+personalTimes;
    }

}