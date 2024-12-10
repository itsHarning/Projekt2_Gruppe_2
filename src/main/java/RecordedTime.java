import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Duration;
import java.time.LocalDate;

public class RecordedTime {
    @JsonProperty("discipline")
    public Discipline discipline;
    @JsonProperty("distance")
    public int distance;
    @JsonProperty("time")
    public Duration duration;
    @JsonProperty("dateSet")
    public LocalDate dateSet;
    @JsonProperty("isOfficial")
    public boolean isOfficial;
    @JsonProperty("meetName")
    public String meetName;

    // constructor for when it's on unofficial time
    RecordedTime(Discipline discipline, int distance, Duration duration, LocalDate dateSet, boolean isOfficial){
        this.discipline=discipline;
        this.distance=distance;
        this.duration = duration;
        this.dateSet=dateSet;
        this.isOfficial=isOfficial;
    }

    // constructor if the time is official, and therefore also has a meet name
    RecordedTime(Discipline discipline, int distance, Duration duration, LocalDate dateSet, boolean isOfficial, String meetName){
        this.discipline=discipline;
        this.distance=distance;
        this.duration = duration;
        this.dateSet=dateSet;
        this.isOfficial=isOfficial;
        this.meetName=meetName;
    }

    RecordedTime(Discipline discipline, int distance){
        this.discipline=discipline;
        this.distance=distance;
    }

    // used when loading everything from .json
    RecordedTime(){
    }

    public String toString() {
        if (isOfficial)
            return "\nDisciplin: "+distance+"m "+discipline+
                    "\tTid: "+ CompMemberHandler.durationToStringFormatter(duration)+
                    "\tDato: "+dateSet+
                    "\tSat til stævnet \"" + meetName+"\"";
        else
            return "\nDisciplin: "+distance+"m "+discipline +
                    "\tTid: "+ CompMemberHandler.durationToStringFormatter(duration)+
                    "\tDato: "+dateSet+
                    "\tTiden blev sat til en træning";
    }

    public Duration getDuration() {
        return duration;
    }
}
