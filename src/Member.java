import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
public class Member {

    static ArrayList <Member> members = new ArrayList<>();
    int memberId;
    String memberName;
    int memberAge;
    String memberPhoneNumber;
    boolean isActiveMember;
    boolean isCompeting;

    Member(int Id, String name, int age, boolean active, boolean competing){
        memberId = Id;
        memberName = name;
        memberAge = age;
        isActiveMember = active;
        isCompeting = competing;
    }

    public String toString(){
        return "Id: "+memberId+" Navn: "+memberName+" alder: "+memberAge+" aktive/ikke aktiv: "+isActiveMember+" Kompetetiv: "+isCompeting;
    }

    int getMemberId() {
        return memberId;
    }

    // Member skal nok have en boolean med betalt eller ikke betalt - Mads.
    /*void setHasPaid(boolean hasPaid) {
        this.hasPaid = hasPaid;
    }
     */

}