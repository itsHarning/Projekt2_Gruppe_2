import java.util.ArrayList;

public class Coach {
    public int id;
    static int numOfCoaches = 0;
    public String name;
    public String arrayName;
    ArrayList<Member> team;

    public Coach(String name, String arrayName){
        numOfCoaches ++;
        this.id=numOfCoaches;
        this.name=name;
        this.arrayName = arrayName;
        team = new ArrayList<>();
    }

    public String toString(){
        return  name;
    }
    public ArrayList<Member> getTeam() {
        return team;
    }

}
