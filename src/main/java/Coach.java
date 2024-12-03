import java.util.ArrayList;

public class Coach {
    int id;
    static int numOfCoaches = 0;
    String name;
    String arrayName;
    ArrayList team;

    ArrayList<Team> teams;
    public Coach(String name,String arrayName ,ArrayList team){
        numOfCoaches ++;
        this.id=numOfCoaches;
        this.name=name;
        this.arrayName = arrayName;
        this.team = team;
    }

    public String toString(){
        return  name;
    }

}
