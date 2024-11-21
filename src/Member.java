import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
public class Member {
    public static void main(String[] args) throws Exception {
        Member test = new Member(1,"membername",52,true,true);
        test.textfilogMember();
        System.out.println(members);
    }
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

    @Override
    public String toString() {
        return "Member:"+memberId+ memberName+memberAge+isActiveMember+isCompeting;
    }

    //Læser text filer og input member
    public void textfilogMember() throws IOException {
        FileReader fil = new FileReader("src//Memberlist.txt");
        BufferedReader ind = new BufferedReader(fil);
        String linje = ind.readLine(); // Laver det den læser om til en String
        while (linje !=null) {
            String[]bidder = linje.split(",");

            String id = bidder [0];
            String navn = bidder[1];
            String alder = bidder[2];

            boolean active = Boolean.parseBoolean(bidder[3]);
            boolean competitive = Boolean.parseBoolean(bidder[4]);

            Integer Sid;
            Integer Salder;
            try {
                Sid = Integer.valueOf(id);
                int Cid = Sid;
                Salder = Integer.valueOf(alder);
                int CAlder = Salder;

                Member.members.add(new Member(Cid,navn,CAlder,active,competitive));

            } catch (NumberFormatException e){

                System.out.println("Fejl");
            }

        }

    }
}



