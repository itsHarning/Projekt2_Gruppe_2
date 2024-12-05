import com.fasterxml.jackson.annotation.JsonProperty;

public class Member {

    @JsonProperty("memberId")
    public int memberId;
    @JsonProperty("numOfMembers")
    static int numOfMembers=1;
    @JsonProperty("memberName")
    String memberName;
    @JsonProperty("memberGender")
    Gender memberGender;
    @JsonProperty("memberAge")
    int memberAge;
    @JsonProperty("isActiveMember")
    boolean isActiveMember;
    @JsonProperty("isCompeting")
    boolean isCompeting;
    @JsonProperty("hasPaid")
    boolean hasPaid;
    @JsonProperty("automaticPayment")
    boolean automaticPayment;
    @JsonProperty("competitiveSwimmer")
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

    Member(){
        numOfMembers++;
    }

    public String toString(){
        return "ID: "+memberId+"\tNavn: "+memberName+"\tkøn: "+memberGender+"\talder: "+memberAge+"\ter medlemskab aktivt: "+isActiveMember+", konkurrence svømmer: "+isCompeting+", har betalt: "+hasPaid+"\n";
    }
}