import java.time.LocalDate;

public class Time {
    String discipline;
    double time;
    LocalDate dateSet;
    boolean isOfficial;
    String meetName;

    // constructor for when it's on unofficial time
    Time (String discipline, double time, LocalDate dateSet, boolean isOfficial){
        this.discipline=discipline;
        this.time=time;
        this.dateSet=dateSet;
        this.isOfficial=isOfficial;
    }

    // constructor if the time is official, and therefore also has a meet name
    Time (String discipline, double time, LocalDate dateSet, boolean isOfficial, String meetName){
        this.discipline=discipline;
        this.time=time;
        this.dateSet=dateSet;
        this.isOfficial=isOfficial;
        this.meetName=meetName;
    }

}
