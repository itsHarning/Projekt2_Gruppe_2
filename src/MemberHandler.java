import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
}
