public class Member {

    int memberId;
    static int numOfMembers=0;
    String memberName;
    Gender memberGender;
    int memberAge;
    boolean isActiveMember;
    boolean isCompeting;
    boolean hasPaid;
    boolean automaticPayment;
    CompetitiveMember competitiveSwimmer;

    // when loading members from the MemberList.txt file, they already have an ID, this constructor helps them keep that ID
    Member(int id, String name, Gender gender, int age, boolean active, boolean competing, boolean paid, boolean autoPay){
        numOfMembers = id;
        memberId = id;
        memberGender = gender;
        memberName = name;
        memberAge = age;
        isActiveMember = active;
        isCompeting = competing;
        hasPaid = paid;
        automaticPayment = autoPay;
    }

    // this is the normal way of creating a Member, with the ID being automatically assigned
    Member(String name, Gender gender, int age, boolean active, boolean competing, boolean paid, boolean autoPay){
        numOfMembers++;
        memberId = numOfMembers;
        memberName = name;
        memberGender = gender;
        memberAge = age;
        isActiveMember = active;
        isCompeting = competing;
        hasPaid = paid;
        automaticPayment = autoPay;
    }

    public String toString(){
        return "ID: "+memberId+", Navn: "+memberName+", alder: "+memberAge+", er medlemskab aktivt: "+isActiveMember+", konkurrence svømmer: "+isCompeting+", har betalt: "+hasPaid+"\n";
    }
}