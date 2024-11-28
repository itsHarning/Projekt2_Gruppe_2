import java.util.ArrayList;

public class Coach {
    int id;
    String name;
    String arrayName;
    ArrayList team;

    ArrayList<Team> teams;
    public Coach(int id,String name,String arrayName ,ArrayList team){
        this.id=id;
        this.name=name;
        this.arrayName = arrayName;
        this.team = team;
    }

    public String toString(){
        return name+" "+arrayName+" Hold"+team;
    }

}
