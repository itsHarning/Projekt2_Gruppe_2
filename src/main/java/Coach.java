import java.util.ArrayList;

public class Coach {
    public int id;
    static int numOfCoaches = 0;
    public String name;
    public String teamName;
    ArrayList<Member> assignedMembers;

    public Coach(String name, String teamName){
        numOfCoaches ++;
        this.id=numOfCoaches;
        this.name=name;
        this.teamName = teamName;
        assignedMembers = new ArrayList<>();
    }

    public String toString(){
        return  name;
    }
    public ArrayList<Member> getAssignedMembers() {
        return assignedMembers;
    }

}
