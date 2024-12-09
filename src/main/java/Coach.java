import java.util.ArrayList;

public class Coach {
    public int id;
    static int numOfCoaches = 0;
    public String name;
    public String arrayName;
    ArrayList<Member> team;
    ArrayList<Member> assaginedMembers;

    public Coach(String name, String arrayName, ArrayList<Member> team){
        numOfCoaches ++;
        this.id=numOfCoaches;
        this.name=name;
        this.arrayName = arrayName;
        this.team = team;
    }

    public Coach(String name, String arrayName, ArrayList<Member> team, ArrayList<Member> assaginedMembers){
        numOfCoaches ++;
        this.id=numOfCoaches;
        this.name=name;
        this.arrayName = arrayName;
        this.team = team;
        this.assaginedMembers = assaginedMembers;
    }

    Coach(){}

    public String toString(){
        return  name;
    }

}
