import java.time.Duration;
import java.time.LocalDate;

public class TimeClass {
    String discipline;
    int distance;
    Duration time;
    LocalDate dateSet;
    boolean isOfficial;
    String meetName;

    // constructor for when it's on unofficial time
    TimeClass(String discipline, int distance, Duration time, LocalDate dateSet, boolean isOfficial){
        this.discipline=discipline;
        this.distance=distance;
        this.time=time;
        this.dateSet=dateSet;
        this.isOfficial=isOfficial;
    }

    // constructor if the time is official, and therefore also has a meet name
    TimeClass(String discipline, int distance, Duration time, LocalDate dateSet, boolean isOfficial, String meetName){
        this.discipline=discipline;
        this.distance=distance;
        this.time=time;
        this.dateSet=dateSet;
        this.isOfficial=isOfficial;
        this.meetName=meetName;
    }

}
