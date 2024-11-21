

    import java.util.Scanner;

    public class CreateNewMember {
        static Scanner keyboard = new Scanner(System.in);
        public void createNewMember() {
            int memberId=1;
            System.out.println("Hvad er dit navn?");
            String memberName= keyboard.nextLine();
            System.out.println("Hvor gammel er du?");
            int memberAge = keyboard.nextInt();
            keyboard.nextLine();
            System.out.println("Hvad er dit telefon nummer");
            String memberPhoneNumber = keyboard.nextLine();

            boolean isActiveMember = true;
            System.out.println("Er du kompetitiv? ja eller nej");
            String ja = "ja";
            String nej = "nej";
            boolean isCompeting = false;
            if(keyboard.nextLine().equals(ja)) {
                isCompeting = true;
            } else if (keyboard.nextLine().equals(nej))
            {
                isCompeting = false;
            }

            Member Members = new Member(memberId, memberName, memberAge, memberPhoneNumber, isActiveMember, isCompeting);
        }

        public static void main(String[] args) {
            CreateNewMember test = new CreateNewMember();
            test.createNewMember();
        }
    }


