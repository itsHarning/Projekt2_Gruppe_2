import java.util.ArrayList;

public class Coach {
    public int id;
    static int numOfCoaches = 0;
    public String name;
    public String arrayName;
    ArrayList<Member> team;

    ArrayList<Team> teams;
    public Coach(String name, String arrayName, ArrayList<Member> team){
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
