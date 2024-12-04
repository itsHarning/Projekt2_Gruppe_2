import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Duration;
import java.time.LocalDate;

public class TimeHolder {
    @JsonProperty("discipline")
    public Discipline discipline;
    @JsonProperty("distance")
    public int distance;
    @JsonProperty("time")
    public Duration time;
    @JsonProperty("dateSet")
    public LocalDate dateSet;
    @JsonProperty("isOfficial")
    public boolean isOfficial;
    @JsonProperty("meetName")
    public String meetName;

    // constructor for when it's on unofficial time
    TimeHolder(Discipline discipline, int distance, Duration time, LocalDate dateSet, boolean isOfficial){
        this.discipline=discipline;
        this.distance=distance;
        this.time=time;
        this.dateSet=dateSet;
        this.isOfficial=isOfficial;
    }

    // constructor if the time is official, and therefore also has a meet name
    TimeHolder(Discipline discipline, int distance, Duration time, LocalDate dateSet, boolean isOfficial, String meetName){
        this.discipline=discipline;
        this.distance=distance;
        this.time=time;
        this.dateSet=dateSet;
        this.isOfficial=isOfficial;
        this.meetName=meetName;
    }

    TimeHolder(){
    }

    public String toString() {
        if (isOfficial) return "Disciplin: "+discipline+"\tTid: "+ CompetitiveMember.durationFormatter(time)+"\tDato: "+dateSet+"\tSat til stævnet " + meetName+"\n";
        else return "Disciplin: "+discipline+"\tTid: "+ CompetitiveMember.durationFormatter(time)+"\tDato: "+dateSet+"\tTiden blev sat til en træning\n";
    }
}
