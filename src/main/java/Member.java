import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

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
    @JsonProperty("TimeHolder")
    ArrayList<RecordedTime> personalTimes = new ArrayList<>();

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

    // used when loading everything from .json
    Member(){
        numOfMembers++;
    }

    public String getProfile(){
        return memberName + " " + memberGender + " " + memberAge;
    }

    public String toString(){
        return "\nID: "+memberId+"\tNavn: "+memberName+"\tKøn: "+memberGender+"\tAlder: "+memberAge
                +"\tEr medlemskab aktivt: "+isActiveMember+", Er medlemmet konkurrence svømmer: "
                +isCompeting+", Har medlemmet betalt: "+hasPaid;
    }
}