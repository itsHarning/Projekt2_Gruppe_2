import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class MemberHandler {
        public static void main(String[] args) throws IOException{
                /*
                ArrayList<Member> membersList = new ArrayList<>();
                membersList.add(CreateNewMember.createNewMember());
                System.out.println(membersList);
                */

                //Ved ikke hvordan jeg ellers kalder textfilogMember. Har også en toSting i Member for at teste
                MemberHandler test = new MemberHandler();
                Member.members.add(new Member(1,"Heydu",22,true,false));
                Member.members.add(new Member(2,"bob",22,true,false));

                test.updateTextfil();
                test.textfilogMember();
                System.out.println(Member.members);

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

                        try {
                                int cId = Integer.parseInt(id);
                                int cAlder = Integer.parseInt(alder);

                                Member.members.add(new Member(cId,navn,cAlder,active,competitive));

                        } catch (NumberFormatException e){

                                System.out.println("Fejl");
                        }
                        linje = ind.readLine();
                }
        }
        public void updateTextfil() throws IOException{
                FileWriter fil = new FileWriter("src//Memberlist.txt", false);                        // Her finder den BookingListen
                PrintWriter ud = new PrintWriter(fil);
                for (Member m: Member.members){
                        int id = m.memberId;
                        String name = m.memberName;
                        int age = m.memberAge;
                        boolean active = m.isActiveMember;
                        boolean competing = m.isCompeting;

                        ud.println(id+","+name+","+age+","+active+","+competing);
                }
                ud.close();
                // Her stopper jeg printeren
        }
}
